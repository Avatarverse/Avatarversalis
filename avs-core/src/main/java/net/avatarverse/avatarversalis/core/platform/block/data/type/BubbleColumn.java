package net.avatarverse.avatarversalis.core.platform.block.data.type;

import net.avatarverse.avatarversalis.core.platform.block.data.BlockData;

public interface BubbleColumn extends BlockData {
	boolean drag();
	BubbleColumn drag(boolean drag);
}
