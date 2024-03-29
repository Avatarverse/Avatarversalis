package net.avatarverse.avatarversalis.core.game.attribute;

import net.avatarverse.avatarversalis.core.game.ability.AbilityInstance;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Attributes are categories that aspects of abilities (fields of {@link AbilityInstance} can fit into.
 */
@RequiredArgsConstructor
public enum Attribute {

	DAMAGE("Damage"),
	RANGE("Range"),
	SOURCE_RANGE("SourceRange"),
	COOLDOWN("Cooldown"),
	DURATION("Duration"),
	SPEED("Speed"),
	KNOCKBACK("Knockback"),
	KNOCKUP("Knockup"),
	CHARGE_TIME("ChargeTime"),
	RADIUS("Radius"),
	HEIGHT("Height"),
	AMOUNT("Amount"),
	FIRE_TICKS("FireTicks"),
	FREEZE_TICKS("FreezeTicks");

	@Getter private final String value;

}
