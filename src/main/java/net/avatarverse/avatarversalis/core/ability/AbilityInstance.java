package net.avatarverse.avatarversalis.core.ability;

import net.avatarverse.avatarversalis.core.user.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public abstract class AbilityInstance implements Updateable {

	@Getter @Setter(AccessLevel.PACKAGE) protected User user;
	@Getter protected final Ability ability;

	public AbilityInstance(User user, Ability ability) {
		this.user = user;
		this.ability = ability;
	}

	protected abstract boolean start(Activation method);
	protected abstract void end();
	protected abstract void load();

}
