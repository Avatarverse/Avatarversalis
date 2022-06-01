package net.avatarverse.avatarversalis.core.element;

import net.md_5.bungee.api.ChatColor;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ElementDisplay {
	private final String noun, verb, user;
	private final ChatColor color;

	/**
	 * Standard ElementDisplay - adds "bending", "bend", and "bender" suffixes to the name parameter
	 * @param name name of element
	 * @param color {@link ChatColor} of element
	 */
	public ElementDisplay(String name, ChatColor color) {
		noun = name + "bending";
		verb = name + "bend";
		user = name + "bender";
		this.color = color;
	}
}
