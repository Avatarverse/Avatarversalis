package net.avatarverse.avatarversalis.core.ability;

import java.util.HashSet;
import java.util.Set;

import net.avatarverse.avatarversalis.core.attribute.AttributeModifier;
import net.avatarverse.avatarversalis.core.element.Element;
import net.avatarverse.avatarversalis.core.element.fire.Fire;
import net.avatarverse.avatarversalis.core.element.water.Water;
import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.event.AbilityStartEvent;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public abstract class AbilityInstance {

	@Getter @Setter(AccessLevel.PACKAGE) protected User user;
	@Getter protected final Ability ability;
	@Getter protected final Set<AttributeModifier> modifiers;
	@Getter protected final long startTime;

	public AbilityInstance(User user) {
		this.user = user;
		this.ability = AbilityManager.ABILITIES_BY_CLASS.get(getClass());
		this.modifiers = new HashSet<>();

		addBaseModifiers();
		load();

		this.startTime = System.currentTimeMillis();
		start();
		postStart();
	}

	private void addBaseModifiers() {
		if (ability.element() == Element.FIRE) // TODO && isDay
			modifiers.addAll(Fire.dayFactor().modifiers());
		if (user.hasElement(Element.BLUE_FIRE))
			modifiers.addAll(Fire.blueFireFactor().modifiers());
		if (ability.element() == Element.WATER)
			modifiers.addAll(Water.nightFactor().modifiers()); // TODO && isNight
	}

	protected final void start() {
		if (new AbilityStartEvent(user, this).call().isCancelled()) return;
		AbilityManager.INSTANCES.add(this);
		AbilityManager.INSTANCES_BY_USER.get(user).add(this);
	}

	public final void end() {
		AbilityManager.INSTANCES.remove(this);
		AbilityManager.INSTANCES_BY_USER.get(user).remove(this);
	}

	protected abstract void load();
	protected abstract void postStart();
	protected abstract boolean update();
	protected abstract void cleanup();

	protected void onAttack() {}
	protected void onInteract() {}
	protected void onInteractEntity() {}
	protected void onInteractBlock() {}
	protected void onSneak() {}
	protected void onSneakRelease() {}
	protected void onFall() {}

	protected boolean elapsed(long interval) {
		return elapsed(startTime, interval);
	}

	protected static boolean elapsed(long startTime, long interval) {
		return System.currentTimeMillis() > startTime + interval;
	}

}
