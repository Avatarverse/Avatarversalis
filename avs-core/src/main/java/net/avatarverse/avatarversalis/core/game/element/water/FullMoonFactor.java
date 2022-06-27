package net.avatarverse.avatarversalis.core.game.element.water;

import net.avatarverse.avatarversalis.core.game.Game;

public class FullMoonFactor extends WaterFactor {
	FullMoonFactor() {
		super(FullMoonFactor.class, Game.configManager().config().node("properties", "water", "full-moon-factor"));
	}
}
