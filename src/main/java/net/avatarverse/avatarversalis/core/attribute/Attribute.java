package net.avatarverse.avatarversalis.core.attribute;

import lombok.Getter;

public enum Attribute {

	DAMAGE("Damage"),
	RANGE("Range"),
	SOURCE_RANGE("SourceRange"),
	COOLDOWN("Cooldown"),
	DURATION("Duration"),
	SPEED("Speed"),
	CHARGE_TIME("ChargeTime"),
	RADIUS("Radius"),
	HIT_RADIUS("HitRadius"),
	HEIGHT("Height"),
	AMOUNT("Amount"),
	FIRE_TICKS("FireTicks"),
	FREEZE_TICKS("FreezeTicks");

	@Getter private final String value;

	Attribute(String value) {
		this.value = value;
	}

}
