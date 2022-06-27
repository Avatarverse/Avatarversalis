package net.avatarverse.avatarversalis.bukkit.event.user;

import net.avatarverse.avatarversalis.bukkit.event.BendingEvent;
import net.avatarverse.avatarversalis.core.game.user.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class UserEvent extends BendingEvent implements net.avatarverse.avatarversalis.core.event.user.UserEvent {
	protected final User user;

	@Override
	public User user() {
		return user;
	}
}
