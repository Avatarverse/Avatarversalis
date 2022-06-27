package net.avatarverse.avatarversalis.core.platform.block.data.type;

import java.util.Set;

import net.avatarverse.avatarversalis.core.platform.block.BlockFace;
import net.avatarverse.avatarversalis.core.platform.block.data.AnaloguePowerable;

public interface RedstoneWire extends AnaloguePowerable {

	Connection face(BlockFace face);
	RedstoneWire face(BlockFace face, Connection connection);
	Set<BlockFace> allowedFaces();

	enum Connection {
		SIDE,
		UP,
		NONE
	}
}
