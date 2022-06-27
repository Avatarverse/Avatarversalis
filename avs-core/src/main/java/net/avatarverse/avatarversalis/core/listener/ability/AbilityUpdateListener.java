package net.avatarverse.avatarversalis.core.listener.ability;

import net.avatarverse.avatarversalis.core.event.ability.AbilityUpdateEvent;
import net.avatarverse.avatarversalis.core.listener.Listener;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
@FunctionalInterface
public interface AbilityUpdateListener extends Listener {
	void onAbilityUpdate(AbilityUpdateEvent event);
}
