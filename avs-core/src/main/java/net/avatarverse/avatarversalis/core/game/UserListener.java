package net.avatarverse.avatarversalis.core.game;

import net.avatarverse.avatarversalis.core.game.ability.Ability;
import net.avatarverse.avatarversalis.core.game.board.BendingBoardManager.BendingBoard;
import net.avatarverse.avatarversalis.core.game.temporary.Cooldown;
import net.avatarverse.avatarversalis.core.game.user.AvatarPlayer;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.game.user.preset.Preset;
import net.avatarverse.avatarversalis.core.platform.entity.Player;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * Listener for handling player related events
 */
@DefaultAnnotation(NonNull.class)
@SuppressWarnings("unused")
public class UserListener {

	/**
	 * Loads player information from the database
	 * @param player the {@link Player} to load
	 */
	protected final void onJoin(Player player) {

	}

	/**
	 * Saves player information to the database
	 * @param player the {@link Player} to save
	 */
	protected final void onLeave(Player player) {

	}

	/**
	 * Updates the {@link BendingBoard} associated with the {@link AvatarPlayer}
	 * @param player the {@link AvatarPlayer} whose {@link BendingBoard} is to be updated
	 */
	protected final void onChangeWorld(AvatarPlayer player) {
		Game.boardManager().board(player).ifPresent(BendingBoard::update);
	}

	/**
	 * Updates the {@link BendingBoard} associated with the {@link AvatarPlayer} by changing the slot in the display
	 * @param player the {@link AvatarPlayer} whose {@link BendingBoard} is to be updated
	 * @param newSlot the slot to which the {@link AvatarPlayer} is switching
	 */
	protected final void onSlotChange(AvatarPlayer player, int newSlot) {
		Game.boardManager().board(player).ifPresent(board -> board.switchSlot(newSlot));
	}

	/**
	 * Updates the {@link BendingBoard} associated with the {@link AvatarPlayer} by updating their bound abilities
	 * @see EventBus#postUserBindEvent(User, Ability, int)
	 * @see EventBus#postUserUnbindEvent(User, Ability, int)
	 * @param player the {@link AvatarPlayer} whose {@link BendingBoard} is to be updated
	 * @param slot the
	 */
	protected final void onBindChange(AvatarPlayer player, int slot) {
		Game.boardManager().board(player).ifPresent(board -> board.update(slot));
	}

	/**
	 * Updates the {@link BendingBoard} associated with the {@link AvatarPlayer} with starting and ending cooldowns
	 * @see EventBus#postUserCooldownStartEvent(User, Cooldown)
	 * @see EventBus#postUserCooldownEndEvent(User, Cooldown)
	 * @param player the {@link AvatarPlayer} whose {@link BendingBoard} is to be updated
	 * @param cooldown the {@link Cooldown} whose information will be used to update the board
	 * @param starting true if the {@link Cooldown} is being applied, false if it is being removed
	 */
	protected final void onCooldown(AvatarPlayer player, Cooldown cooldown, boolean starting) {
		Game.boardManager().board(player).ifPresent(board -> {
			Ability ability = Ability.byName(cooldown.ability());
			if (ability != null && ability.bindable()) {
				player.binds().entrySet().stream().filter(e -> e.getValue().equals(ability)).forEach(e -> board.update(e.getKey()));
			} else {
				board.updateMisc(cooldown.ability(), starting);
			}
		});
	}

	/**
	 * Updates the {@link BendingBoard} associated with the {@link AvatarPlayer} with newly bound abilities from a preset
	 * @see EventBus#postUserPresetBindEvent(User, Preset)
	 * @param player the {@link AvatarPlayer} whose {@link BendingBoard} is to be updated
	 */
	protected final void onPresetBind(AvatarPlayer player) {
		Game.boardManager().board(player).ifPresent(BendingBoard::update);
	}

}
