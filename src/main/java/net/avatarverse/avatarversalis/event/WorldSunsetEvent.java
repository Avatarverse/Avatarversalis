package net.avatarverse.avatarversalis.event;

import org.bukkit.World;

import lombok.Getter;

@Getter
public class WorldSunsetEvent extends WorldBendingEvent {
	public WorldSunsetEvent(World world) {
		super(world);
	}
}
