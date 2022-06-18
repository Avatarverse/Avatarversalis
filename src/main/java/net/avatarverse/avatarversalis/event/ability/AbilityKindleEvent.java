package net.avatarverse.avatarversalis.event.ability;

import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.user.User;

public class AbilityKindleEvent extends AbilityEvent {
	public AbilityKindleEvent(User user, AbilityInstance ability) {
		super(user, ability);
	}
}
