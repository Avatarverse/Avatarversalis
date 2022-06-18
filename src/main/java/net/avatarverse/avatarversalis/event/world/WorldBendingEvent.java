package net.avatarverse.avatarversalis.event.world;

import org.bukkit.World;

import net.avatarverse.avatarversalis.event.BendingEvent;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class WorldBendingEvent extends BendingEvent {
	protected final World world;
}
