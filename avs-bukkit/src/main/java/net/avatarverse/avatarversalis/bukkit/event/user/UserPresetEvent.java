package net.avatarverse.avatarversalis.bukkit.event.user;

import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.game.user.preset.Preset;
import net.avatarverse.avatarversalis.core.platform.event.Cancellable;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
public class UserPresetEvent extends UserEvent implements net.avatarverse.avatarversalis.core.event.user.UserPresetEvent {

	@Getter private final Preset preset;
	@Getter private final Action action;

	private boolean cancelled = false;

	public UserPresetEvent(User user, Preset preset, Action action) {
		super(user);
		this.preset = preset;
		this.action = action;
	}

	public UserPresetEvent call() {
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
