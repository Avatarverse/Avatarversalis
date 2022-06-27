package net.avatarverse.avatarversalis.core.game.user;

import java.util.HashMap;
import java.util.Map;

import net.avatarverse.avatarversalis.core.game.Game;
import net.avatarverse.avatarversalis.core.game.ability.Ability;
import net.avatarverse.avatarversalis.core.game.user.preset.Preset;
import net.avatarverse.avatarversalis.core.platform.GameMode;
import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.entity.Player;
import net.avatarverse.avatarversalis.core.util.Geometry;

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
		User user = byId(player.uuid());
		return user != null ? user.as(AvatarPlayer.class) : null;
	}

	public void createPreset(String name) {
		Preset preset = new Preset(this, name);
		if (!Game.eventBus().postUserPresetCreateEvent(this, preset)) return;
		presets.put(name, new Preset(this, name));
	}

	public void deletePreset(Preset preset) {
		if (!Game.eventBus().postUserPresetDeleteEvent(this, preset)) return;
		presets.remove(preset.name());
	}

	public void bindPreset(Preset preset) {
		if (!Game.eventBus().postUserPresetBindEvent(this, preset)) return;
		preset.binds().forEach((slot, ability) -> {
			if (canBend(ability))
				binds.put(slot, ability);
		});
	}

	@Override
	public String name() {
		return player.name();
	}

	@Override
	public int currentSlot() {
		return player.heldItemSlot();
	}

	@Override
	public void currentSlot(int slot) {
		player.heldItemSlot(slot);
	}

	@Override
	public @Nullable Ability selectedAbility() {
		return binds.get(currentSlot());
	}

	@Override
	public Location handLocation() {
		return Geometry.move(location(), 0.55, player.mainHand()).add(0, 1.2, 0);
	}

	@Override
	public boolean valid() {
		return player.online();
	}

	@Override
	public boolean spectator() {
		return player.gameMode() == GameMode.SPECTATOR;
	}

	@Override
	public boolean sneaking() {
		return player.sneaking();
	}

	@Override
	public boolean flying() {
		return player.flying();
	}

	@Override
	@SuppressWarnings("deprecation")
	public boolean onGround() {
		return player.onGround();
	}

}
