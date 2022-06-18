package net.avatarverse.avatarversalis.event.user;

import org.bukkit.event.Cancellable;

import net.avatarverse.avatarversalis.core.user.User;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
public class UserBindCopyEvent extends UserEvent implements Cancellable {

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
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}
}
