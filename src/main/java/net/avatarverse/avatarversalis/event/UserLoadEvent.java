package net.avatarverse.avatarversalis.event;

import net.avatarverse.avatarversalis.core.user.User;

public class UserLoadEvent extends UserEvent {
	public UserLoadEvent(User user) {
		super(user);
	}
}
