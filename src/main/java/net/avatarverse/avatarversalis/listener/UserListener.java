package net.avatarverse.avatarversalis.listener;

import java.util.Optional;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.avatarverse.avatarversalis.Avatarversalis;
import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.core.board.BendingBoardManager.BendingBoard;
import net.avatarverse.avatarversalis.core.user.AvatarPlayer;
import net.avatarverse.avatarversalis.event.user.UserBindChangeEvent;
import net.avatarverse.avatarversalis.event.user.UserCooldownEvent;
import net.avatarverse.avatarversalis.event.user.UserCooldownEvent.Result;
import net.avatarverse.avatarversalis.event.user.UserPresetEvent;

/**
 * Listener for handling player related events
 */
public class UserListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {

	}

	@EventHandler
	public void onLeave(PlayerQuitEvent event) {

	}

	@EventHandler
	public void onChangeWorld(PlayerChangedWorldEvent event) {
		Optional.ofNullable(AvatarPlayer.of(event.getPlayer()))
				.flatMap(player -> Avatarversalis.game().boardManager().board(player))
				.ifPresent(BendingBoard::update);
	}

	@EventHandler
	public void onSlotChange(PlayerItemHeldEvent event) {
		Optional.ofNullable(AvatarPlayer.of(event.getPlayer()))
				.flatMap(player -> Avatarversalis.game().boardManager().board(player))
				.ifPresent(board -> board.switchSlot(event.getNewSlot()));
	}

	@EventHandler
	public void onBindChange(UserBindChangeEvent event) {
		if (!(event.user() instanceof AvatarPlayer player)) return;
		Avatarversalis.game().boardManager().board(player).ifPresent(board -> board.update(event.slot()));
	}

	@EventHandler
	public void onCooldown(UserCooldownEvent event) {
		if (!(event.user() instanceof AvatarPlayer player)) return;
		Avatarversalis.game().boardManager().board(player).ifPresent(board -> {
		    Ability ability = Ability.byName(event.cooldown().ability());
            if (ability != null && ability.bindable()) {
                player.binds().entrySet().stream().filter(e -> e.getValue().equals(ability)).forEach(e -> board.update(e.getKey()));
            } else {
                board.updateMisc(event.cooldown().ability(), event.result() == Result.START);
            }
        });
	}

	@EventHandler
	public void onPresetBind(UserPresetEvent event) {
		if (!(event.user() instanceof AvatarPlayer player) || event.result() != UserPresetEvent.Result.BIND) return;
		Avatarversalis.game().boardManager().board(player).ifPresent(BendingBoard::update);
	}

}
