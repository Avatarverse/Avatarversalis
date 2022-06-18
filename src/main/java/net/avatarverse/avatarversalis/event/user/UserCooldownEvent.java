package net.avatarverse.avatarversalis.event.user;

import org.bukkit.event.Cancellable;

import net.avatarverse.avatarversalis.core.temporary.Cooldown;
import net.avatarverse.avatarversalis.core.user.User;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
public class UserCooldownEvent extends UserEvent implements Cancellable {

	@Getter private final Cooldown cooldown;
	@Getter private final Result result;

	private boolean cancelled = false;

	public UserCooldownEvent(User user, Cooldown cooldown, Result result) {
		super(user);
		this.cooldown = cooldown;
		this.result = result;
	}

	public UserCooldownEvent call() {
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

	public enum Result {
		START, END
	}
}
