package net.avatarverse.avatarversalis.bukkit.event.world;

import net.avatarverse.avatarversalis.core.platform.World;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
public class WorldSunsetEvent extends WorldBendingEvent implements net.avatarverse.avatarversalis.core.event.world.WorldSunsetEvent {
	public WorldSunsetEvent(World world) {
		super(world);
	}
}
