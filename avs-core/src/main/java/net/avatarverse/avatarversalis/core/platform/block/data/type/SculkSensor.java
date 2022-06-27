package net.avatarverse.avatarversalis.core.platform.block.data.type;

import net.avatarverse.avatarversalis.core.platform.block.data.AnaloguePowerable;
import net.avatarverse.avatarversalis.core.platform.block.data.Waterlogged;

public interface SculkSensor extends AnaloguePowerable, Waterlogged {

	Phase phase();
	SculkSensor phase(Phase phase);

	enum Phase {
		ACTIVE, COOLDOWN, INACTIVE
	}
}
