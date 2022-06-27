package net.avatarverse.avatarversalis.bending.listener.world;

import net.avatarverse.avatarversalis.core.event.world.WorldSunriseEvent;
import net.avatarverse.avatarversalis.bending.listener.Listener;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
@FunctionalInterface
public interface WorldSunriseListener extends Listener {
	void onWorldSunrise(WorldSunriseEvent event);
}
