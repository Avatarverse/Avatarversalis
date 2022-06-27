package net.avatarverse.avatarversalis.core.platform.block.data;

public interface AnaloguePowerable extends BlockData {
	int power();
	AnaloguePowerable power(int power);
	int maxPower();
}
