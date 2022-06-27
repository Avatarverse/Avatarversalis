package net.avatarverse.avatarversalis.core.platform.block;

import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.Material;
import net.avatarverse.avatarversalis.core.platform.World;
import net.avatarverse.avatarversalis.core.platform.block.data.BlockData;

public interface BlockState {
	Block block();
	Location location();
	World world();
	int x();
	int y();
	int z();
	BlockData data();
	Material type();
	BlockState data(BlockData data);
	BlockState type(Material type);

	boolean update();
	boolean update(boolean force);
	boolean update(boolean force, boolean applyPhysics);
}
