package net.avatarverse.avatarversalis.ability.fire;

import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.ability.Activation;
import net.avatarverse.avatarversalis.core.ability.Addon;
import net.avatarverse.avatarversalis.core.element.Element;
import net.avatarverse.avatarversalis.core.user.User;

public class FireBall extends AbilityInstance implements Addon {

	static {
		Ability.builder("FireBall", Element.FIRE)
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
