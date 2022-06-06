package net.avatarverse.avatarversalis.event;

import org.bukkit.World;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class WorldSunsetEvent extends BendingEvent {
	private final World world;
}
