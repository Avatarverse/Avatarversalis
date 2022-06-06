package net.avatarverse.avatarversalis.core.user;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.core.temporary.Cooldown;

import lombok.Getter;

public class EntityUser extends User {

	@Getter private final Entity entity;

	public EntityUser(Entity entity) {
		super(entity.getUniqueId());
		this.entity = entity;
	}

	@Override
	public @Nullable Cooldown cooldown(Ability ability) {
		return null;
	}

	@Override
	public boolean canBend(Ability ability) {
		return false;
	}

	@Override
	public int currentSlot() {
		return 0;
	}

	@Override
	public Ability selectedAbility() {
		return null;
	}

	@Override
	public Location location() {
		return entity.getLocation();
	}

	@Override
	public Location eyeLocation() {
		return location();
	}

	@Override
	public Block locBlock() {
		return location().getBlock();
	}

	@Override
	public Block headBlock() {
		return eyeLocation().getBlock();
	}

	@Override
	public Vector direction() {
		return location().getDirection();
	}

	@Override
	public Vector velocity() {
		return entity.getVelocity();
	}

	@Override
	public float yaw() {
		return location().getYaw();
	}

	@Override
	public float pitch() {
		return location().getPitch();
	}

	@Override
	public World world() {
		return entity.getWorld();
	}

	@Override
	public boolean dead() {
		return entity.isDead();
	}

	@Override
	public boolean valid() {
		return entity.isValid();
	}

	@Override
	public boolean spectator() {
		return false;
	}

	@Override
	public boolean sneaking() {
		return true;
	}

	@Override
	public boolean flying() {
		return false;
	}

	@Override
	public boolean onGround() {
		return entity.isOnGround();
	}
}
