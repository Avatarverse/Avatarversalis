package net.avatarverse.avatarversalis.core.user;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import net.avatarverse.avatarversalis.core.ability.Ability;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
public class EntityUser extends User {

	@Getter private final Entity entity;
	private int currentSlot;

	public EntityUser(Entity entity) {
		super(entity.getUniqueId());
		this.entity = entity;
	}

	public static @Nullable EntityUser of(Entity entity) {
		User user = byId(entity.getUniqueId());
		return user != null ? user.as(EntityUser.class) : null;
	}

	@Override
	public String name() {
		return entity.getName();
	}

	@Override
	public boolean canBend(Ability ability) {
		return false;
	}

	@Override
	public int currentSlot() {
		return currentSlot;
	}

	@Override
	public void currentSlot(int slot) {
		this.currentSlot = slot;
	}

	@Override
	public @Nullable Ability selectedAbility() {
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
	public Location handLocation() {
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
