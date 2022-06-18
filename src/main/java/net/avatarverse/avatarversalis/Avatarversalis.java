package net.avatarverse.avatarversalis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.spongepowered.configurate.ConfigurationNode;

import net.avatarverse.avatarversalis.config.ConfigManager;
import net.avatarverse.avatarversalis.core.Game;
import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.core.ability.AbilityManager;
import net.avatarverse.avatarversalis.core.attribute.Attribute;
import net.avatarverse.avatarversalis.core.attribute.ModifierOperation;
import net.avatarverse.avatarversalis.core.element.Element;
import net.avatarverse.avatarversalis.core.user.AvatarPlayer;
import net.avatarverse.avatarversalis.core.user.EntityUser;
import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.core.user.preset.Preset;
import net.avatarverse.avatarversalis.listener.UserListener;
import net.avatarverse.avatarversalis.util.Equivoque;
import net.avatarverse.avatarversalis.util.Listeners;

import co.aikar.commands.BukkitCommandCompletionContext;
import co.aikar.commands.BukkitCommandExecutionContext;
import co.aikar.commands.CommandCompletions;
import co.aikar.commands.CommandContexts;
import co.aikar.commands.InvalidCommandArgument;
import co.aikar.commands.PaperCommandManager;
import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
public class Avatarversalis extends JavaPlugin {

	@Getter private static Avatarversalis plugin;
	@Getter private static Logger log;
	@Getter private static String version;
	@Getter private static ConfigManager configManager;
	@Getter private static Game game;
	@Getter private static PaperCommandManager commandManager;
	private Set<Listener> listeners;

	@Override
	public void onEnable() {
		plugin = this;
		log = getLogger();
		version = getDescription().getVersion();

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
		completions.registerAsyncCompletion("attributes", c -> Arrays.stream(Attribute.values()).map(a -> a.toString().toLowerCase()).toList());
		completions.registerAsyncCompletion("operations", c -> Arrays.stream(ModifierOperation.values()).map(a -> a.toString().toLowerCase()).toList());
		completions.registerAsyncCompletion("abilities", c -> {
			if (c.getSender() instanceof Player player) {
				User user = EntityUser.of(player);
				if (user == null) return Collections.emptyList();
				return new ArrayList<>(AbilityManager.all().filter(a -> user.elements().contains(a.element())).map(Ability::name).toList());
			}
			return Collections.emptyList();
		});
		completions.registerAsyncCompletion("bindables", c -> {
			if (c.getSender() instanceof Player player) {
				User user = EntityUser.of(player);
				if (user == null) return Collections.emptyList();
				return new ArrayList<>(AbilityManager.all().filter(a -> user.elements().contains(a.element()) && a.bindable()).map(Ability::name).toList());
			}
			return Collections.emptyList();
		});
		completions.registerAsyncCompletion("presets", c -> {
			if (c.getSender() instanceof Player player) {
				AvatarPlayer ap = AvatarPlayer.of(player);
				if (ap == null) return Collections.emptyList();
				return ap.presets().keySet().stream().toList();
			}
			return Collections.emptyList();
		});
		completions.registerAsyncCompletion("elements-users", c -> {
			List<String> list = new ArrayList<>();
			list.addAll(Element.all().map(e -> "element:" + e.name()).toList());
			list.addAll(User.all().map(u -> "user:" + u.name()).toList());
			return list;
		});
		completions.registerAsyncCompletion("elements-abilities", c -> {
			List<String> list = new ArrayList<>();
			list.addAll(Element.all().map(Element::name).toList());
			list.addAll(AbilityManager.all().map(Ability::name).toList());
			return list;
		});
	}

	private void registerCommandContexts() {
		CommandContexts<BukkitCommandExecutionContext> contexts = commandManager.getCommandContexts();
		contexts.registerIssuerAwareContext(AvatarPlayer.class, c -> {
			if (!(c.getSender() instanceof Player player)) return null;
			AvatarPlayer ap;
			if ("false".equalsIgnoreCase(c.getFlagValue("other", "false"))) {
				ap = AvatarPlayer.of(player);
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
				user = EntityUser.of(player);
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
			Element element = Element.byNameIgnoreCase(arg);
			if (element == null)
				throw new InvalidCommandArgument(); // TODO message
			return element;
		});
		contexts.registerContext(Ability.class, c -> {
			String arg = c.popFirstArg();
			if (arg == null)
				throw new InvalidCommandArgument(); // TODO message
			Ability ability = Ability.byNameIgnoreCase(arg);
			if (ability == null)
				throw new InvalidCommandArgument(); // TODO message
			return ability;
		});
		contexts.registerIssuerAwareContext(Preset.class, c -> {
			if (!(c.getSender() instanceof Player player)) return null;
			AvatarPlayer ap = AvatarPlayer.of(player);
			if (ap == null)
				throw new InvalidCommandArgument(); // TODO message
			Preset preset = ap.presets().get(c.popFirstArg());
			if (preset == null)
				throw new InvalidCommandArgument(); // TODO message
			return preset;
		});
		contexts.registerContext(Equivoque.class, c ->
				new Equivoque<>("element:", Element::byNameIgnoreCase, "user:", User::byName, c.popFirstArg())
		);
		contexts.registerContext(Attribute.class, c -> {
			try {
				return Attribute.valueOf(c.popFirstArg());
			} catch (IllegalArgumentException e) {
				throw new InvalidCommandArgument(); // TODO
			}
		});
	}

	@Override
	public void reloadConfig() {
		configManager = new ConfigManager();
	}

	public static ConfigurationNode config() {
		return configManager.config();
	}

}
