package net.avatarverse.avatarversalis.core.element.fire;

import net.avatarverse.avatarversalis.Avatarversalis;

public final class DayFactor extends FireFactor {
	DayFactor() {
		super(DayFactor.class, Avatarversalis.configManager().config().node("properties", "fire", "day-factor"));
	}

}
