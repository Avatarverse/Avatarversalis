package net.avatarverse.avatarversalis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.avatarverse.avatarversalis.config.ConfigManager;
import net.avatarverse.avatarversalis.core.Game;
import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.core.ability.AbilityManager;
import net.avatarverse.avatarversalis.core.element.Element;
import net.avatarverse.avatarversalis.core.listener.UserListener;
import net.avatarverse.avatarversalis.core.user.AvatarPlayer;
import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.core.user.preset.Preset;
import net.avatarverse.avatarversalis.util.Listeners;

import co.aikar.commands.BukkitCommandCompletionContext;
import co.aikar.commands.BukkitCommandExecutionContext;
import co.aikar.commands.CommandCompletions;
import co.aikar.commands.CommandContexts;
import co.aikar.commands.InvalidCommandArgument;
import co.aikar.commands.PaperCommandManager;
import lombok.Getter;

public class Avatarversalis extends JavaPlugin {

	@Getter private static Avatarversalis plugin;
	@Getter private static Logger log;
	@Getter private static ConfigManager configManager;
	@Getter private static Game game;
	@Getter private static PaperCommandManager commandManager;
	private Set<Listener> listeners;

	@Override
	public void onEnable() {
		plugin = this;
		log = getLogger();

		configManager = new ConfigManager();
		game = new Game();

		commandManager = new PaperCommandManager(this);
		registerCommandCompletions();
		registerCommandContexts();

		listeners = Set.of(new UserListener());
		listeners.forEach(Listeners::register);

		log.info("Avatarversalis has successfully been enabled.");
	}

	@Override
	public void onDisable() {
		listeners.forEach(Listeners::unregister);
		log.info("Avatarversalis has successfully been disabled.");
	}

	private void registerCommandCompletions() {
		CommandCompletions<BukkitCommandCompletionContext> completions = commandManager.getCommandCompletions();
		completions.registerAsyncCompletion("users", c -> User.all().map(User::name).toList());
		completions.registerAsyncCompletion("elements", c -> Element.all().map(Element::name).toList());
		completions.registerAsyncCompletion("abilities", c -> {
			if (c.getSender() instanceof Player player) {
				User user = User.byEntity(player);
				if (user == null) return Collections.emptyList();
				return new ArrayList<>(AbilityManager.all().filter(a -> user.elements().containsKey(a.element())).map(Ability::name).toList());
			}
			return Collections.emptyList();
		});
		completions.registerAsyncCompletion("bindables", c -> {
			if (c.getSender() instanceof Player player) {
				User user = User.byEntity(player);
				if (user == null) return Collections.emptyList();
				return new ArrayList<>(AbilityManager.all().filter(a -> user.elements().containsKey(a.element()) && a.bindable()).map(Ability::name).toList());
			}
			return Collections.emptyList();
		});
		completions.registerAsyncCompletion("presets", c -> {
			if (c.getSender() instanceof Player player) {
				AvatarPlayer ap = AvatarPlayer.byPlayer(player);
				if (ap == null) return Collections.emptyList();
				return ap.presets().keySet().stream().toList();
			}
			return Collections.emptyList();
		});
	}

	private void registerCommandContexts() {
		CommandContexts<BukkitCommandExecutionContext> contexts = commandManager.getCommandContexts();
		contexts.registerIssuerAwareContext(AvatarPlayer.class, c -> {
			if (!(c.getSender() instanceof Player player)) return null;
			AvatarPlayer ap;
			if ("false".equalsIgnoreCase(c.getFlagValue("other", "false"))) {
				ap = AvatarPlayer.byPlayer(player);
			} else {
				User user = User.byName(c.popFirstArg());
				if (user == null) return null;
				ap = user.as(AvatarPlayer.class);
			}
			if (ap == null)
				throw new InvalidCommandArgument(); // TODO message
			return ap;
		});
		contexts.registerIssuerAwareContext(User.class, c -> {
			User user;
			if ("false".equalsIgnoreCase(c.getFlagValue("other", "false"))) {
				if (!(c.getSender() instanceof Player player)) return null;
				user = User.byEntity(player);
			} else {
				user = User.byName(c.popFirstArg());
			}
			if (user == null)
				throw new InvalidCommandArgument(); // TODO message
			return user;
		});
		contexts.registerContext(Element.class, c -> {
			String arg = c.popFirstArg();
			if (arg == null)
				throw new InvalidCommandArgument(); // TODO message
			Element element = Element.byName(arg);
			if (element == null)
				throw new InvalidCommandArgument(); // TODO message
			return element;
		});
		contexts.registerContext(Ability.class, c -> {
			String arg = c.popFirstArg();
			if (arg == null)
				throw new InvalidCommandArgument(); // TODO message
			Ability ability = Ability.byName(arg);
			if (ability == null)
				throw new InvalidCommandArgument(); // TODO message
			return ability;
		});
		contexts.registerIssuerAwareContext(Preset.class, c -> {
			if (!(c.getSender() instanceof Player player)) return null;
			AvatarPlayer ap = AvatarPlayer.byPlayer(player);
			if (ap == null)
				throw new InvalidCommandArgument(); // TODO message
			Preset preset = ap.presets().get(c.popFirstArg());
			if (preset == null)
				throw new InvalidCommandArgument(); // TODO message
			return preset;
		});
	}

}
