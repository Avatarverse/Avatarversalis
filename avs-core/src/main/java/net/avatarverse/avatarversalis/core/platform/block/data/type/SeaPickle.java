package net.avatarverse.avatarversalis.core.platform.block.data.type;

import net.avatarverse.avatarversalis.core.platform.block.data.Waterlogged;

public interface SeaPickle extends Waterlogged {
	int pickles();
	SeaPickle pickles(int pickles);
	int maxPickles();
	int minPickles();
}
