package net.avatarverse.avatarversalis.core.user;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import lombok.Getter;

public class AvatarPlayer extends LivingEntityUser {

	@Getter private final Player player;

	public AvatarPlayer(Player player) {
		super(player);
		this.player = player;
	}

	public int currentSlot() {
		return player.getInventory().getHeldItemSlot();
	}

	@Override
	public boolean valid() {
		return player.isOnline();
	}

	@Override
	public boolean spectator() {
		return player.getGameMode() == GameMode.SPECTATOR;
	}

	@Override
	public boolean sneaking() {
		return player.isSneaking();
	}

	@Override
	public boolean flying() {
		return player.isFlying();
	}

	@Override
	public boolean onGround() {
		return player.isOnGround();
	}

}
