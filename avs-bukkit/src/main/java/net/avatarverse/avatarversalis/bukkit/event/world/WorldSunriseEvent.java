package net.avatarverse.avatarversalis.bukkit.event.world;

import net.avatarverse.avatarversalis.core.platform.World;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
public class WorldSunriseEvent extends WorldBendingEvent implements net.avatarverse.avatarversalis.core.event.world.WorldSunriseEvent {
	public WorldSunriseEvent(World world) {
		super(world);
	}
}
