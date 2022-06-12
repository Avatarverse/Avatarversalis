package net.avatarverse.avatarversalis;

import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.avatarverse.avatarversalis.config.ConfigManager;
import net.avatarverse.avatarversalis.core.Game;
import net.avatarverse.avatarversalis.core.listener.UserListener;
import net.avatarverse.avatarversalis.util.Listeners;

import lombok.Getter;

public class Avatarversalis extends JavaPlugin {

	@Getter private static Avatarversalis plugin;
	@Getter private static Logger log;
	@Getter private static ConfigManager configManager;
	@Getter private static Game game;
	private Set<Listener> listeners;

	@Override
	public void onEnable() {
		plugin = this;
		log = getLogger();

		configManager = new ConfigManager();
		game = new Game();

		listeners = Set.of(new UserListener());
		listeners.forEach(Listeners::register);

		log.info("Avatarversalis has successfully been enabled.");
	}

	@Override
	public void onDisable() {
		listeners.forEach(Listeners::unregister);
		log.info("Avatarversalis has successfully been disabled.");
	}

}
