package net.avatarverse.avatarversalis.core.game.element.fire;

import net.avatarverse.avatarversalis.core.game.Game;

public class BlueFireFactor extends FireFactor {
	BlueFireFactor() {
		super(BlueFireFactor.class, Game.configManager().config().node("properties", "fire", "blue-fire-factor"));
	}
}
