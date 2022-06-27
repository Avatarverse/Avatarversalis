package net.avatarverse.avatarversalis.core.platform.block.data.type;

import net.avatarverse.avatarversalis.core.platform.block.data.Bisected;
import net.avatarverse.avatarversalis.core.platform.block.data.Directional;
import net.avatarverse.avatarversalis.core.platform.block.data.Waterlogged;

public interface Stairs extends Bisected, Directional, Waterlogged {

	Shape shape();
	Stairs shape(Shape shape);

	enum Shape {
		STRAIGHT,
		INNER_LEFT,
		INNER_RIGHT,
		OUTER_LEFT,
		OUTER_RIGHT
	}
}
