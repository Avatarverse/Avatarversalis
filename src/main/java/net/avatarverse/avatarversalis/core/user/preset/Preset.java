package net.avatarverse.avatarversalis.core.user.preset;

import java.util.Map;

import net.avatarverse.avatarversalis.Avatarversalis;
import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.core.user.AvatarPlayer;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Preset {

	public static final int LIMIT_PER_PLAYER = Avatarversalis.config().node("properties", "per-player-limit").getInt(5);

	private final Map<Integer, Ability> binds;
	private final String name;

	public Preset(AvatarPlayer player, String name) {
		this.binds = player.binds();
		this.name = name;
	}

}
