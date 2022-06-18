package net.avatarverse.avatarversalis.util;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import net.avatarverse.avatarversalis.Avatarversalis;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
public final class Listeners {

	public static void register(Listener listener) {
		Avatarversalis.plugin().getServer().getPluginManager().registerEvents(listener, Avatarversalis.plugin());
	}

	public static void unregister(Listener listener) {
		HandlerList.unregisterAll(listener);
	}

}
