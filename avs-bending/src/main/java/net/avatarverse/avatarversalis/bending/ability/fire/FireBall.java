package net.avatarverse.avatarversalis.bending.ability.fire;

import net.avatarverse.avatarversalis.bending.Bending;
import net.avatarverse.avatarversalis.core.game.ability.Ability;
import net.avatarverse.avatarversalis.core.game.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.game.ability.Activation;
import net.avatarverse.avatarversalis.core.game.ability.Addon;
import net.avatarverse.avatarversalis.core.game.user.User;

public class FireBall extends AbilityInstance implements Addon {

	static {
		Ability.builder("FireBall", Bending.FIRE)
				.description("config path")
				.instructions("config path")
				.activation(Activation.ATTACK, FireBall.class)
				.bindable()
				.author("Nameless Addon Developer")
				.version("1.0.0").build();
	}

	public FireBall(User user) {
		super(user);
	}

	@Override
	protected void load() {

	}

	@Override
	protected void postStart() {

	}

	@Override
	public boolean update() {
		return false;
	}

	@Override
	protected void cleanup() {

	}

	@Override
	public void register() {

	}
}
