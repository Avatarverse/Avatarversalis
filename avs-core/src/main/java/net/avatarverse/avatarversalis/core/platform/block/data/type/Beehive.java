package net.avatarverse.avatarversalis.core.platform.block.data.type;

import net.avatarverse.avatarversalis.core.platform.block.data.Directional;

public interface Beehive extends Directional {
	int honeyLevel();
	Beehive honeyLevel(int honeyLevel);
	int maxHoneyLevel();
}
