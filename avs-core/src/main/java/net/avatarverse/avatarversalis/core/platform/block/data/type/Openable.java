package net.avatarverse.avatarversalis.core.platform.block.data.type;

import net.avatarverse.avatarversalis.core.platform.block.data.BlockData;

public interface Openable extends BlockData {
	boolean open();
	Openable open(boolean open);
}
