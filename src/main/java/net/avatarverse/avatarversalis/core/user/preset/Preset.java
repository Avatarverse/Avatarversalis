package net.avatarverse.avatarversalis.core.user.preset;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.core.user.AvatarPlayer;

import edu.umd.cs.findbugs.annotations.ReturnValuesAreNonnullByDefault;
import lombok.Getter;

@ParametersAreNonnullByDefault
@ReturnValuesAreNonnullByDefault
@Getter
public class Preset {

	private final Map<Integer, Ability> binds;
	private final String name;

	public Preset(AvatarPlayer ap, String name) {
		this.binds = new ConcurrentHashMap<>(ap.binds());
		this.name = name;
	}

}
