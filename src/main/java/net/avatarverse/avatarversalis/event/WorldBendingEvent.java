package net.avatarverse.avatarversalis.event;

import org.bukkit.World;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class WorldBendingEvent extends BendingEvent {
	protected final World world;
}
