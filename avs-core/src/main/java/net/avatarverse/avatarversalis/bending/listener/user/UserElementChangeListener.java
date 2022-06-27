package net.avatarverse.avatarversalis.bending.listener.user;

import net.avatarverse.avatarversalis.core.event.user.UserElementChangeEvent;
import net.avatarverse.avatarversalis.bending.listener.Listener;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
@FunctionalInterface
public interface UserElementChangeListener extends Listener {
	void onUserElementChange(UserElementChangeEvent event);
}
