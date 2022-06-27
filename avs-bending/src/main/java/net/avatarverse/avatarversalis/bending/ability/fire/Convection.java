package net.avatarverse.avatarversalis.bending.ability.fire;

import net.avatarverse.avatarversalis.core.config.AbilityConfig;
import net.avatarverse.avatarversalis.core.game.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.game.user.User;

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
