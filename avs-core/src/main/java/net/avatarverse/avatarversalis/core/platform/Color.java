package net.avatarverse.avatarversalis.core.platform;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@DefaultAnnotation(NonNull.class)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Color {

	private final int red, green, blue;

	public static Color fromRGB(int red, int green, int blue) {
		return new Color(red, green, blue);
	}

}
