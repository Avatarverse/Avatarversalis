package net.avatarverse.avatarversalis.core.platform.block.data.type;

import net.avatarverse.avatarversalis.core.platform.block.data.Directional;

public interface Dispenser extends Directional {
	boolean triggered();
	Dispenser triggered(boolean triggered);
}
