package net.avatarverse.avatarversalis.core.game.ability;

import java.util.HashSet;
import java.util.Set;

import net.avatarverse.avatarversalis.core.game.Game;
import net.avatarverse.avatarversalis.core.game.attribute.AttributeModifier;
import net.avatarverse.avatarversalis.core.game.element.Element;
import net.avatarverse.avatarversalis.core.game.user.User;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * An actual instance of an Ability. Individual abilities should extend this class.
 *
 * All AbilityInstances require a static void method named register() to remotely register the ability.
 *
 * @see Ability#builder(String, Element)
 * @see Ability.Builder#build()
 */
@DefaultAnnotation(NonNull.class)
@Getter
public abstract class AbilityInstance {

	@Setter(AccessLevel.PACKAGE) protected User user;
	protected final Ability ability;
	protected final Set<AttributeModifier> modifiers;
	@Getter(AccessLevel.NONE) protected long startTime;

	public AbilityInstance(User user) {
		this.user = user;
		this.ability = AbilityManager.ABILITIES_BY_CLASS.get(getClass());
		this.modifiers = new HashSet<>();

		if (!user.canBend(ability)) return;

		addUserModifiers();
		if (!Game.eventBus().postAbilityConfigLoadEvent(user, this)) return;
		load();
	}

	private void addUserModifiers() {
		modifiers.addAll(user.modifiers(ability.element()));
		modifiers.addAll(user.modifiers(ability));
	}

	protected final void start() {
		if (user.permaremoved()) return;
		if (!Game.eventBus().postAbilityStartEvent(user, this)) return;
		AbilityManager.INSTANCES.add(this);
		AbilityManager.INSTANCES_BY_USER.get(user).add(this);
		this.startTime = System.currentTimeMillis();
		postStart();
	}

	public final void end() {
		if (AbilityManager.INSTANCES.contains(this)) {
			AbilityManager.INSTANCES.remove(this);
			AbilityManager.INSTANCES_BY_USER.get(user).remove(this);
			cleanup();
		}
	}

	protected abstract void load();
	protected abstract void postStart();
	protected abstract boolean update();

	protected void cleanup() {}

	protected void onAttack() {}
	protected void onInteract() {}
	protected void onInteractEntity() {}
	protected void onInteractBlock() {}
	protected void onSneak() {}
	protected void onSneakRelease() {}
	protected void onFall() {}
	protected void onSunrise() {}
	protected void onSunset() {}

	protected boolean elapsed(long interval) {
		return elapsed(startTime, interval);
	}

	protected static boolean elapsed(long startTime, long interval) {
		return System.currentTimeMillis() > startTime + interval;
	}

}
