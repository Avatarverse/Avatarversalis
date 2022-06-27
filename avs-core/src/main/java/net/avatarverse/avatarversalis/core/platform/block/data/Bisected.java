package net.avatarverse.avatarversalis.core.platform.block.data;

public interface Bisected extends BlockData {

	Half half();
	Bisected half(Half half);

	enum Half {
		BOTTOM, TOP
	}
}
