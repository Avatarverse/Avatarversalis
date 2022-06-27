package net.avatarverse.avatarversalis.core.event.user;

import net.avatarverse.avatarversalis.core.game.user.preset.Preset;
import net.avatarverse.avatarversalis.core.platform.event.Cancellable;

public interface UserPresetEvent extends UserEvent, Cancellable {
	Preset preset();
	Action action();
	UserPresetEvent call();

	enum Action {
		CREATE, DELETE, BIND
	}
}
