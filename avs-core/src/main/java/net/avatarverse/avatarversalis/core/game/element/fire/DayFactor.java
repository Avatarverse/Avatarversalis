package net.avatarverse.avatarversalis.core.game.element.fire;

import net.avatarverse.avatarversalis.core.game.Game;

public final class DayFactor extends FireFactor {
	DayFactor() {
		super(DayFactor.class, Game.configManager().config().node("properties", "fire", "day-factor"));
	}

}
