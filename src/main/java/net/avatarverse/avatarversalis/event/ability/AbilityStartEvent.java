package net.avatarverse.avatarversalis.event.ability;

import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.user.User;

public class AbilityStartEvent extends AbilityEvent {
	public AbilityStartEvent(User user, AbilityInstance ability) {
		super(user, ability);
	}
}
