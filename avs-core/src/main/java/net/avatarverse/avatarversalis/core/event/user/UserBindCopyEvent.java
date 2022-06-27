package net.avatarverse.avatarversalis.core.event.user;

import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.platform.event.Cancellable;

public interface UserBindCopyEvent extends UserEvent, Cancellable {
	User beingCopied();
	UserBindCopyEvent call();
}
