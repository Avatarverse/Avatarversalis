package net.avatarverse.avatarversalis.event.user;

import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.event.BendingEvent;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class UserEvent extends BendingEvent {
	protected final User user;
}
