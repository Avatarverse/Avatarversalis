package net.avatarverse.avatarversalis.bukkit.event;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
public abstract class BendingEvent extends Event implements net.avatarverse.avatarversalis.core.event.Event {

	private static final HandlerList handlers = new HandlerList();

	public BendingEvent call() {
		Bukkit.getPluginManager().callEvent(this);
		return this;
	}

	@Override
	public @NonNull HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
