package net.avatarverse.avatarversalis.event;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import net.avatarverse.avatarversalis.Avatarversalis;

import edu.umd.cs.findbugs.annotations.ReturnValuesAreNonnullByDefault;

@ParametersAreNonnullByDefault
@ReturnValuesAreNonnullByDefault
public abstract class BendingEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	public BendingEvent call() {
		Avatarversalis.plugin().getServer().getPluginManager().callEvent(this);
		return this;
	}

	@Override
	public @NotNull HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
