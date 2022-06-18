package net.avatarverse.avatarversalis.event.user;

import org.bukkit.event.Cancellable;

import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.core.user.preset.Preset;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
public class UserPresetEvent extends UserEvent implements Cancellable {

	@Getter private final Preset preset;
	@Getter private final Result result;

	private boolean cancelled = false;

	public UserPresetEvent(User user, Preset preset, Result result) {
		super(user);
		this.preset = preset;
		this.result = result;
	}

	public UserPresetEvent call() {
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
		CREATE, DELETE, BIND
	}
}
