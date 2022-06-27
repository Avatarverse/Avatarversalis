package net.avatarverse.avatarversalis.bending.listener;

import java.util.Set;

import net.avatarverse.avatarversalis.bending.Bending;
import net.avatarverse.avatarversalis.core.event.ability.AbilityConfigLoadEvent;
import net.avatarverse.avatarversalis.core.game.ability.Ability;
import net.avatarverse.avatarversalis.core.game.attribute.AttributeModifier;
import net.avatarverse.avatarversalis.core.game.element.fire.Fire;
import net.avatarverse.avatarversalis.core.game.element.water.Water;
import net.avatarverse.avatarversalis.bending.listener.ability.AbilityConfigLoadListener;

public class AbilityListener implements AbilityConfigLoadListener {
	@Override
	public void onAbilityConfigLoad(AbilityConfigLoadEvent event) {
		Ability ability = event.ability().ability();
		Set<AttributeModifier> modifiers = event.ability().modifiers();
		if (ability.element().isOrInherits(Bending.FIRE)) // TODO && isDay
			modifiers.addAll(Fire.dayFactor().modifiers());
		if (ability.element().isOrInherits(Bending.FIRE) && event.user().hasElement(Bending.BLUE_FIRE))
			modifiers.addAll(Fire.blueFireFactor().modifiers());
		if (ability.element().isOrInherits(Bending.WATER))
			modifiers.addAll(Water.nightFactor().modifiers()); // TODO && isNight
	}
}
