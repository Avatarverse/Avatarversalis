package net.avatarverse.avatarversalis.core.platform.block.data.type;

import net.avatarverse.avatarversalis.core.platform.block.data.Directional;
import net.avatarverse.avatarversalis.core.platform.block.data.Waterlogged;

public interface BigDripleaf extends Directional, Waterlogged {

	Tilt tilt();
	BigDripleaf tilt(Tilt tilt);

	enum Tilt {
		FULL, PARTIAL, UNSTABLE, NONE
	}
}
