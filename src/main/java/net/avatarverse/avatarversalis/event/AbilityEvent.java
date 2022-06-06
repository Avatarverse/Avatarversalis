package net.avatarverse.avatarversalis.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbilityEvent extends BendingEvent implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private final User user;
	private final AbilityInstance ability;

	private boolean cancelled = false;

	public AbilityEvent call() {
		super.call();
		return this;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

	@Override
	public @NotNull HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
