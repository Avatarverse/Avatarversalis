package net.avatarverse.avatarversalis.core.platform.block;

import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.Material;
import net.avatarverse.avatarversalis.core.platform.World;
import net.avatarverse.avatarversalis.core.platform.block.data.BlockData;

public interface Block {
	BlockData data();
	BlockState state();
	Material type();
	World world();
	int x();
	int y();
	int z();
	Block data(BlockData data);
	Block type(Material type);
	Block type(Material type, boolean applyPhysics);

	boolean breakNaturally();
	boolean solid();
	boolean liquid();
	Location location();
	default Location center() { return location().add(0.5, 0.5, 0.5); }
	Block relative(BlockFace face);
	Block relative(BlockFace face, int distance);
}
