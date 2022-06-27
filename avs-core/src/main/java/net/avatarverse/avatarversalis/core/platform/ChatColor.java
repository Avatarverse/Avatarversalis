package net.avatarverse.avatarversalis.core.platform;

import java.awt.Color;

import net.avatarverse.avatarversalis.core.util.text.Colors;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatColor {

	public static final ChatColor BLACK = byChar('0');
	public static final ChatColor DARK_BLUE = byChar('1');
	public static final ChatColor DARK_GREEN = byChar('2');
	public static final ChatColor DARK_AQUA = byChar('3');
	public static final ChatColor DARK_RED = byChar('4');
	public static final ChatColor DARK_PURPLE = byChar('5');
	public static final ChatColor GOLD = byChar('6');
	public static final ChatColor GRAY = byChar('7');
	public static final ChatColor DARK_GRAY = byChar('8');
	public static final ChatColor BLUE = byChar('9');
	public static final ChatColor GREEN = byChar('a');
	public static final ChatColor AQUA = byChar('b');
	public static final ChatColor RED = byChar('c');
	public static final ChatColor LIGHT_PURPLE = byChar('d');
	public static final ChatColor YELLOW = byChar('e');
	public static final ChatColor WHITE = byChar('f');

	private final Color color;

	public static ChatColor of(Color color) {
		return new ChatColor(color);
	}

	public static ChatColor of(String string) {
		return new ChatColor(Colors.hexToJavaColor(string));
	}

	public static ChatColor byChar(char code) {
		return of("");
	}

	public static String translateAlternateColorCodes(char altColorChar, String textToTranslate) {
		return textToTranslate;
	}

	public static ChatColor[] values() {
		return new ChatColor[5];
	}

}
