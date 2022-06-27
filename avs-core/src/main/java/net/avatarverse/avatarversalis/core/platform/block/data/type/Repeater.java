package net.avatarverse.avatarversalis.core.platform.block.data.type;

import net.avatarverse.avatarversalis.core.platform.block.data.Directional;
import net.avatarverse.avatarversalis.core.platform.block.data.Powerable;

public interface Repeater extends Directional, Powerable {
	int delay();
	int maxDelay();
	int minDelay();
	boolean locked();
	Repeater delay(int delay);
	Repeater locked(boolean locked);
}
