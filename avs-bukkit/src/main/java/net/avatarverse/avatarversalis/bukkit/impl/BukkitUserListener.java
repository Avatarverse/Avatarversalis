package net.avatarverse.avatarversalis.bukkit.impl;

import java.util.Optional;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.avatarverse.avatarversalis.bukkit.platform.entity.Player;
import net.avatarverse.avatarversalis.core.game.UserListener;
import net.avatarverse.avatarversalis.core.game.user.AvatarPlayer;

public class BukkitUserListener extends UserListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		onJoin(new Player(event.getPlayer()));
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		onLeave(new Player(event.getPlayer()));
	}

	@EventHandler
	public void onChangeWorld(PlayerChangedWorldEvent event) {
		Player player = new Player(event.getPlayer());
		Optional.ofNullable(AvatarPlayer.of(player)).ifPresent(this::onChangeWorld);
	}

}
