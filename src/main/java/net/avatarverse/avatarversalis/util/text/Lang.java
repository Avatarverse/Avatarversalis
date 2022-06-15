package net.avatarverse.avatarversalis.util.text;

import javax.annotation.ParametersAreNonnullByDefault;
import java.text.MessageFormat;
import java.util.Locale;

import org.bukkit.command.CommandSender;

import com.karuslabs.lingua.franca.Bundle;
import com.karuslabs.lingua.franca.Bundler;
import com.karuslabs.lingua.franca.annotations.ClassLoaderSources;
import com.karuslabs.lingua.franca.annotations.Namespace;

import net.avatarverse.avatarversalis.Avatarversalis;
import net.avatarverse.avatarversalis.core.user.AvatarPlayer;

import net.md_5.bungee.api.ChatColor;

import edu.umd.cs.findbugs.annotations.ReturnValuesAreNonnullByDefault;
import lombok.RequiredArgsConstructor;

@ParametersAreNonnullByDefault
@ReturnValuesAreNonnullByDefault
@Namespace("messages")
@ClassLoaderSources({"translations"})
public interface Lang {

	Locale LOCALE = Locale.forLanguageTag(Avatarversalis.configManager().config().node("properties.language").getString("en"));
	Bundle BUNDLE = Bundler.bundler().load(Lang.class, LOCALE);
	ThreadLocal<MessageFormat> FORMATTER = ThreadLocal.withInitial(() -> new MessageFormat(""));

	Message USER_INVALID_ACTION = translate("command.user.invalid-action", "The valid subcommands are: {0}, {1}, {2}, {3}.");
	Message INVALID_ELEMENT = translate("command.add.invalid-element", "Element {0} is invalid.");
	Message ELEMENT_ADD_NO_PERM = translate("command.add.no-permission", "You don't have permission to add elements.");
	Message ELEMENT_ADD_NO_PERM_OTHER = translate("command.add.no-permission.other", "You don't have permission to add elements to others.");
	Message ELEMENT_ADD_NO_PERM_ELEMENT = translate("command.add.no-permission.element", "You don't have permission to add {0} to anyone.");
	Message ELEMENT_ADD_SUCCESS = translate("command.add.success", "Successfully added {0} to {1}.");
	Message ELEMENT_REMOVE_NO_PERM = translate("command.remove.no-permission", "You don't have permission to remove elements.");
	Message ELEMENT_REMOVE_NO_PERM_OTHER = translate("command.remove.no-permission.other", "You don't have permission to remove others' elements.");
	Message ELEMENT_REMOVE_SUCCESS_CLEAR = translate("command.remove.success.clear", "Successfully cleared {0}'s elements.");
	Message ELEMENT_REMOVE_SUCCESS = translate("command.remove.success", "Successfully removed {0}'s {1}.");
	Message ABILITY_BIND_NOT_BINDABLE = translate("command.bind.not-bindable", "{0} cannot be bound to a slot. Use /b h {0} to learn how it works.");
	Message ABILITY_BIND_NO_ACCESS = translate("command.bind.no-access", "You don't have access to {0}.");
	Message ABILITY_BIND_SUCCESS = translate("command.bind.success", "Successfully bound {0} to slot {1}.");
	Message PRESET_CREATE_SUCCESS = translate("command.preset.create.success", "Successfully created the preset {0}.");
	Message PRESET_DELETE_SUCCESS = translate("command.preset.delete.success", "Successfully deleted the preset {0}.");
	Message PRESET_BIND_SUCCESS = translate("command.preset.bind.success", "Successfully bound the preset {0}.");
	Message TOGGLE_ON = translate("command.toggle.on", "Your bending has been toggled back on.");
	Message TOGGLE_OFF = translate("command.toggle.off", "Your bending has been toggled off.");
	Message TOGGLE_ON_ELEMENT = translate("command.toggle.on.element", "Your {0} has been toggled back on.");
	Message TOGGLE_OFF_ELEMENT = translate("command.toggle.off.element", "Your {0} has been toggled off.");
	Message TOGGLE_OTHER_SUCCESS = translate("command.toggle.other", "Successfully toggled {0}'s bending.");
	Message TOGGLE_OTHER_ELEMENT_SUCCESS = translate("command.toggle.other.element", "Successfully toggled {0}'s {1}.");

	Message BOARD_TITLE = translate("scoreboard.title", "&lAbilities");
	Message BOARD_EMPTY = translate("scoreboard.empty-slot", " ---- Slot {0} ---- ");

	static Message translate(String key, String def) {
		return new Message(BUNDLE.get(key).orElse(def));
	}

	static String format(String translated, Object... args) {
		MessageFormat formatter = FORMATTER.get();
		formatter.setLocale(LOCALE);
		formatter.applyPattern(translated);
		return formatter.format(args);
	}

	static void info(String message) {
		Avatarversalis.log().info(colorify(message));
	}

	static void warn(String message) {
		Avatarversalis.log().warning(colorify(message));
	}

	static void severe(String message) {
		Avatarversalis.log().severe(colorify(message));
	}

	static String colorify(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	static String bold(String message) {
		return colorify("&l" + message);
	}

	static String italic(String message) {
		return colorify("&o" + message);
	}

	static String underline(String message) {
		return colorify("&n" + message);
	}

	static String strikethrough(String message) {
		return colorify("&m" + message);
	}

	@RequiredArgsConstructor
	class Message {
		private final String translated;

		public void send(AvatarPlayer player, Object... args) {
			send(player.player(), args);
		}

		public void send(CommandSender receiver, Object... args) {
			receiver.sendMessage(asString(args));
		}

		public String asString(Object... args) {
			return colorify(format(translated, args));
		}
	}

}
