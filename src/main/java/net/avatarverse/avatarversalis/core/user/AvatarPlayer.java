package net.avatarverse.avatarversalis.core.user;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import net.avatarverse.avatarversalis.core.ability.Ability;

import lombok.Getter;

public class AvatarPlayer extends LivingEntityUser {

	private static final Map<Integer, Ability> BINDS = new HashMap<>();

	@Getter private final Player player;

	public AvatarPlayer(Player player) {
		super(player);
		this.player = player;
	}

	@Override
	public int currentSlot() {
		return player.getInventory().getHeldItemSlot();
	}

	@Override
	public @Nullable Ability selectedAbility() {
		return BINDS.get(currentSlot());
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
