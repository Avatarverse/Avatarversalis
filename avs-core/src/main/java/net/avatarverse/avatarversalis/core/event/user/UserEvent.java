package net.avatarverse.avatarversalis.core.event.user;

import net.avatarverse.avatarversalis.core.event.Event;
import net.avatarverse.avatarversalis.core.game.user.User;

public interface UserEvent extends Event {
	User user();
}
