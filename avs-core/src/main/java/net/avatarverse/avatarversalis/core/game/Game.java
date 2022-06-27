package net.avatarverse.avatarversalis.core.game;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import net.avatarverse.avatarversalis.core.Avatarversalis;
import net.avatarverse.avatarversalis.core.config.ConfigManager;
import net.avatarverse.avatarversalis.core.game.ability.AbilityManager;
import net.avatarverse.avatarversalis.core.game.ability.ComboManager;
import net.avatarverse.avatarversalis.core.game.board.BendingBoardManager;
import net.avatarverse.avatarversalis.core.game.temporary.CooldownManager;
import net.avatarverse.avatarversalis.core.listener.Listener;
import net.avatarverse.avatarversalis.core.listener.ability.AbilityAffectEntityListener;
import net.avatarverse.avatarversalis.core.listener.ability.AbilityConfigLoadListener;
import net.avatarverse.avatarversalis.core.listener.ability.AbilityEndListener;
import net.avatarverse.avatarversalis.core.listener.ability.AbilityKindleListener;
import net.avatarverse.avatarversalis.core.listener.ability.AbilityStartListener;
import net.avatarverse.avatarversalis.core.listener.ability.AbilityUpdateListener;
import net.avatarverse.avatarversalis.core.listener.user.UserBindChangeListener;
import net.avatarverse.avatarversalis.core.listener.user.UserBindCopyListener;
import net.avatarverse.avatarversalis.core.listener.user.UserCooldownListener;
import net.avatarverse.avatarversalis.core.listener.user.UserElementChangeListener;
import net.avatarverse.avatarversalis.core.listener.user.UserLoadListener;
import net.avatarverse.avatarversalis.core.listener.user.UserPermaremoveListener;
import net.avatarverse.avatarversalis.core.listener.user.UserPresetListener;
import net.avatarverse.avatarversalis.core.listener.world.WorldSunriseListener;
import net.avatarverse.avatarversalis.core.listener.world.WorldSunsetListener;
import net.avatarverse.avatarversalis.core.platform.World;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Game {

	private static Game instance;

	private final Avatarversalis plugin;
	private final ConfigManager configManager;
	private final AbilityManager abilityManager;
	private final ComboManager comboManager;
	private final CooldownManager cooldownManager;
	private final BendingBoardManager boardManager;

	private final Map<Class<?>, Set<Listener>> listeners = Map.ofEntries(
			Map.entry(AbilityAffectEntityListener.class, new HashSet<>()),
			Map.entry(AbilityConfigLoadListener.class, new HashSet<>()),
			Map.entry(AbilityEndListener.class, new HashSet<>()),
			Map.entry(AbilityKindleListener.class, new HashSet<>()),
			Map.entry(AbilityStartListener.class, new HashSet<>()),
			Map.entry(AbilityUpdateListener.class, new HashSet<>()),
			Map.entry(UserBindChangeListener.class, new HashSet<>()),
			Map.entry(UserBindCopyListener.class, new HashSet<>()),
			Map.entry(UserCooldownListener.class, new HashSet<>()),
			Map.entry(UserElementChangeListener.class, new HashSet<>()),
			Map.entry(UserLoadListener.class, new HashSet<>()),
			Map.entry(UserPermaremoveListener.class, new HashSet<>()),
			Map.entry(UserPresetListener.class, new HashSet<>())
	);
	private final Set<World> disabledWorlds;

	public Game(Avatarversalis plugin) {
		Game.instance = this;
		this.plugin = plugin;

		configManager = new ConfigManager();

		abilityManager = new AbilityManager();
		comboManager = new ComboManager();
		cooldownManager = new CooldownManager();
		boardManager = new BendingBoardManager();

		List<String> worlds = null;
		try {
			worlds = plugin.configManager().config().node("properties", "disabled-worlds").getList(String.class);
		} catch (SerializationException e) {
			// TODO Lang error
		}
		disabledWorlds = worlds != null ? worlds.stream().map(plugin::world).filter(Objects::nonNull).collect(Collectors.toSet()) : Collections.emptySet();

		plugin.scheduler().repeat(this::update);
	}

	private void update() {

	}

	public void addListener(Class<? extends Listener> listenerClass, Listener listener) {
		listeners.get(listenerClass).add(listener);
	}

	public static Avatarversalis plugin() { return instance.plugin; }
	public static ConfigManager configManager() { return instance.configManager; }
	public static CommentedConfigurationNode config() { return configManager().config(); }
	public static AbilityManager abilityManager() { return instance.abilityManager; }
	public static ComboManager comboManager() { return instance.comboManager; }
	public static CooldownManager cooldownManager() { return instance.cooldownManager; }
	public static BendingBoardManager boardManager() { return instance.boardManager; }
	public static Controller controller() { return instance.plugin.controller(); }
	public static EventBus eventBus() { return instance.plugin.eventBus(); }

	public static void addAbilityAffectEntityListener(AbilityAffectEntityListener listener) { instance.addListener(AbilityAffectEntityListener.class, listener); }
	public static void addAbilityConfigLoadListener(AbilityConfigLoadListener listener) { instance.addListener(AbilityConfigLoadListener.class, listener); }
	public static void addAbilityEndEvent(AbilityEndListener listener) { instance.addListener(AbilityEndListener.class, listener); }
	public static void addAbilityKindleEvent(AbilityKindleListener listener) { instance.addListener(AbilityKindleListener.class, listener); }
	public static void addAbilityStartListener(AbilityStartListener listener) { instance.addListener(AbilityStartListener.class, listener); }
	public static void addAbilityUpdateListener(AbilityUpdateListener listener) { instance.addListener(AbilityUpdateListener.class, listener); }
	public static void addUserBindChangeListener(UserBindChangeListener listener) { instance.addListener(UserBindChangeListener.class, listener); }
	public static void addUserBindCopyListener(UserBindCopyListener listener) { instance.addListener(UserBindCopyListener.class, listener); }
	public static void addUserCooldownListener(UserCooldownListener listener) { instance.addListener(UserCooldownListener.class, listener); }
	public static void addUserElementChangeListener(UserElementChangeListener listener) { instance.addListener(UserElementChangeListener.class, listener); }
	public static void addUserLoadListener(UserLoadListener listener) { instance.addListener(UserLoadListener.class, listener); }
	public static void addUserPermaremoveListener(UserPermaremoveListener listener) { instance.addListener(UserPermaremoveListener.class, listener); }
	public static void addUserPresetListener(UserPresetListener listener) { instance.addListener(UserPresetListener.class, listener); }
	public static void addWorldSunriseListener(WorldSunriseListener listener) { instance.addListener(WorldSunriseListener.class, listener); }
	public static void addWorldSunsetListener(WorldSunsetListener listener) { instance.addListener(WorldSunsetListener.class, listener); }

	static <T extends Listener> Set<T> listeners(Class<T> listenerClass) {
		return instance.listeners.get(listenerClass).stream().map(listenerClass::cast).collect(Collectors.toSet());
	}

}
