package net.avatarverse.avatarversalis.core.command;

import java.util.Objects;

import net.avatarverse.avatarversalis.core.game.Game;
import net.avatarverse.avatarversalis.core.game.ability.Ability;
import net.avatarverse.avatarversalis.core.game.attribute.Attribute;
import net.avatarverse.avatarversalis.core.game.attribute.AttributeModifier;
import net.avatarverse.avatarversalis.core.game.attribute.ModifierOperation;
import net.avatarverse.avatarversalis.core.game.board.BendingBoardManager;
import net.avatarverse.avatarversalis.core.game.element.Element;
import net.avatarverse.avatarversalis.core.game.user.AvatarPlayer;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.game.user.preset.Preset;
import net.avatarverse.avatarversalis.core.platform.command.CommandSender;
import net.avatarverse.avatarversalis.core.platform.entity.Player;
import net.avatarverse.avatarversalis.core.util.data.Equivoque;
import net.avatarverse.avatarversalis.core.util.data.Wrapper;
import net.avatarverse.avatarversalis.core.util.text.Lang;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Flags;
import co.aikar.commands.annotation.Optional;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Values;
import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

@DefaultAnnotation(NonNull.class)
@CommandAlias("bending|b|avatarversalis|avatar|av|avs|pk|tla|mtla|bend")
public class BendingCommand extends BaseCommand {

	public void add(CommandSender self, User user, String element) {
		if (!self.hasPermission("bending.command.add")) {
			Lang.ELEMENT_ADD_NO_PERM.send(self);
			return;
		}
		if (element == null) {
			Lang.INVALID_ELEMENT.send(self, "null");
			return;
		}
		if (element.equalsIgnoreCase("all")) {
			Element.names().forEach(e -> add(self, user, e));
			return;
		}
		Element e = Element.byNameIgnoreCase(element);
		if (e == null) {
			Lang.INVALID_ELEMENT.send(self, element);
			return;
		}
		if (user instanceof AvatarPlayer player && !self.equals(player.player()) && !self.hasPermission("bending.command.other")) {
			Lang.ELEMENT_ADD_NO_PERM_OTHER.send(self);
			return;
		}
		if (!self.hasPermission("bending.command.add." + element)) {
			Lang.ELEMENT_ADD_NO_PERM_ELEMENT.send(self, element);
			return;
		}

		user.addElement(e);

		Lang.ELEMENT_ADD_SUCCESS.send(self, element, user.name());
	}

	@Subcommand("bind|b")
	@CommandCompletion("@bindables <slot:1-9>")
	@CommandPermission("bending.command.bind")
	public void bind(AvatarPlayer self, Ability ability, @Optional @Values("@range:1-9") Integer slot) {
		if (self.permaremoved()) {
			Lang.SELF_PERMAREMOVED.send(self);
			return;
		}
		if (!ability.bindable()) {
			Lang.BIND_NOT_BINDABLE.send(self, ability.name());
			return;
		}
		if (!self.canBend(ability)) {
			Lang.BIND_NO_ACCESS.send(self, ability.name());
			return;
		}
		if (slot == null)
			slot = self.currentSlot();

		self.bind(ability, slot);

		Lang.BIND_SUCCESS.send(self, ability.name(), slot);
	}

	@Subcommand("board|scoreboard|bo")
	@CommandPermission("bending.command.board")
	public void board(AvatarPlayer self) {
		BendingBoardManager boardManager = Game.boardManager();
		if (!boardManager.enabled()) {
			Lang.BOARD_DISABLED.send(self);
			return;
		}
		if (boardManager.enabled(self)) {
			boardManager.disable(self);
			Lang.BOARD_TOGGLE_ON.send(self);
		} else {
			boardManager.enable(self);
			Lang.BOARD_TOGGLE_OFF.send(self);
		}
	}

	@Subcommand("choose|ch")
	@CommandCompletion("@elements @users")
	@CommandPermission("bending.command.choose")
	public void choose(AvatarPlayer self, Element element, @Optional @Flags("other") User user) {
		if (user != null && !self.equals(user) && !self.player().hasPermission("bending.command.choose.other")) {
			Lang.ELEMENT_CHOOSE_NO_PERM_OTHER.send(self);
			return;
		}
		if (user == null)
			user = self;
		if (user.permaremoved()) {
			Lang.USER_PERMAREMOVED.send(self, user.name());
			return;
		}
		if (!user.elements().isEmpty() && !self.player().hasPermission("bending.command.rechoose")) {
			Lang.ELEMENT_CHOOSE_NO_PERM_RECHOOSE.send(self);
			return;
		}
		if (/* TODO cooldown is present on user && */!self.player().hasPermission("bending.command.choose.anytime")) {
			Lang.ELEMENT_CHOOSE_ON_COOLDOWN.send(self, "" /* TODO new Date() representing cooldown expiry time */);
			return;
		}

		user.clearElements();
		user.addElement(element);

		if (user.equals(self))
			Lang.ELEMENT_CHANGE.send(self, element.name());
		else if (user instanceof AvatarPlayer player) {
			Lang.ELEMENT_CHANGE.send(player);
			Lang.ELEMENT_CHOOSE_SUCCESS.send(self, element.name(), player.name());
		}
	}

	@Subcommand("clear|c|unbind|u")
	@CommandCompletion("<slot:1-9>")
	@CommandPermission("bending.command.clear")
	public void clear(AvatarPlayer self, @Optional @Values("@range:1-9") Integer slot) {
		if (slot == null) {
			self.clearBinds();
			if (self.binds().values().stream().anyMatch(Objects::nonNull))
				Lang.CLEAR_BLOCKED.send(self);
			else
				Lang.CLEAR_ALL_SUCCESS.send(self);
		} else {
			self.unbind(slot);
			if (self.binds().get(slot) != null)
				Lang.CLEAR_BLOCKED.send(self);
			else
				Lang.CLEAR_ONE_SUCCESS.send(self, slot);
		}
	}

	@Subcommand("copy|co")
	@CommandCompletion("@users")
	@CommandPermission("bending.command.copy")
	public void copy(AvatarPlayer self, @Flags("other") User user) {
		if (self.copyBinds(user)) {
			Lang.COPY_SUCCESS.send(self, user.name());
		} else {
			// TODO blocked
		}
	}

	@Subcommand("display|d")
	@CommandCompletion("@elements")
	@CommandPermission("bending.command.display")
	public void display(User self, Element element) { // TODO generify for "wc" etc.

	}

	@Subcommand("help|h")
	public void help(User self, String topic) { // TODO possibly make command completion for "help topic"

	}

	public void invincible(CommandSender self, @Optional @Flags("other") User user) {
		if (user.invincible()) {
			user.invincible(false);
			Lang.INVINCIBLE_TOGGLE_OFF.send(self, user.name());
		} else {
			user.invincible(true);
		}
	}

	@Subcommand("modify|m")
	@CommandPermission("bending.command.modify")
	public static class ModifyCommand extends BaseCommand {

		@Subcommand("add|a")
		@CommandCompletion("@users @elements-abilities @attributes @operations <modifier>")
		public void add(CommandSender self, User user, String elementOrAbility, Attribute attribute, ModifierOperation operation, Number modifier) {
			Element element = Element.byNameIgnoreCase(elementOrAbility);
			Ability ability = Ability.byNameIgnoreCase(elementOrAbility);
			AttributeModifier attributeModifier = new AttributeModifier(attribute, operation, modifier);
			if (element != null) {
				user.addModifier(element, attributeModifier);
				Lang.MODIFY_ADD_SUCCESS.send(self, user.name(), element.name());
			} else if (ability != null) {
				user.addModifier(ability, attributeModifier);
				Lang.MODIFY_ADD_SUCCESS.send(self, user.name(), ability.name());
			} else {
				Lang.MODIFY_ADD_PARSE_ERROR.send(self, elementOrAbility);
			}
		}

		@Subcommand("clear|c")
		public void clear(CommandSender self, User user) {
			user.clearModifiers();
			Lang.MODIFY_CLEAR_SUCCESS.send(self, user.name());
		}
	}

	@Subcommand("permaremove|pr")
	@CommandCompletion("@elements-users @elements-users")
	@CommandPermission("bending.command.permaremove")
	public void permaremove(CommandSender self, User user) {
		user.permaremove();
		(user.permaremoved() ? Lang.PERMAREMOVE_SUCCESS : Lang.PERMAREMOVE_BLOCKED).send(self, user.name());
	}

	@Subcommand("preset|p")
	@CommandPermission("bending.command.preset")
	public static class PresetCommand extends BaseCommand {

		@Subcommand("create|c")
		@CommandCompletion("<name>")
		@CommandPermission("bending.command.preset.create")
		public void create(AvatarPlayer self, String name) {
			if (self.presets().containsKey(name)) {
				Lang.PRESET_CREATE_NAME_TAKEN.send(self);
				return;
			}
			if (!Lang.alphanumeric(name)) {
				Lang.REQUIRE_ALPHANUMERIC.send(self);
				return;
			}
			if (self.presets().size() + 1 > Preset.LIMIT_PER_PLAYER) {
				Lang.PRESET_CREATE_LIMIT_REACHED.send(self, Preset.LIMIT_PER_PLAYER);
				return;
			}

			self.createPreset(name);

			if (!self.presets().containsKey(name))
				Lang.PRESET_CREATE_BLOCKED.send(self);
			else
				Lang.PRESET_CREATE_SUCCESS.send(self, name);
		}

		@Subcommand("delete|d")
		@CommandCompletion("@presets")
		@CommandPermission("bending.command.preset.delete")
		public void delete(AvatarPlayer self, Preset preset) {
			self.deletePreset(preset);

			if (self.presets().containsKey(preset.name()))
				Lang.PRESET_DELETE_BLOCKED.send(self);
			else
				Lang.PRESET_DELETE_SUCCESS.send(self, preset.name());
		}

		@Subcommand("bind|b")
		@CommandCompletion("@presets")
		@CommandPermission("bending.command.preset.bind")
		public void bind(AvatarPlayer self, Preset preset) {
			self.bindPreset(preset);

			if (!self.binds().equals(preset.binds()))
				Lang.PRESET_BIND_BLOCKED.send(self);
			else
				Lang.PRESET_BIND_SUCCESS.send(self, preset.name());
		}

		@Subcommand("list|l")
		@CommandPermission("bending.command.preset.list")
		public void list(AvatarPlayer self) {

		}

	}

	@Subcommand("reload")
	@CommandPermission("bending.command.reload")
	public void reload(User self) {
		Game.plugin().reloadConfig();
		// TODO success
	}

	@Subcommand("remove|r")
	@CommandCompletion("@elements-users @elements-users")
	@CommandPermission("bending.command.remove")
	public void remove(CommandSender self, Equivoque<Element, User> user, @Optional Equivoque<Element, User> element) {
		if (element == null) { // user can be Element or User. if Element, remove element from self. if User, remove all elements from user.
			Element e = user.p();
			User u = user.q();
			if (e != null && u == null) {
				if (!(self instanceof Player player)) {
					Lang.REQUIRE_PLAYER.send(self);
					return;
				}
				Wrapper.of(AvatarPlayer.of(player)).ifPresent(ap -> {
					ap.removeElement(e);
					(!ap.hasElement(e) ? Lang.ELEMENT_REMOVE_SUCCESS : Lang.ELEMENT_REMOVE_NO_PERM).send(self); // TODO blocked
				});
			} else if (u != null && e == null) {
				u.clearElements();
				// TODO success
			} else {
				// TODO parse
			}
		} else { // figure out which one is Element and which is User, then remove Element from User
			User u1 = user.q(), u2 = element.q(), u;
			Element e1 = user.p(), e2 = element.p(), e;
			if (u1 != null && e2 != null) {
				u = u1; e = e2;
			} else if (e1 != null && u2 != null) {
				u = u2; e = e1;
			} else {
				// TODO parse
				return;
			}
			u.removeElement(e);
			if (!u.hasElement(e)) {
				// TODO Your e has been removed
				// TODO success
			} else {
				// TODO blocked
			}
		}
	}

	public void remove(CommandSender self, User user, @Nullable String element) {
		if (!self.hasPermission("bending.command.remove")) {
			Lang.ELEMENT_REMOVE_NO_PERM.send(self);
			return;
		}
		if (!self.equals(user) && !self.hasPermission("bending.command.remove.other")) {
			Lang.ELEMENT_REMOVE_NO_PERM_OTHER.send(self);
			return;
		}
		if (element == null) {
			user.clearElements();
			Lang.ELEMENT_REMOVE_SUCCESS_CLEAR.send(self, user.name());
			return;
		}
		Element e = Element.byNameIgnoreCase(element);
		if (e == null) {
			Lang.INVALID_ELEMENT.send(self, element);
			return;
		}
		user.removeElement(e);
		Lang.ELEMENT_REMOVE_SUCCESS.send(self, user.name(), e.name());
	}

	@Subcommand("toggle|t")
	@CommandCompletion("@elements-users @elements-users")
	@CommandPermission("bending.command.toggle")
	public void toggle(CommandSender self, @Optional Equivoque<Element, User> arg1, @Optional Equivoque<Element, User> arg2) {
		if (arg1 == null && arg2 == null) { // no arguments, toggle self's bending
			if (!(self instanceof Player player)) {
				Lang.REQUIRE_PLAYER.send(self);
				return;
			}
			Wrapper.of(AvatarPlayer.of(player)).ifPresent(ap -> {
				ap.toggle();
				(ap.toggled() ? Lang.TOGGLE_ON : Lang.TOGGLE_OFF).send(ap);
			});
		} else if (arg1 != null && arg2 == null) { // one argument (/b t <arg1>), element or user
			Element element = arg1.p();
			User user = arg1.q();
			if (element != null) { // toggle self's element
				if (!(self instanceof Player player)) {
					Lang.REQUIRE_PLAYER.send(self);
					return;
				}
				Wrapper.of(AvatarPlayer.of(player)).ifPresent(ap -> {
					ap.toggleElement(element);
					(ap.toggled() ? Lang.TOGGLE_ON_ELEMENT : Lang.TOGGLE_OFF_ELEMENT).send(ap, element.name());
				});
			} else if (user != null) { // toggle other user's bending
				if (!self.hasPermission("bending.command.toggle.other")) {
					Lang.TOGGLE_OTHER_NO_PERM.send(self);
					return;
				}
				user.toggle();
				(user.toggled() ? Lang.TOGGLE_ON : Lang.TOGGLE_OFF).send(user);
				Lang.TOGGLE_OTHER_SUCCESS.send(self);
			} else { // error
				Lang.CANNOT_PARSE_ARG.send(self, arg1, "toggle");
			}
		} else if (arg1 != null) { // two arguments, toggle element for user
			if (!self.hasPermission("bending.command.toggle.other")) {
				Lang.TOGGLE_OTHER_NO_PERM.send(self);
				return;
			}
			Element e1 = arg1.p(), e2 = arg2.p(), e;
			User u1 = arg1.q(), u2 = arg2.q(), u;
			if (e1 != null && u2 != null) {
				e = e1;
				u = u2;
			} else if (e2 != null && u1 != null) {
				e = e2;
				u = u1;
			} else {
				Lang.CANNOT_PARSE_ARG.send(self, arg1 + " and/or " + arg2, "toggle");
				return;
			}
			u.toggleElement(e);
			(u.isElementToggled(e) ? Lang.TOGGLE_ON_ELEMENT : Lang.TOGGLE_OFF_ELEMENT).send(u, e.name());
			Lang.TOGGLE_OTHER_ELEMENT_SUCCESS.send(self, u.name(), e.name());
		}
	}

	@Subcommand("version|v")
	@CommandPermission("bending.command.version")
	public void version(User self) {
		String version = Game.plugin().version();
	}

	@Subcommand("who|w")
	@CommandCompletion("@users")
	@CommandPermission("bending.command.who")
	public void who(User self, @Flags("other") User user) {

	}

}
