package net.avatarverse.avatarversalis.core.element.water;

import net.avatarverse.avatarversalis.Avatarversalis;

public class NightFactor extends WaterFactor {
	NightFactor() {
		super(NightFactor.class, Avatarversalis.configManager().config().node("properties", "water", "night-factor"));
	}
}
