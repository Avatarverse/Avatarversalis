package net.avatarverse.avatarversalis.event;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.event.Cancellable;

import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.user.User;

import edu.umd.cs.findbugs.annotations.ReturnValuesAreNonnullByDefault;
import lombok.Getter;

@ParametersAreNonnullByDefault
@ReturnValuesAreNonnullByDefault
@Getter
public abstract class AbilityEvent extends UserEvent implements Cancellable {

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

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}
}
