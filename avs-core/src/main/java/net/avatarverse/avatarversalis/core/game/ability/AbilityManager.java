package net.avatarverse.avatarversalis.core.game.ability;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.reflections.Reflections;

import net.avatarverse.avatarversalis.core.game.Game;
import net.avatarverse.avatarversalis.core.game.element.Element;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.util.text.Lang;

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
			instance.tick++;
			if (!instance.update()) {
				iterator.remove();
				INSTANCES_BY_USER.get(instance.user).remove(instance);
				instance.cleanup();
			}
		}
	}

	/**
	 * Registers abilities in the package corresponding to the given package name.
	 * Only classes that extend AbilityInstance are loaded and registered.
	 * All classes that extend AbilityInstance must contain a static method called <code>register</code>.
	 * @param packageName the fully qualified package name to read
	 * @see AbilityInstance
	 * @see Ability#builder(String, Element)
	 */
	public static void registerAbilities(String packageName) {
		Set<Class<? extends AbilityInstance>> abilityClasses = new Reflections(packageName).getSubTypesOf(AbilityInstance.class);

		for (Class<? extends AbilityInstance> ability : abilityClasses) {
			try {
				Method registerMethod = ability.getMethod("register");
				registerMethod.setAccessible(true);
				if (!Modifier.isStatic(registerMethod.getModifiers()))
					throw new NoSuchMethodException();
				registerMethod.invoke(null);
			} catch (NoSuchMethodException e) {
				Lang.warn(Lang.ABILITY_MANAGER_REGISTER_METHOD_NOT_FOUND, ability.getName());
			} catch (InvocationTargetException | IllegalAccessException e) {
				Lang.warn(Lang.ABILITY_MANAGER_REGISTER_INVOKE_ERROR, ability.getName(), e.getLocalizedMessage());
			}
		}
	}

	public static Stream<Ability> all() {
		return ABILITIES_BY_NAME.values().stream();
	}
}
