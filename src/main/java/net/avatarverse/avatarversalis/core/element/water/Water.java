package net.avatarverse.avatarversalis.core.element.water;

import lombok.Getter;
import lombok.Setter;

public final class Water {

	@Getter @Setter public static NightFactor nightFactor = new NightFactor();
	@Getter @Setter public static FullMoonFactor fullMoonFactor = new FullMoonFactor();

}
