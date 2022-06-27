package net.avatarverse.avatarversalis.core.event.user;

import net.avatarverse.avatarversalis.core.game.temporary.Cooldown;
import net.avatarverse.avatarversalis.core.platform.event.Cancellable;

public interface UserCooldownEvent extends UserEvent, Cancellable {
	Cooldown cooldown();
	Result result();
	UserCooldownEvent call();

	enum Result {
		START, END
	}
}
