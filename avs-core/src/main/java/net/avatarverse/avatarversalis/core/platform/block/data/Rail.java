package net.avatarverse.avatarversalis.core.platform.block.data;

import java.util.Set;

public interface Rail extends Waterlogged {

	Shape shape();
	Rail shape(Shape shape);
	Set<Shape> shapes();

	enum Shape {
		NORTH_EAST,
		NORTH_SOUTH,
		NORTH_WEST,
		SOUTH_EAST,
		SOUTH_WEST,
		EAST_WEST,
		ASCENDING_NORTH,
		ASCENDING_EAST,
		ASCENDING_SOUTH,
		ASCENDING_WEST
	}
}
