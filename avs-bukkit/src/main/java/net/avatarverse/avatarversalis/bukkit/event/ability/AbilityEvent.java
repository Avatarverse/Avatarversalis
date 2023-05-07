package net.avatarverse.avatarversalis.bukkit.event.ability;

import net.avatarverse.avatarversalis.bukkit.event.user.UserEvent;
import net.avatarverse.avatarversalis.core.game.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.game.user.User;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
public abstract class AbilityEvent extends UserEvent implements net.avatarverse.avatarversalis.core.event.ability.AbilityEvent {

	protected final AbilityInstance ability;

	protected boolean cancelled = false;

	public AbilityEvent(User user, AbilityInstance ability) {
		super(user);
		this.ability = ability;
	}

	public AbilityEvent call() {
		super.call();
		return this;
	}

	public AbilityInstance ability() {
		return this.ability;
	}

	@Override
	public boolean cancelled() {
		return cancelled;
	}

	@Override
	public AbilityEvent cancelled(boolean cancel) {
		this.cancelled = cancel;
		return this;
	}
}
