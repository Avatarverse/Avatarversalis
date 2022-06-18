package net.avatarverse.avatarversalis.core.user;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.core.user.preset.Preset;
import net.avatarverse.avatarversalis.event.user.UserPresetEvent;
import net.avatarverse.avatarversalis.event.user.UserPresetEvent.Result;
import net.avatarverse.avatarversalis.util.Geometry;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
public class AvatarPlayer extends LivingEntityUser {

	@Getter private final Player player;
	@Getter private final Map<String, Preset> presets;

	public AvatarPlayer(Player player) {
		super(player);
		this.player = player;
		this.presets = new HashMap<>();
	}

	public static @Nullable AvatarPlayer of(Player player) {
		User user = byId(player.getUniqueId());
		return user != null ? user.as(AvatarPlayer.class) : null;
	}

	public void createPreset(String name) {
		Preset preset = new Preset(this, name);
		if (new UserPresetEvent(this, preset, Result.CREATE).call().isCancelled()) return;
		presets.put(name, new Preset(this, name));
	}

	public void deletePreset(Preset preset) {
		if (new UserPresetEvent(this, preset, Result.DELETE).call().isCancelled()) return;
		presets.remove(preset.name());
	}

	public void bindPreset(Preset preset) {
		if (new UserPresetEvent(this, preset, Result.BIND).call().isCancelled()) return;
		preset.binds().forEach((slot, ability) -> {
			if (canBend(ability))
				binds.put(slot, ability);
		});
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
	public void currentSlot(int slot) {
		player.getInventory().setHeldItemSlot(slot);
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
	@SuppressWarnings("deprecation")
	public boolean onGround() {
		return player.isOnGround();
	}

}
