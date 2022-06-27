package net.avatarverse.avatarversalis.core.game.element.water;

import net.avatarverse.avatarversalis.core.game.Game;

public class NightFactor extends WaterFactor {
	NightFactor() {
		super(NightFactor.class, Game.configManager().config().node("properties", "water", "night-factor"));
	}
}
