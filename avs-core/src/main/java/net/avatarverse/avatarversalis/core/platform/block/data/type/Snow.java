package net.avatarverse.avatarversalis.core.platform.block.data.type;

import net.avatarverse.avatarversalis.core.platform.block.data.BlockData;

public interface Snow extends BlockData {
	int layers();
	Snow layers(int layers);
	int maxLayers();
	int minLayers();
}
