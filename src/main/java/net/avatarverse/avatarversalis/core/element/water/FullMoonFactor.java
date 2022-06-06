package net.avatarverse.avatarversalis.core.element.water;

import net.avatarverse.avatarversalis.Avatarversalis;

public class FullMoonFactor extends WaterFactor {
	FullMoonFactor() {
		super(FullMoonFactor.class, Avatarversalis.configManager().config().node("properties", "water", "full-moon-factor"));
	}
}
