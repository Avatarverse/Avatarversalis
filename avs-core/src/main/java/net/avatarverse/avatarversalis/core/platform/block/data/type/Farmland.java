package net.avatarverse.avatarversalis.core.platform.block.data.type;

import net.avatarverse.avatarversalis.core.platform.block.data.BlockData;

public interface Farmland extends BlockData {
	int moisture();
	Farmland moisture(int moisture);
	int maxMoisture();
}
