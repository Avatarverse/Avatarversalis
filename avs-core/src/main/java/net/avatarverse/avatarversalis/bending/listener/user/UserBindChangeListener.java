package net.avatarverse.avatarversalis.bending.listener.user;

import net.avatarverse.avatarversalis.core.event.user.UserBindChangeEvent;
import net.avatarverse.avatarversalis.bending.listener.Listener;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
@FunctionalInterface
public interface UserBindChangeListener extends Listener {
	void onUserBindChange(UserBindChangeEvent event);
}
