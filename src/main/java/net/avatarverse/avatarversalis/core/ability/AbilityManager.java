package net.avatarverse.avatarversalis.core.ability;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.bukkit.plugin.Plugin;

import net.avatarverse.avatarversalis.ability.fire.FireBlast;
import net.avatarverse.avatarversalis.ability.fire.FireBlastCharged;
import net.avatarverse.avatarversalis.ability.fire.Illumination;
import net.avatarverse.avatarversalis.ability.fire.combo.FireBlade;
import net.avatarverse.avatarversalis.core.element.Element;
import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.util.Scheduler;

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
		registerCoreAbilities();
		registerAddonAbilities();

		Scheduler.repeat(this::update, 1);
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

	private void registerCoreAbilities() {
		// FIRE

		// FireBlast
		Ability.builder("FireBlast", Element.FIRE)
				.activation(Activation.ATTACK, FireBlast.class)
				.activation(Activation.SNEAK, FireBlastCharged.class)
				.control(FireBlastCharged.class, Activation.SNEAK_RELEASE)
				.bindable().build();
		// Illumination
		Ability.builder("Illumination", Element.FIRE)
				.activation(Activation.ATTACK, Illumination.class)
				.control(Illumination.class, Activation.ATTACK)
				.bindable().build();
		// FireBlade
		Ability.builder("FireBlade", Element.FIRE)
				.combo(FireBlade.class,
						new ComboStep("FireBlast", Activation.ATTACK),
						new ComboStep("FireBlast", Activation.ATTACK),
						new ComboStep("FireBlast", Activation.SNEAK),
						new ComboStep("FireBlast", Activation.ATTACK)).build();
	}

	private void registerAddonAbilities() {

	}

	/**
	 * Registers a plugin's addon abilities (classes that extend {@link AbilityInstance}) from a package
	 * @param plugin the {@link Plugin} to register
	 * @param packageName the fully qualified package name containing {@link AbilityInstance} classes
	 */
	public static void registerPluginAbilities(Plugin plugin, String packageName) {

	}
}
