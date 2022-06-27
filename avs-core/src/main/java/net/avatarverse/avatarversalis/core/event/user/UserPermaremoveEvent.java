package net.avatarverse.avatarversalis.core.event.user;

import net.avatarverse.avatarversalis.core.platform.event.Cancellable;

public interface UserPermaremoveEvent extends UserEvent, Cancellable {
	UserPermaremoveEvent call();
}
