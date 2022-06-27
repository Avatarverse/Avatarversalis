package net.avatarverse.avatarversalis.core.platform.block.data.type;

import net.avatarverse.avatarversalis.core.platform.block.data.BlockData;

public interface Snowable extends BlockData {
	boolean snowy();
	Snowable snowy(boolean snowy);
}
