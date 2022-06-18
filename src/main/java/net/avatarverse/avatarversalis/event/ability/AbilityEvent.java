package net.avatarverse.avatarversalis.event.ability;

import org.bukkit.event.Cancellable;

import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.event.user.UserEvent;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.AccessLevel;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public abstract class AbilityEvent extends UserEvent implements Cancellable {

	protected final AbilityInstance ability;

	@Getter(AccessLevel.NONE) protected boolean cancelled = false;

	public AbilityEvent(User user, AbilityInstance ability) {
		super(user);
		this.ability = ability;
	}

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
}
