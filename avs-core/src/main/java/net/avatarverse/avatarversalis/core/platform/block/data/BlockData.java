package net.avatarverse.avatarversalis.core.platform.block.data;

import net.avatarverse.avatarversalis.core.platform.Material;

public interface BlockData extends Cloneable {
	Material material();
	String asString();
	BlockData clone();
}
