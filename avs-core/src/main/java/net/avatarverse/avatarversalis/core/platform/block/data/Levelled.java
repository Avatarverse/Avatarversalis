package net.avatarverse.avatarversalis.core.platform.block.data;

public interface Levelled extends BlockData {
	int level();
	Levelled level(int level);
	int maxLevel();
}
