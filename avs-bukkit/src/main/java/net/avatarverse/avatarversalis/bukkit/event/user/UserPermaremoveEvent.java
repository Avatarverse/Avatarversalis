package net.avatarverse.avatarversalis.bukkit.event.user;

import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.platform.event.Cancellable;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
public class UserPermaremoveEvent extends UserEvent implements net.avatarverse.avatarversalis.core.event.user.UserPermaremoveEvent {

	private boolean cancelled = false;

	public UserPermaremoveEvent(User user) {
		super(user);
	}

	public UserPermaremoveEvent call() {
		super.call();
		return this;
	}

	@Override
	public boolean cancelled() {
		return cancelled;
	}

	@Override
	public Cancellable cancelled(boolean cancel) {
		this.cancelled = cancel;
		return this;
	}
}
