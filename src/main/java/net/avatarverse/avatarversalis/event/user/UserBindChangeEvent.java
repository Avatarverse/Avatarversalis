package net.avatarverse.avatarversalis.event.user;

import org.bukkit.event.Cancellable;

import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.core.user.User;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
public class UserBindChangeEvent extends UserEvent implements Cancellable {

	@Getter private final Ability ability;
	@Getter private final int slot;
	@Getter private final Result result;

	private boolean cancelled = false;

	public UserBindChangeEvent(User user, Ability ability, int slot, Result result) {
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
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}

	public enum Result {
		BIND, UNBIND
	}
}
