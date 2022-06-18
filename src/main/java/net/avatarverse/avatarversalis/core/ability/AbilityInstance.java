package net.avatarverse.avatarversalis.core.ability;

import java.util.HashSet;
import java.util.Set;

import net.avatarverse.avatarversalis.core.attribute.AttributeModifier;
import net.avatarverse.avatarversalis.core.element.Element;
import net.avatarverse.avatarversalis.core.element.fire.Fire;
import net.avatarverse.avatarversalis.core.element.water.Water;
import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.event.ability.AbilityStartEvent;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * An actual instance of an Ability. Individual abilities should extend this class.
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

		addBaseModifiers();
		addUserModifiers();
		load();
	}

	private void addBaseModifiers() {
		if (ability.element().isOrInherits(Element.FIRE)) // TODO && isDay
			modifiers.addAll(Fire.dayFactor().modifiers());
		if (ability.element().isOrInherits(Element.FIRE) && user.hasElement(Element.BLUE_FIRE))
			modifiers.addAll(Fire.blueFireFactor().modifiers());
		if (ability.element().isOrInherits(Element.WATER))
			modifiers.addAll(Water.nightFactor().modifiers()); // TODO && isNight
	}

	private void addUserModifiers() {
		modifiers.addAll(user.modifiers(ability.element()));
		modifiers.addAll(user.modifiers(ability));
	}

	protected final void start() {
		if (user.permaremoved()) return;
		if (new AbilityStartEvent(user, this).call().isCancelled()) return;
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
