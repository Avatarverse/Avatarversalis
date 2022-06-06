package net.avatarverse.avatarversalis.core.element.fire;

import net.avatarverse.avatarversalis.Avatarversalis;

public class BlueFireFactor extends FireFactor {
	BlueFireFactor() {
		super(BlueFireFactor.class, Avatarversalis.configManager().config().node("properties", "fire", "blue-fire-factor"));
	}
}
