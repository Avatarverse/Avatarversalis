package net.avatarverse.avatarversalis.bukkit.event.user;

import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.platform.event.Cancellable;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
public class UserBindCopyEvent extends UserEvent implements net.avatarverse.avatarversalis.core.event.user.UserBindCopyEvent {

	@Getter private final User beingCopied;

	private boolean cancelled = false;

	public UserBindCopyEvent(User user, User beingCopied) {
		super(user);
		this.beingCopied = beingCopied;
	}

	public UserBindCopyEvent call() {
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
