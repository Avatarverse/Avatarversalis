package net.avatarverse.avatarversalis.core.platform.block.data.type;

import net.avatarverse.avatarversalis.core.platform.block.data.Waterlogged;

public interface Slab extends Waterlogged {

	Type type();
	Slab type(Type type);

	enum Type {
		BOTTOM, TOP, DOUBLE
	}
}
