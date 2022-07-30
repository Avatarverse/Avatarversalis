package net.avatarverse.avatarversalis.core.game.ability;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import net.avatarverse.avatarversalis.core.game.Game;
import net.avatarverse.avatarversalis.core.game.user.User;

/**
 * Manages all registered instances of {@link Ability} and {@link AbilityInstance}
 */
public final class AbilityManager {

	static final Map<String, Ability> ABILITIES_BY_NAME = new HashMap<>();
	static final Map<Class<? extends AbilityInstance>, Ability> ABILITIES_BY_CLASS = new HashMap<>();
	static final Set<Ability> COMBOS = new HashSet<>();

	static final Set<AbilityInstance> INSTANCES = new HashSet<>();
	public static final Map<User, Set<AbilityInstance>> INSTANCES_BY_USER = new HashMap<>();

	public AbilityManager() {
		Game.plugin().scheduler().repeat(this::update);
	}

	public void update() {
		Iterator<AbilityInstance> iterator = INSTANCES.iterator();
		while (iterator.hasNext()) {
			AbilityInstance instance = iterator.next();
			if (!instance.update()) {
				iterator.remove();
				INSTANCES_BY_USER.get(instance.user).remove(instance);
				instance.cleanup();
			}
		}
	}

	public static void registerAbilities(Class<?> clazz, String packageName) {

	}

	public static Stream<Ability> all() {
		return ABILITIES_BY_NAME.values().stream();
	}
}
