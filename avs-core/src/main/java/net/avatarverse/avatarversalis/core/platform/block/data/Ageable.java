package net.avatarverse.avatarversalis.core.platform.block.data;

public interface Ageable extends BlockData {
	int age();
	Ageable age(int age);
	int maxAge();
}
