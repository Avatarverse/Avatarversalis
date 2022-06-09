package net.avatarverse.avatarversalis.ability.fire;

import net.avatarverse.avatarversalis.config.AbilityConfig;
import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.user.User;

public class Convection extends AbilityInstance {

	public Convection(User user) {
		super(user);
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

	private static class Config extends AbilityConfig {



		@Override
		public void onLoad() {

		}
	}
}
