package net.avatarverse.avatarversalis.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.Nullable;

import net.avatarverse.avatarversalis.Avatarversalis;
import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.core.board.BendingBoardManager;
import net.avatarverse.avatarversalis.core.element.Element;
import net.avatarverse.avatarversalis.core.user.AvatarPlayer;
import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.core.user.preset.Preset;
import net.avatarverse.avatarversalis.util.text.Lang;
import net.avatarverse.avatarversalis.util.text.Lang.Message;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Flags;
import co.aikar.commands.annotation.Optional;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Values;

@CommandAlias("b|avatarversalis|avatar|av|avs|pk|tla|mtla|bend")
public class BendingCommand extends BaseCommand {

	@Subcommand("user")
	@CommandCompletion("@users add|remove|permaremove|toggle|a|r|pr|t @elements")
	public void user(CommandSender self, @Flags("other") User user, String action, @Optional String element) {
		switch (action) {
		case "add", "a" -> add(self, user, element);
		case "remove", "r" -> remove(self, user, element);
		case "permaremove", "pr" -> permaremove(self, user, element);
		case "toggle", "t" -> toggle(self, user, element);
		default -> Lang.USER_INVALID_ACTION.send(self, "add", "remove", "permaremove", "toggle");
		}
	}

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
		Element e = Element.byName(element);
		if (e == null) {
			Lang.INVALID_ELEMENT.send(self, element);
			return;
		}
		if (!self.equals(user) && !self.hasPermission("bending.command.other")) {
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
		if (!ability.bindable()) {
			Lang.ABILITY_BIND_NOT_BINDABLE.send(self, ability.name());
			return;
		}
		if (!self.canBend(ability)) {
			Lang.ABILITY_BIND_NO_ACCESS.send(self, ability.name());
			return;
		}
		if (slot == null)
			slot = self.currentSlot();
		self.bind(ability, slot);
		Lang.ABILITY_BIND_SUCCESS.send(self, ability.name(), slot);
	}

	@Subcommand("board|scoreboard|bo")
	@CommandPermission("bending.command.board")
	public void board(AvatarPlayer self) {
		BendingBoardManager boardManager = Avatarversalis.game().boardManager();
		if (!boardManager.enabled()) {
			// TODO board is disabled message
			return;
		}
		if (boardManager.enabled(self)) {
			boardManager.disable(self);
			// TODO board enabled msg
		} else {
			boardManager.enable(self);
			// TODO board disabled msg
		}
	}

	@Subcommand("choose|ch")
	@CommandCompletion("@elements [user]")
	@CommandPermission("bending.command.choose")
	public void choose(AvatarPlayer self, Element element, @Optional @Flags("other") User user) {
		// bending.command.choose.anytime - bypass cooldown if present
		// bending.command.rechoose
		if (user != null && !self.equals(user) && !self.player().hasPermission("bending.command.choose.other")) {
			// TODO cannot change others' elements
			return;
		}
		if (user == null)
			user = self;
		if (!self.elements().isEmpty() && !self.player().hasPermission("bending.command.rechoose")) {
			// TODO cannot rechoose element
			return;
		}
		if (/* cooldown is present on user && */!self.player().hasPermission("bending.command.choose.anytime")) {
			// TODO cannot choose because of cooldown
			return;
		}
		user.clearElements();
		user.addElement(element);
		// TODO success msg
	}

	@Subcommand("clear|c")
	@CommandCompletion("<slot:1-9>")
	@CommandPermission("bending.command.clear")
	public void clear(User self, @Optional @Values("@range:1-9") Integer slot) {
		if (slot == null)
			self.binds().clear();
		else self.unbind(slot);
		// TODO success msg
	}

	@Subcommand("copy|co")
	@CommandCompletion("@users")
	@CommandPermission("bending.command.copy")
	public void copy(User self, @Flags("other") User user) {
		user.binds().forEach((slot, ability) -> {
			if (self.canBend(ability))
				self.bind(ability, slot);
		});
		// TODO success msg
	}

	@Subcommand("display|d")
	@CommandCompletion("@elements")
	@CommandPermission("bending.command.display")
	public void display(User self, Element element) { // TODO generify for "wc" etc.

	}

	@Subcommand("help|h")
	public void help(User self, String topic) { // TODO possibly make command completion for "help topic"

	}

	@Subcommand("invincible|i")
	@CommandCompletion("@users")
	@CommandPermission("bending.command.invincible")
	public void invincible(User self, @Optional @Flags("other") User user) {
		// TODO add invincible stuff first
	}

	@Subcommand("permaremove|pr")
	@CommandCompletion("@users @elements")
	public void permaremove(CommandSender self, User user, @Nullable String element) {
		// TODO add permaremove
	}

	@Subcommand("preset|p")
	@CommandPermission("bending.command.preset")
	public static class PresetCommand extends BaseCommand {

		@Subcommand("create|c")
		@CommandCompletion("<name>")
		@CommandPermission("bending.command.preset.create")
		public void create(AvatarPlayer self, String name) {
			self.createPreset(name);
			Lang.PRESET_CREATE_SUCCESS.send(self, name);
		}

		@Subcommand("delete|d")
		@CommandCompletion("@presets")
		@CommandPermission("bending.command.preset.delete")
		public void delete(AvatarPlayer self, Preset preset) {
			self.deletePreset(preset);
			Lang.PRESET_DELETE_SUCCESS.send(self, preset.name());
		}

		@Subcommand("bind|b")
		@CommandCompletion("@presets")
		@CommandPermission("bending.command.preset.bind")
		public void bind(AvatarPlayer self, Preset preset) {
			self.bindPreset(preset);
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
		// TODO add reload
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
		Element e = Element.byName(element);
		if (e == null) {
			Lang.INVALID_ELEMENT.send(self, element);
			return;
		}
		user.removeElement(e);
		Lang.ELEMENT_REMOVE_SUCCESS.send(self, user.name(), e.name());
	}

	@Subcommand("toggle|t")
	@CommandCompletion("@elements")
	@CommandPermission("bending.command.toggle")
	public void toggle(AvatarPlayer self, @Optional Element element) {
		if (element == null) {
			self.toggle();
			Message message = self.toggled() ? Lang.TOGGLE_ON : Lang.TOGGLE_OFF;
			message.send(self);
		} else {
			self.toggleElement(element);
			Message message = self.isElementToggled(element) ? Lang.TOGGLE_ON_ELEMENT : Lang.TOGGLE_OFF_ELEMENT;
			message.send(self, element.name());
		}
	}

	public void toggle(CommandSender self, User user, String element) {

	}

	@Subcommand("version|v")
	@CommandPermission("bending.command.version")
	public void version(User self) {
		String version = Avatarversalis.plugin().getDescription().getVersion();
	}

	@Subcommand("who|w")
	@CommandCompletion("@users")
	@CommandPermission("bending.command.who")
	public void who(User self, @Flags("other") User user) {

	}

}
