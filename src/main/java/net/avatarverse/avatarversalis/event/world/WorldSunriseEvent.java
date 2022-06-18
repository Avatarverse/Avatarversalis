package net.avatarverse.avatarversalis.event.world;

import org.bukkit.World;

import lombok.Getter;

@Getter
public class WorldSunriseEvent extends WorldBendingEvent {
	public WorldSunriseEvent(World world) {
		super(world);
	}
}
