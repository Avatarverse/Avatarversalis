package net.avatarverse.avatarversalis;

import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import net.avatarverse.avatarversalis.core.Game;
import net.avatarverse.avatarversalis.core.listener.UserListener;

public class Avatarversalis extends JavaPlugin {

	public static Avatarversalis plugin;
	public static Logger log;
	public static Game game;
	private Set<Listener> listeners;

	@Override
	public void onEnable() {
		plugin = this;
		log = getLogger();
		game = new Game();

		listeners = Set.of(new UserListener());
		listeners.forEach(l -> getServer().getPluginManager().registerEvents(l, this));

		log.info("Avatarversalis has successfully been enabled.");
	}

	@Override
	public void onDisable() {
		listeners.forEach(HandlerList::unregisterAll);
		log.info("Avatarversalis has successfully been disabled.");
	}

}
