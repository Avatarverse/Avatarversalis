package net.avatarverse.avatarversalis.core.platform.block.data.type;

import net.avatarverse.avatarversalis.core.platform.block.BlockFace;
import net.avatarverse.avatarversalis.core.platform.block.data.Waterlogged;

public interface Wall extends Waterlogged {

	Height height(BlockFace face);
	Wall height(BlockFace face, Height height);
	boolean up();
	Wall up(boolean up);

	enum Height {
		LOW, TALL, NONE
	}
}
