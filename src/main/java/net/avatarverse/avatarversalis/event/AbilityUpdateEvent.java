package net.avatarverse.avatarversalis.event;

import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.user.User;

public class AbilityUpdateEvent extends AbilityEvent {
	public AbilityUpdateEvent(User user, AbilityInstance ability) {
		super(user, ability);
	}
}
