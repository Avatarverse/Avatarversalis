package net.avatarverse.avatarversalis.bukkit.event.user;

import net.avatarverse.avatarversalis.core.game.ability.Ability;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.platform.event.Cancellable;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
public class UserBindChangeEvent extends UserEvent implements net.avatarverse.avatarversalis.core.event.user.UserBindChangeEvent {

	@Getter private final Ability ability;
	@Getter private final int slot;
	@Getter private final net.avatarverse.avatarversalis.core.event.user.UserBindChangeEvent.Result result;

	private boolean cancelled = false;

	public UserBindChangeEvent(User user, Ability ability, int slot, net.avatarverse.avatarversalis.core.event.user.UserBindChangeEvent.Result result) {
		super(user);
		this.ability = ability;
		this.slot = slot;
		this.result = result;
	}

	public UserBindChangeEvent call() {
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
