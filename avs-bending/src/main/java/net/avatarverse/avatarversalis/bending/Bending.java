package net.avatarverse.avatarversalis.bending;

import net.avatarverse.avatarversalis.bending.ability.fire.FireBlast;
import net.avatarverse.avatarversalis.bending.ability.fire.FireBlastCharged;
import net.avatarverse.avatarversalis.bending.ability.fire.Illumination;
import net.avatarverse.avatarversalis.bending.ability.fire.combo.FireBlade;
import net.avatarverse.avatarversalis.core.game.Game;
import net.avatarverse.avatarversalis.core.game.ability.Ability;
import net.avatarverse.avatarversalis.core.game.ability.Activation;
import net.avatarverse.avatarversalis.core.game.ability.ComboStep;
import net.avatarverse.avatarversalis.core.game.element.Element;
import net.avatarverse.avatarversalis.core.game.element.ElementDisplay;
import net.avatarverse.avatarversalis.bending.listener.AbilityListener;
import net.avatarverse.avatarversalis.core.platform.ChatColor;
import net.avatarverse.avatarversalis.core.util.text.Colors;

import static net.avatarverse.avatarversalis.core.game.element.Element.register;

public class Bending {

	public static final Element WATER = register(new ElementDisplay("Water", Colors.WATER));
	public static final Element EARTH = register(new ElementDisplay("Earth", Colors.EARTH));
	public static final Element FIRE = register(new ElementDisplay("Fire", Colors.FIRE));
	public static final Element AIR = register(new ElementDisplay("Air", Colors.AIR));
	public static final Element NON = register(new ElementDisplay("Non", Colors.NON));

	public static final Element BLOOD = register(new ElementDisplay("Blood", ChatColor.DARK_AQUA), WATER);
	public static final Element HEALING = register(new ElementDisplay("Healing", "Heal", "Healer", ChatColor.DARK_AQUA), WATER);
	public static final Element PLANT = register(new ElementDisplay("Plant", ChatColor.DARK_AQUA), WATER);

	public static final Element LAVA = register(new ElementDisplay("Lava", ChatColor.DARK_GREEN), EARTH);
	public static final Element METAL = register(new ElementDisplay("Metal", ChatColor.DARK_GREEN), EARTH);
	public static final Element SAND = register(new ElementDisplay("Sand", ChatColor.DARK_GREEN), EARTH);

	public static final Element COMBUSTION = register(new ElementDisplay("Combustion", ChatColor.DARK_RED), FIRE);
	public static final Element LIGHTNING = register(new ElementDisplay("Lightning", ChatColor.DARK_RED), FIRE);
	public static final Element BLUE_FIRE = register(new ElementDisplay("Blue Fire", ChatColor.DARK_RED), FIRE);

	public static final Element FLIGHT = register(new ElementDisplay("Flight", ChatColor.DARK_GRAY), AIR);
	public static final Element SPIRITUAL = register(new ElementDisplay("Spiritual", ChatColor.DARK_GRAY), AIR);

	public static final Element CHI = register(new ElementDisplay("Chiblocking", "Chiblock", "Chiblocker", ChatColor.GOLD), NON);
	public static final Element WARRIOR = register(new ElementDisplay("Warrior", "Warrior", "Warrior", ChatColor.GOLD), NON);
	public static final Element ARCHER = register(new ElementDisplay("Archer", "Archer", "Archer", ChatColor.GOLD), NON);

	public static final Element MUD = register(new ElementDisplay("Mud", ChatColor.of("#788fff")), WATER, EARTH);

	public void init() {
		Game.addAbilityConfigLoadListener(new AbilityListener());
	}

	public void registerBendingAbilities() {
		// FIRE

		// FireBlast
		Ability.builder("FireBlast", FIRE)
				.activation(Activation.ATTACK, FireBlast.class)
				.activation(Activation.SNEAK, FireBlastCharged.class)
				.control(FireBlastCharged.class, Activation.SNEAK_RELEASE)
				.bindable().build();
		// Illumination
		Ability.builder("Illumination", FIRE)
				.activation(Activation.ATTACK, Illumination.class)
				.control(Illumination.class, Activation.ATTACK)
				.bindable().build();
		// FireBlade
		Ability.builder("FireBlade", FIRE)
				.combo(FireBlade.class,
						new ComboStep("FireBlast", Activation.ATTACK),
						new ComboStep("FireBlast", Activation.ATTACK),
						new ComboStep("FireBlast", Activation.SNEAK),
						new ComboStep("FireBlast", Activation.ATTACK)).build();
	}
}
