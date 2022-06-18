package net.avatarverse.avatarversalis.core.element;

import net.md_5.bungee.api.ChatColor;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@DefaultAnnotation(NonNull.class)
@RequiredArgsConstructor
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
