package net.avatarverse.avatarversalis.core.platform.potion;

import java.util.Arrays;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
public enum PotionEffectType {

	ABSORPTION(22),
	BAD_OMEN(31),
	BLINDNESS(15),
	CONDUIT_POWER(29),
	CONFUSION(9),
	DAMAGE_RESISTANCE(11),
	DARKNESS(33),
	DOLPHINS_GRACE(30),
	FAST_DIGGING(3),
	FIRE_RESISTANCE(12),
	GLOWING(24),
	HARM(7),
	HEAL(6),
	HEALTH_BOOST(21),
	HERO_OF_THE_VILLAGE(32),
	HUNGER(17),
	INCREASE_DAMAGE(5),
	INVISIBILITY(14),
	JUMP(8),
	LEVITATION(25),
	LUCK(26),
	NIGHT_VISION(16),
	POISON(19),
	REGENERATION(10),
	SATURATION(23),
	SLOW(2),
	SLOW_DIGGING(4),
	SLOW_FALLING(28),
	SPEED(1),
	UNLUCK(27),
	WATER_BREATHING(13),
	WEAKNESS(18),
	WITHER(20);

	@Getter private final int id;

	PotionEffectType(int id) {
		this.id = id;
	}

	public static @Nullable PotionEffectType byId(int id) {
		return Arrays.stream(values()).filter(type -> type.id == id).findAny().orElse(null);
	}
}
