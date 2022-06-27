package net.avatarverse.avatarversalis.core.game.ability;

import java.util.function.Consumer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Activation {

	ATTACK(AbilityInstance::onAttack),
	INTERACT(AbilityInstance::onInteract),
	INTERACT_ENTITY(AbilityInstance::onInteractEntity),
	INTERACT_BLOCK(AbilityInstance::onInteractBlock),
	SNEAK(AbilityInstance::onSneak),
	SNEAK_RELEASE(AbilityInstance::onSneakRelease),
	FALL(AbilityInstance::onFall),
	SUNRISE(AbilityInstance::onSunrise),
	SUNSET(AbilityInstance::onSunset),
	COMBO(null);

	@Getter private final Consumer<AbilityInstance> method;

}
