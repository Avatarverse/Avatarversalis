package net.avatarverse.avatarversalis.core.listener.user;

import net.avatarverse.avatarversalis.core.event.user.UserCooldownEvent;
import net.avatarverse.avatarversalis.core.listener.Listener;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
@FunctionalInterface
public interface UserCooldownListener extends Listener {
	void onUserCooldown(UserCooldownEvent event);
}
