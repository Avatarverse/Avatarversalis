package net.avatarverse.avatarversalis.core.event.ability;

import net.avatarverse.avatarversalis.core.event.user.UserEvent;
import net.avatarverse.avatarversalis.core.game.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.platform.event.Cancellable;

public interface AbilityEvent extends UserEvent, Cancellable {
	AbilityInstance ability();
	AbilityEvent call();
}
