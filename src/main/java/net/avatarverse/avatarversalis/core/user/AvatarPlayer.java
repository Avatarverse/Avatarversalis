package net.avatarverse.avatarversalis.core.user;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.util.Geometry;

import edu.umd.cs.findbugs.annotations.ReturnValuesAreNonnullByDefault;
import lombok.Getter;

@ParametersAreNonnullByDefault
@ReturnValuesAreNonnullByDefault
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
	public Location handLocation() {
		return Geometry.move(location(), 0.55, player.getMainHand()).add(0, 1.2, 0);
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
