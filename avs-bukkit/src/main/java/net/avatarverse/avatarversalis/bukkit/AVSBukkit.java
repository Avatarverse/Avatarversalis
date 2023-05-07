package net.avatarverse.avatarversalis.bukkit;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.avatarverse.avatarversalis.bukkit.impl.BukkitController;
import net.avatarverse.avatarversalis.bukkit.impl.BukkitEventBus;
import net.avatarverse.avatarversalis.bukkit.impl.BukkitScheduler;
import net.avatarverse.avatarversalis.bukkit.impl.BukkitUserListener;
import net.avatarverse.avatarversalis.core.Avatarversalis;
import net.avatarverse.avatarversalis.core.config.ConfigManager;
import net.avatarverse.avatarversalis.core.game.Controller;
import net.avatarverse.avatarversalis.core.game.EventBus;
import net.avatarverse.avatarversalis.core.game.Game;
import net.avatarverse.avatarversalis.core.game.Scheduler;
import net.avatarverse.avatarversalis.core.platform.World;
import net.avatarverse.avatarversalis.core.platform.scoreboard.Scoreboard;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

@DefaultAnnotation(NonNull.class)
public class AVSBukkit extends JavaPlugin implements Avatarversalis {

	public static AVSBukkit plugin;

	private Logger log;
	private String version;
	private ConfigManager configManager;
	private Game game;
//	private PaperCommandManager commandManager;
	private BukkitController controller;
	private BukkitEventBus eventBus;
	private BukkitScheduler scheduler;

	private Set<Listener> listeners;

	@Override
	public void onEnable() {
		plugin = this;
		log = getLogger();
		version = getDescription().getVersion();

		configManager = new ConfigManager();
		game = new Game(this);
		controller = new BukkitController();
		eventBus = new BukkitEventBus();
		scheduler = new BukkitScheduler();

//		commandManager = new PaperCommandManager(this);
//		registerCommandCompletions();
//		registerCommandContexts();
//		commandManager.registerCommand(new BendingCommand());

		listeners = Set.of(
				controller,
				new BukkitUserListener()
		);
		listeners.forEach(l -> Bukkit.getPluginManager().registerEvents(l, this));
	}

	@Override
	public void onDisable() {
		listeners.forEach(HandlerList::unregisterAll);
	}

	@Override
	public Logger log() {
		return log;
	}

	@Override
	public String version() {
		return version;
	}

	@Override
	public ConfigManager configManager() {
		return configManager;
	}

	@Override
	public Game game() {
		return game;
	}

//	@Override
//	public PaperCommandManager commandManager() {
//		return commandManager;
//	}

	@Override
	public Controller controller() {
		return controller;
	}

	@Override
	public EventBus eventBus() {
		return eventBus;
	}

	@Override
	public Scheduler scheduler() {
		return scheduler;
	}

	@Override
	public Scoreboard mainScoreboard() {
		return new net.avatarverse.avatarversalis.bukkit.platform.scoreboard.Scoreboard(Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard());
	}

	@Override
	public Scoreboard newScoreboard() {
		return new net.avatarverse.avatarversalis.bukkit.platform.scoreboard.Scoreboard(Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard());
	}

	@Override
	public @Nullable World world(UUID uid) {
		return new net.avatarverse.avatarversalis.bukkit.platform.World(Bukkit.getWorld(uid));
	}

	@Override
	public @Nullable World world(String name) {
		return new net.avatarverse.avatarversalis.bukkit.platform.World(Bukkit.getWorld(name));
	}

	@Override
	public void reloadConfig() {
		configManager = new ConfigManager();
	}

//	private void registerCommandCompletions() {
//		CommandCompletions<BukkitCommandCompletionContext> completions = commandManager.getCommandCompletions();
//		completions.registerAsyncCompletion("users", c -> User.all().map(User::name).toList());
//		completions.registerAsyncCompletion("elements", c -> Element.all().map(Element::name).toList());
//		completions.registerAsyncCompletion("attributes", c -> Arrays.stream(Attribute.values()).map(a -> a.toString().toLowerCase()).toList());
//		completions.registerAsyncCompletion("operations", c -> Arrays.stream(ModifierOperation.values()).map(a -> a.toString().toLowerCase()).toList());
//		completions.registerAsyncCompletion("abilities", c -> {
//			if (c.getSender() instanceof Player player) {
//				User user = EntityUser.of(player);
//				if (user == null) return Collections.emptyList();
//				return new ArrayList<>(AbilityManager.all().filter(a -> user.elements().contains(a.element())).map(Ability::name).toList());
//			}
//			return Collections.emptyList();
//		});
//		completions.registerAsyncCompletion("bindables", c -> {
//			if (c.getSender() instanceof Player player) {
//				User user = EntityUser.of(player);
//				if (user == null) return Collections.emptyList();
//				return new ArrayList<>(AbilityManager.all().filter(a -> user.elements().contains(a.element()) && a.bindable()).map(Ability::name).toList());
//			}
//			return Collections.emptyList();
//		});
//		completions.registerAsyncCompletion("presets", c -> {
//			if (c.getSender() instanceof Player player) {
//				AvatarPlayer ap = AvatarPlayer.of(player);
//				if (ap == null) return Collections.emptyList();
//				return ap.presets().keySet().stream().toList();
//			}
//			return Collections.emptyList();
//		});
//		completions.registerAsyncCompletion("elements-users", c -> {
//			List<String> list = new ArrayList<>();
//			list.addAll(Element.all().map(e -> "element:" + e.name()).toList());
//			list.addAll(User.all().map(u -> "user:" + u.name()).toList());
//			return list;
//		});
//		completions.registerAsyncCompletion("elements-abilities", c -> {
//			List<String> list = new ArrayList<>();
//			list.addAll(Element.all().map(Element::name).toList());
//			list.addAll(AbilityManager.all().map(Ability::name).toList());
//			return list;
//		});
//	}
//
//	private void registerCommandContexts() {
//		CommandContexts<BukkitCommandExecutionContext> contexts = commandManager.getCommandContexts();
//		contexts.registerIssuerAwareContext(AvatarPlayer.class, c -> {
//			if (!(c.getSender() instanceof Player player)) return null;
//			AvatarPlayer ap;
//			if ("false".equalsIgnoreCase(c.getFlagValue("other", "false"))) {
//				ap = AvatarPlayer.of(player);
//			} else {
//				User user = User.byName(c.popFirstArg());
//				if (user == null) return null;
//				ap = user.as(AvatarPlayer.class);
//			}
//			if (ap == null)
//				throw new InvalidCommandArgument(); // TODO message
//			return ap;
//		});
//		contexts.registerIssuerAwareContext(User.class, c -> {
//			User user;
//			if ("false".equalsIgnoreCase(c.getFlagValue("other", "false"))) {
//				if (!(c.getSender() instanceof Player player)) return null;
//				user = EntityUser.of(player);
//			} else {
//				user = User.byName(c.popFirstArg());
//			}
//			if (user == null)
//				throw new InvalidCommandArgument(); // TODO message
//			return user;
//		});
//		contexts.registerContext(Element.class, c -> {
//			String arg = c.popFirstArg();
//			if (arg == null)
//				throw new InvalidCommandArgument(); // TODO message
//			Element element = Element.byNameIgnoreCase(arg);
//			if (element == null)
//				throw new InvalidCommandArgument(); // TODO message
//			return element;
//		});
//		contexts.registerContext(Ability.class, c -> {
//			String arg = c.popFirstArg();
//			if (arg == null)
//				throw new InvalidCommandArgument(); // TODO message
//			Ability ability = Ability.byNameIgnoreCase(arg);
//			if (ability == null)
//				throw new InvalidCommandArgument(); // TODO message
//			return ability;
//		});
//		contexts.registerIssuerAwareContext(Preset.class, c -> {
//			if (!(c.getSender() instanceof Player player)) return null;
//			AvatarPlayer ap = AvatarPlayer.of(player);
//			if (ap == null)
//				throw new InvalidCommandArgument(); // TODO message
//			Preset preset = ap.presets().get(c.popFirstArg());
//			if (preset == null)
//				throw new InvalidCommandArgument(); // TODO message
//			return preset;
//		});
//		contexts.registerContext(Equivoque.class, c ->
//				new Equivoque<>("element:", Element::byNameIgnoreCase, "user:", User::byName, c.popFirstArg())
//		);
//		contexts.registerContext(Attribute.class, c -> {
//			try {
//				return Attribute.valueOf(c.popFirstArg());
//			} catch (IllegalArgumentException e) {
//				throw new InvalidCommandArgument(); // TODO
//			}
//		});
//	}
}
