package net.avatarverse.avatarversalis.core.platform.block.data.type;

public interface Bamboo {

	Leaves leaves();
	Bamboo leaves(Leaves leaves);

	enum Leaves {
		SMALL, LARGE, NONE
	}
}
