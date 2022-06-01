package net.avatarverse.avatarversalis.core.user;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.core.temporary.Cooldown;

import lombok.Getter;
import lombok.Setter;

public abstract class User {

	@Getter protected final UUID uuid;
	@Getter @Setter private boolean toggled;

	public User(UUID uuid) {
		this.uuid = uuid;
		UserManager.USERS.put(uuid, this);
	}

	public boolean isOnCooldown(Ability ability) {
		return cooldown(ability) != null;
	}

	public abstract @Nullable Cooldown cooldown(Ability ability);
	public abstract void addCooldown(Ability ability, Cooldown cooldown);
	public abstract void removeCooldown(Ability ability);
	public abstract boolean canBend(Ability ability);
	public abstract Location location();
	public abstract Location eyeLocation();
	public abstract Block locBlock();
	public abstract Block headBlock();
	public abstract Vector direction();
	public abstract Vector velocity();
	public abstract float yaw();
	public abstract float pitch();
	public abstract World world();
	public abstract boolean dead();
	public abstract boolean valid();
	public abstract boolean spectator();
	public abstract boolean sneaking();
	public abstract boolean flying();
	public abstract boolean onGround();

}
