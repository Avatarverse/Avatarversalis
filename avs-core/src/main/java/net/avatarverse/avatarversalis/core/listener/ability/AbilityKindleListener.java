package net.avatarverse.avatarversalis.core.listener.ability;

import net.avatarverse.avatarversalis.core.event.ability.AbilityKindleEvent;
import net.avatarverse.avatarversalis.core.listener.Listener;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
@FunctionalInterface
public interface AbilityKindleListener extends Listener {
	void onAbilityKindle(AbilityKindleEvent event);
}
