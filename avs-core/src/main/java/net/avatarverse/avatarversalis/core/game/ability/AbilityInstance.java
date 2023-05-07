package net.avatarverse.avatarversalis.core.game.ability;

import java.util.HashSet;
import java.util.Set;

import net.avatarverse.avatarversalis.core.event.ability.AbilityConfigLoadEvent;
import net.avatarverse.avatarversalis.core.event.ability.AbilityStartEvent;
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
	protected long tick;

	/**
	 * Instantiates a new AbilityInstance for the given user.
	 * Does not complete instantiation if the user cannot bend the ability.
	 * Also loads the ability's configured values.
	 * @param user the user of this ability
	 */
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

	/**
	 * Starts the ability by setting it as an actively updating instance.
	 */
	protected final void start() {
		if (user.permaremoved()) return;
		if (!Game.eventBus().postAbilityStartEvent(user, this)) return;
		AbilityManager.INSTANCES.add(this);
		AbilityManager.INSTANCES_BY_USER.get(user).add(this);
		this.startTime = System.currentTimeMillis();
		postStart();
	}

	/**
	 * Stops the ability by removing it from the actively updating instances.
	 */
	public final void stop() {
		if (AbilityManager.INSTANCES.contains(this)) {
			AbilityManager.INSTANCES.remove(this);
			AbilityManager.INSTANCES_BY_USER.get(user).remove(this);
			cleanup();
		}
	}

	/**
	 * Called just after the ability is instantiated. Not called if the user cannot bend the ability or the {@link AbilityConfigLoadEvent} is cancelled.
	 */
	protected abstract void load();

	/**
	 * Called just after the ability is started. Not called if the {@link AbilityStartEvent} is cancelled.
	 */
	protected abstract void postStart();

	/**
	 * Called every tick while the ability is active.
	 * @return true if the ability should continue to be updated, false if it should stop
	 */
	protected abstract boolean update();

	/**
	 * Called just after the ability is stopped.
	 */
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
