package net.avatarverse.avatarversalis.core.game.user.preset;

import java.util.Map;

import net.avatarverse.avatarversalis.core.game.Game;
import net.avatarverse.avatarversalis.core.game.ability.Ability;
import net.avatarverse.avatarversalis.core.game.user.AvatarPlayer;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Preset {

	public static final int LIMIT_PER_PLAYER = Game.config().node("properties", "per-player-limit").getInt(5);

	private final Map<Integer, Ability> binds;
	private final String name;

	public Preset(AvatarPlayer player, String name) {
		this.binds = player.binds();
		this.name = name;
	}

}
