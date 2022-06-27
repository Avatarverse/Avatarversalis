package net.avatarverse.avatarversalis.core.platform.block.data.type;

import java.util.Set;

import net.avatarverse.avatarversalis.core.platform.block.BlockFace;
import net.avatarverse.avatarversalis.core.platform.block.data.Waterlogged;

public interface PointedDripstone extends Waterlogged {

	Thickness thickness();
	PointedDripstone thickness(Thickness thickness);
	BlockFace verticalDirection();
	PointedDripstone verticalDirection(BlockFace direction);
	Set<BlockFace> verticalDirections();

	enum Thickness {
		BASE,
		FRUSTUM,
		MIDDLE,
		TIP,
		TIP_MERGE
	}
}
