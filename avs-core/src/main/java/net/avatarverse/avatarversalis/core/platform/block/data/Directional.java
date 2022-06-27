package net.avatarverse.avatarversalis.core.platform.block.data;

import java.util.Set;

import net.avatarverse.avatarversalis.core.platform.block.BlockFace;

public interface Directional extends BlockData {
	BlockFace facing();
	Directional facing(BlockFace face);
	Set<BlockFace> faces();
}
