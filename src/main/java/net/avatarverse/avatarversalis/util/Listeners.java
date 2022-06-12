package net.avatarverse.avatarversalis.util;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import net.avatarverse.avatarversalis.Avatarversalis;

@ParametersAreNonnullByDefault
public final class Listeners {

	public static void register(Listener listener) {
		Avatarversalis.plugin().getServer().getPluginManager().registerEvents(listener, Avatarversalis.plugin());
	}

	public static void unregister(Listener listener) {
		HandlerList.unregisterAll(listener);
	}

}
