package net.avatarverse.avatarversalis.bukkit.event.user;

import net.avatarverse.avatarversalis.core.game.user.User;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
public class UserLoadEvent extends UserEvent implements net.avatarverse.avatarversalis.core.event.user.UserLoadEvent {
	public UserLoadEvent(User user) {
		super(user);
	}
}
