package net.avatarverse.avatarversalis.event;

import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.user.User;

public class AbilityEndEvent extends AbilityEvent {
	public AbilityEndEvent(User user, AbilityInstance ability) {
		super(user, ability);
	}
}
