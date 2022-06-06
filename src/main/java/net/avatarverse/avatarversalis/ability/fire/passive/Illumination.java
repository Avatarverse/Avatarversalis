package net.avatarverse.avatarversalis.ability.fire.passive;

import java.util.HashMap;
import java.util.Map;

import net.avatarverse.avatarversalis.config.AbilityConfig;
import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.attribute.Attribute;
import net.avatarverse.avatarversalis.core.attribute.Modifiable;
import net.avatarverse.avatarversalis.core.user.User;

import lombok.Getter;

public class Illumination extends AbilityInstance {

	public static final Map<User, Illumination> DISABLED = new HashMap<>();

	public Illumination(User user) {
		super(user);

		if (DISABLED.containsKey(user)) return;

		start();
	}

	@Override
	protected void load() {

	}

	@Override
	protected void postStart() {

	}

	@Override
	protected boolean update() {
		return false;
	}

	@Override
	protected void cleanup() {

	}

	private static class Config extends AbilityConfig {

		@Modifiable(Attribute.COOLDOWN)
		@Getter private long cooldown;
		@Modifiable(Attribute.RANGE)
		@Getter private double range;

		@Override
		public void onLoad() {

		}
	}
}
