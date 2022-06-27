package net.avatarverse.avatarversalis.core.platform.block.data;

import java.util.Set;

import net.avatarverse.avatarversalis.core.platform.block.BlockFace;

public interface MultipleFacing extends BlockData {
	Set<BlockFace> faces();
	Set<BlockFace> allowedFaces();
	boolean hasFace(BlockFace face);
	MultipleFacing setFace(BlockFace face, boolean has);
}
