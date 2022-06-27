package net.avatarverse.avatarversalis.core.game.element.water;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;
import lombok.Setter;

@DefaultAnnotation(NonNull.class)
public final class Water {

	@Getter @Setter public static NightFactor nightFactor = new NightFactor();
	@Getter @Setter public static FullMoonFactor fullMoonFactor = new FullMoonFactor();

}
