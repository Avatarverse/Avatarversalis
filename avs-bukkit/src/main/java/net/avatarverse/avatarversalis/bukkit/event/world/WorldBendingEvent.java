package net.avatarverse.avatarversalis.bukkit.event.world;

import net.avatarverse.avatarversalis.bukkit.event.BendingEvent;
import net.avatarverse.avatarversalis.core.platform.World;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@DefaultAnnotation(NonNull.class)
@RequiredArgsConstructor
@Getter
public abstract class WorldBendingEvent extends BendingEvent implements net.avatarverse.avatarversalis.core.event.world.WorldBendingEvent {
	protected final World world;
}
