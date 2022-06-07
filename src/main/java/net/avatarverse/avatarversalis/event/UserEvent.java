package net.avatarverse.avatarversalis.event;

import net.avatarverse.avatarversalis.core.user.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class UserEvent extends BendingEvent {
	private final User user;
}
