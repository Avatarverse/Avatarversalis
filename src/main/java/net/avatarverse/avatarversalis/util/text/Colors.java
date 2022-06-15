package net.avatarverse.avatarversalis.util.text;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Color;

import net.md_5.bungee.api.ChatColor;

import edu.umd.cs.findbugs.annotations.ReturnValuesAreNonnullByDefault;

@ParametersAreNonnullByDefault
@ReturnValuesAreNonnullByDefault
public interface Colors {

	ChatColor WATER = ChatColor.of("");
	ChatColor EARTH = ChatColor.of("");
	ChatColor FIRE = ChatColor.of("");
	ChatColor AIR = ChatColor.of("");
	ChatColor NON = ChatColor.of("");

	/**
	 * Converts a hex code to the corresponding int array of the red, green, and blue values respectively.
	 * @param hex hexadecimal code, like <code>"ffffff"</code> or <code>"#ffffff"</code>
	 * @return an int array of the R, G, B values in that order, or {0, 0, 0} if the hex code cannot be parsed.
	 */
	static int[] hexToRgb(String hex) {
		if (hex.startsWith("#"))
			hex = hex.substring(1);
		if (hex.length() != 6)
			return new int[]{0, 0, 0};
		int r = Integer.valueOf(hex.substring(0, 2), 16);
		int g = Integer.valueOf(hex.substring(2, 4), 16);
		int b = Integer.valueOf(hex.substring(4, 6), 16);
		return new int[]{r, g, b};
	}

	/**
	 * Converts a hex code to the corresponding {@link Color}.
	 * @param hex hexadecimal code, like <code>"ffffff"</code> or <code>"#ffffff"</code>
	 * @return the {@link Color} associated with the hex code, or the all-black {@link Color} if the hex code cannot be parsed.
	 */
	static Color hexToBukkitColor(String hex) {
		int[] rgb = hexToRgb(hex);
		return Color.fromRGB(rgb[0], rgb[1], rgb[2]);
	}

	/**
	 * Converts a hex code to the corresponding {@link java.awt.Color}.
	 * @param hex hexadecimal code, like <code>"ffffff"</code> or <code>"#ffffff"</code>
	 * @return the {@link java.awt.Color} associated with the hex code, or the all-black {@link java.awt.Color} if the hex code cannot be parsed.
	 */
	static java.awt.Color hexToJavaColor(String hex) {
		int[] rgb = hexToRgb(hex);
		return new java.awt.Color(rgb[0], rgb[1], rgb[2]);
	}

	/**
	 * Converts a hex code to the corresponding {@link ChatColor}.
	 * @param hex hexadecimal code, like <code>"ffffff"</code> or <code>"#ffffff"</code>
	 * @return the {@link ChatColor} associated with the hex code, or the all-black {@link ChatColor} if the hex code cannot be parsed.
	 */
	static ChatColor hexToChatColor(String hex) {
		return ChatColor.of(hexToJavaColor(hex));
	}

}
