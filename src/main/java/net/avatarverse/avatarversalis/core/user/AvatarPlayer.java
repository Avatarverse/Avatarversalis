package net.avatarverse.avatarversalis.core.user;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.core.user.preset.Preset;
import net.avatarverse.avatarversalis.util.Geometry;

import edu.umd.cs.findbugs.annotations.ReturnValuesAreNonnullByDefault;
import lombok.Getter;

@ParametersAreNonnullByDefault
@ReturnValuesAreNonnullByDefault
public class AvatarPlayer extends LivingEntityUser {

	@Getter private final Player player;
	@Getter private final Map<String, Preset> presets;

	public AvatarPlayer(Player player) {
		super(player);
		this.player = player;
		this.presets = new HashMap<>();
	}

	public static @Nullable AvatarPlayer byPlayer(Player player) {
		User user = byEntity(player);
		return user != null ? user.as(AvatarPlayer.class) : null;
	}

	public void createPreset(String name) {
		presets.put(name, new Preset(this, name));
	}

	public void deletePreset(Preset preset) {
		presets.remove(preset.name());
	}

	public void bindPreset(Preset preset) {
		binds.clear();
		preset.binds().forEach(binds::put);
	}

	@Override
	public String name() {
		return player.getName();
	}

	@Override
	public int currentSlot() {
		return player.getInventory().getHeldItemSlot();
	}

	@Override
	public @Nullable Ability selectedAbility() {
		return binds.get(currentSlot());
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
