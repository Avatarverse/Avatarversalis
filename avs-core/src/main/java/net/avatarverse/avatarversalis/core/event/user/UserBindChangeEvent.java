package net.avatarverse.avatarversalis.core.event.user;

import net.avatarverse.avatarversalis.core.game.ability.Ability;
import net.avatarverse.avatarversalis.core.platform.event.Cancellable;

public interface UserBindChangeEvent extends UserEvent, Cancellable {
	Ability ability();
	int slot();
	Result result();
	UserBindChangeEvent call();

	enum Result {
		BIND, UNBIND
	}
}
