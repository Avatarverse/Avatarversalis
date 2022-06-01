package net.avatarverse.avatarversalis.core.element;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableSet;

import net.avatarverse.avatarversalis.util.Colors;
import net.md_5.bungee.api.ChatColor;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Element {

	private static final Map<String, Element> ELEMENTS = new HashMap<>();

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

	@Getter private final ElementDisplay display;
	@Getter private ImmutableSet<Element> parents, children;

	public static Element register(ElementDisplay display, Element... parents) {
		String name = display.noun();
		Element element = new Element(display);

		element.parents = ImmutableSet.copyOf(parents);
		element.children = ImmutableSet.of();
		for (Element parent : parents)
			parent.children = new ImmutableSet.Builder<Element>().addAll(parent.children).add(element).build();

		ELEMENTS.put(name, element);
		return element;
	}

}
