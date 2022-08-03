package net.avatarverse.avatarversalis.core.util.text;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.regex.Pattern;

import com.karuslabs.lingua.franca.Bundle;
import com.karuslabs.lingua.franca.Bundler;
import com.karuslabs.lingua.franca.annotations.ClassLoaderSources;
import com.karuslabs.lingua.franca.annotations.Namespace;

import net.avatarverse.avatarversalis.core.game.Game;
import net.avatarverse.avatarversalis.core.game.user.AvatarPlayer;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.platform.ChatColor;
import net.avatarverse.avatarversalis.core.platform.command.CommandSender;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.RequiredArgsConstructor;

@DefaultAnnotation(NonNull.class)
@Namespace("messages")
@ClassLoaderSources({"translations"})
public interface Lang {

	Locale LOCALE = Locale.forLanguageTag(Game.config().node("properties.language").getString("en"));
	Bundle BUNDLE = Bundler.bundler().load(Lang.class, LOCALE);
	ThreadLocal<MessageFormat> FORMATTER = ThreadLocal.withInitial(() -> new MessageFormat(""));
	Pattern ALPHANUMERIC = Pattern.compile("[A-Za-z0-9]+");

	// COMMANDS
	Message USER_INVALID_ACTION = translate("generic.invalid-action", "The valid subcommands are: {0}, {1}, {2}, {3}.");
	Message INVALID_ELEMENT = translate("generic.invalid-element", "Element {0} is invalid.");
	Message ELEMENT_CHANGE = translate("generic.element-change", "You are now a {0}.");
	Message REQUIRE_ALPHANUMERIC = translate("generic.require-alphanumeric", "The name must be alphanumeric.");
	Message REQUIRE_PLAYER = translate("generic.require-player", "You must be a player to run this command, or add a User argument.");
	Message CANNOT_PARSE_ARG = translate("generic.cannot-parse-argument", "Argument {0} is invalid. For help, use /b h {1}.");
	Message SELF_PERMAREMOVED = translate("generic.permaremoved.self", "You cannot bend; your bending was permanently removed");
	Message USER_PERMAREMOVED = translate("generic.permaremoved.other", "{0} cannot bend; their bending was permanently removed.");
	Message ELEMENT_ADD_NO_PERM = translate("command.add.no-permission", "You don't have permission to add elements.");
	Message ELEMENT_ADD_NO_PERM_OTHER = translate("command.add.no-permission.other", "You don't have permission to add elements to others.");
	Message ELEMENT_ADD_NO_PERM_ELEMENT = translate("command.add.no-permission.element", "You don't have permission to add {0} to anyone.");
	Message ELEMENT_ADD_SUCCESS = translate("command.add.success", "Successfully added {0} to {1}.");
	Message BIND_NOT_BINDABLE = translate("command.bind.not-bindable", "{0} cannot be bound to a slot. Use /b h {0} to learn how it works.");
	Message BIND_NO_ACCESS = translate("command.bind.no-access", "You don't have access to {0}.");
	Message BIND_SUCCESS = translate("command.bind.success", "Successfully bound {0} to slot {1}.");
	Message BOARD_DISABLED = translate("command.board.disabled", "The bending scoreboard is disabled on this server.");
	Message BOARD_TOGGLE_ON = translate("command.board.toggle.on", "Successfully enabled your bending board.");
	Message BOARD_TOGGLE_OFF = translate("command.board.toggle.off", "Successfully disabled your bending board.");
	Message ELEMENT_CHOOSE_NO_PERM_OTHER = translate("command.choose.no-permission.other", "You don't have permission to choose others' elements.");
	Message ELEMENT_CHOOSE_NO_PERM_RECHOOSE = translate("command.choose.no-permission.rechoose", "You don't have permission to rechoose your element.");
	Message ELEMENT_CHOOSE_ON_COOLDOWN = translate("command.choose.on-cooldown", "You must wait until {0} to change your element.");
	Message ELEMENT_CHOOSE_SUCCESS = translate("command.choose.success", "Successfully chose {0} for {1}.");
	Message CLEAR_BLOCKED = translate("command.clear.blocked", "Clearing any binds was blocked by an external plugin.");
	Message CLEAR_ALL_SUCCESS = translate("command.clear.success.all", "Successfully cleared your binds.");
	Message CLEAR_ONE_SUCCESS = translate("command.clear.success.one", "Successfully cleared slot {0}.");
	Message COPY_SUCCESS = translate("command.copy.success", "Copied as many of {0}'s binds as possible.");
	Message INVINCIBLE_TOGGLE_ON = translate("command.invincible.on", "{0} is now invincible.");
	Message INVINCIBLE_TOGGLE_OFF = translate("command.invincible.off", "{0} is no longer invincible.");
	Message MODIFY_ADD_PARSE_ERROR = translate("command.modify.add.parse-error", "Unable to find an element or ability called {0}.");
	Message MODIFY_ADD_SUCCESS = translate("command.modify.add.success", "Successfully added modifier for {0}'s {1}.");
	Message MODIFY_CLEAR_SUCCESS = translate("command.modify.clear.success", "Successfully cleared {0}'s modifiers.");
	Message PERMAREMOVE_BLOCKED = translate("command.permaremove.blocked", "Permanently removing {0}'s bending was blocked by an external plugin.");
	Message PERMAREMOVE_SUCCESS = translate("command.permaremove.success", "Successfully permanently removed {0}'s bending.");
	Message PRESET_CREATE_NAME_TAKEN = translate("command.preset.create.name-taken", "You already have a preset with that name.");
	Message PRESET_CREATE_LIMIT_REACHED = translate("command.preset.create.limit-reached", "Unable to create this preset because you've reached the preset limit of {0}.");
	Message PRESET_CREATE_BLOCKED = translate("command.preset.create.blocked", "Creating this preset was blocked by an external plugin.");
	Message PRESET_CREATE_SUCCESS = translate("command.preset.create.success", "Successfully created the preset {0}.");
	Message PRESET_DELETE_BLOCKED = translate("command.preset.delete.blocked", "Deleting this preset was blocked by an external plugin.");
	Message PRESET_DELETE_SUCCESS = translate("command.preset.delete.success", "Successfully deleted the preset {0}.");
	Message PRESET_BIND_BLOCKED = translate("command.preset.bind.blocked", "Binding this preset was blocked by an external plugin.");
	Message PRESET_BIND_SUCCESS = translate("command.preset.bind.success", "Successfully bound the preset {0}.");
	Message ELEMENT_REMOVE_NO_PERM = translate("command.remove.no-permission", "You don't have permission to remove elements.");
	Message ELEMENT_REMOVE_NO_PERM_OTHER = translate("command.remove.no-permission.other", "You don't have permission to remove others' elements.");
	Message ELEMENT_REMOVE_SUCCESS_CLEAR = translate("command.remove.success.clear", "Successfully cleared {0}'s elements.");
	Message ELEMENT_REMOVE_SUCCESS = translate("command.remove.success", "Successfully removed {0}'s {1}.");
	Message TOGGLE_ON = translate("command.toggle.on", "Your bending has been toggled back on.");
	Message TOGGLE_OFF = translate("command.toggle.off", "Your bending has been toggled off.");
	Message TOGGLE_ON_ELEMENT = translate("command.toggle.on.element", "Your {0} has been toggled back on.");
	Message TOGGLE_OFF_ELEMENT = translate("command.toggle.off.element", "Your {0} has been toggled off.");
	Message TOGGLE_OTHER_NO_PERM = translate("command.toggle.other.no-perm", "You don't have permission to toggle the bending of others.");
	Message TOGGLE_OTHER_SUCCESS = translate("command.toggle.other.success", "Successfully toggled {0}'s bending.");
	Message TOGGLE_OTHER_ELEMENT_SUCCESS = translate("command.toggle.other.element", "Successfully toggled {0}'s {1}.");

	// BENDING BOARD
	Message BOARD_TITLE = translate("scoreboard.title", "&lAbilities");
	Message BOARD_EMPTY = translate("scoreboard.empty-slot", " ---- Slot {0} ---- ");

	// MISCELLANEOUS ERRORS
	Message ABILITY_MANAGER_REGISTER_METHOD_NOT_FOUND = translate("misc-errors.ability-manager.register-method-not-found", "Unable to find static \"register\" method in {0}. Cannot register ability.");
	Message ABILITY_MANAGER_REGISTER_INVOKE_ERROR = translate("misc.errors-ability-manager.register-invoke-error", "Error while registering {0}: {1}");

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
		Game.plugin().log().info(colorify(message));
	}

	static void warn(String message) {
		Game.plugin().log().warning(colorify(message));
	}

	static void severe(String message) {
		Game.plugin().log().severe(colorify(message));
	}

	static void info(Message message, Object... args) {
		Game.plugin().log().info(message.asString(args));
	}

	static void warn(Message message, Object... args) {
		Game.plugin().log().warning(message.asString(args));
	}

	static void severe(Message message, Object... args) {
		Game.plugin().log().severe(message.asString(args));
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

	static boolean alphanumeric(String message) {
		return ALPHANUMERIC.matcher(message).matches();
	}

	@RequiredArgsConstructor
	class Message {
		private final String translated;

		public void send(AvatarPlayer player, Object... args) {
			send(player.player(), args);
		}

		public void send(User user, Object... args) {
			if (user instanceof AvatarPlayer player)
				send(player, args);
		}

		public void send(CommandSender receiver, Object... args) {
			receiver.sendMessage(asString(args));
		}

		public String asString(Object... args) {
			return colorify(format(translated, args));
		}
	}

}
