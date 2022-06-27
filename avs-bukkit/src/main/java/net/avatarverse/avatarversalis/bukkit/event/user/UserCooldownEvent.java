package net.avatarverse.avatarversalis.bukkit.event.user;

import net.avatarverse.avatarversalis.core.game.temporary.Cooldown;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.platform.event.Cancellable;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
public class UserCooldownEvent extends UserEvent implements net.avatarverse.avatarversalis.core.event.user.UserCooldownEvent {

	@Getter private final Cooldown cooldown;
	@Getter private final net.avatarverse.avatarversalis.core.event.user.UserCooldownEvent.Result result;

	private boolean cancelled = false;

	public UserCooldownEvent(User user, Cooldown cooldown, net.avatarverse.avatarversalis.core.event.user.UserCooldownEvent.Result result) {
		super(user);
		this.cooldown = cooldown;
		this.result = result;
	}

	public UserCooldownEvent call() {
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
