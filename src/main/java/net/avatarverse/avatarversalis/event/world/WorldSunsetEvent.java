package net.avatarverse.avatarversalis.event.world;

import org.bukkit.World;

import lombok.Getter;

@Getter
public class WorldSunsetEvent extends WorldBendingEvent {
	public WorldSunsetEvent(World world) {
		super(world);
	}
}
