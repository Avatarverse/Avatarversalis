package net.avatarverse.avatarversalis.core.platform.block.data.type;

import net.avatarverse.avatarversalis.core.platform.block.data.BlockData;

public interface Sapling extends BlockData {
	int stage();
	Sapling stage(int stage);
	int maxStage();
}
