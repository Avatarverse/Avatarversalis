package net.avatarverse.avatarversalis.bukkit.platform.entity;

import java.util.List;
import java.util.UUID;

import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.util.Vector;
import net.avatarverse.avatarversalis.core.platform.World;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@DefaultAnnotation(NonNull.class)
@RequiredArgsConstructor
@Getter
public class Entity implements net.avatarverse.avatarversalis.core.platform.entity.Entity {

	private final org.bukkit.entity.Entity bukkit;

	@Override
	public UUID uuid() {
		return bukkit.getUniqueId();
	}

	@Override
	public String name() {
		return bukkit.getName();
	}

	@Override
	public Location location() {
		return new net.avatarverse.avatarversalis.bukkit.platform.Location(bukkit.getLocation());
	}

	@Override
	public Vector direction() {
		org.bukkit.util.Vector bukkitVec = bukkit.getLocation().getDirection();
		return new Vector(bukkitVec.getX(), bukkitVec.getY(), bukkitVec.getZ());
	}

	@Override
	public float yaw() {
		return location().yaw();
	}

	@Override
	public float pitch() {
		return location().pitch();
	}

	@Override
	public Vector velocity() {
		org.bukkit.util.Vector bukkitVec = bukkit.getVelocity();
		return new Vector(bukkitVec.getX(), bukkitVec.getY(), bukkitVec.getZ());
	}

	@Override
	public double width() {
		return bukkit.getWidth();
	}

	@Override
	public World world() {
		return new net.avatarverse.avatarversalis.bukkit.platform.World(bukkit.getWorld());
	}

	@Override
	public float fallDistance() {
		return bukkit.getFallDistance();
	}

	@Override
	public int fireTicks() {
		return bukkit.getFireTicks();
	}

	@Override
	public int freezeTicks() {
		return bukkit.getFreezeTicks();
	}

	@Override
	public double height() {
		return bukkit.getHeight();
	}

	@Override
	public int maxFireTicks() {
		return bukkit.getMaxFireTicks();
	}

	@Override
	public int maxFreezeTicks() {
		return bukkit.getMaxFreezeTicks();
	}

	@Override
	public void remove() {
		bukkit.remove();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.Entity vehicle() {
		return new Entity(bukkit.getVehicle());
	}

	@Override
	public boolean eject() {
		return bukkit.eject();
	}

	@Override
	public boolean insideVehicle() {
		return bukkit.isInsideVehicle();
	}

	@Override
	public boolean leaveVehicle() {
		return bukkit.leaveVehicle();
	}

	@Override
	public boolean addPassenger(net.avatarverse.avatarversalis.core.platform.entity.Entity passenger) {
		return bukkit.addPassenger(((Entity) passenger).bukkit);
	}

	@Override
	public boolean removePassenger(net.avatarverse.avatarversalis.core.platform.entity.Entity passenger) {
		return bukkit.removePassenger(((Entity) passenger).bukkit);
	}

	@Override
	public List<net.avatarverse.avatarversalis.core.platform.entity.Entity> passengers() {
		return bukkit.getPassengers().stream().map(bkt -> (net.avatarverse.avatarversalis.core.platform.entity.Entity) new Entity(bkt)).toList();
	}

	@Override
	public List<net.avatarverse.avatarversalis.core.platform.entity.Entity> nearbyEntities(double x, double y, double z) {
		return bukkit.getNearbyEntities(x, y, z).stream().map(bkt -> (net.avatarverse.avatarversalis.core.platform.entity.Entity) new Entity(bkt)).toList();
	}

	@Override
	public boolean hasGravity() {
		return bukkit.hasGravity();
	}

	@Override
	public boolean dead() {
		return bukkit.isDead();
	}

	@Override
	public boolean empty() {
		return bukkit.isEmpty();
	}

	@Override
	public boolean glowing() {
		return bukkit.isGlowing();
	}

	@Override
	public boolean invulnerable() {
		return bukkit.isInvulnerable();
	}

	@Override
	public boolean onGround() {
		return bukkit.isOnGround();
	}

	@Override
	public boolean silent() {
		return bukkit.isSilent();
	}

	@Override
	public boolean valid() {
		return bukkit.isValid();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.Entity fallDistance(float distance) {
		bukkit.setFallDistance(distance);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.Entity fireTicks(int ticks) {
		bukkit.setFireTicks(ticks);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.Entity freezeTicks(int ticks) {
		bukkit.setFreezeTicks(ticks);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.Entity glowing(boolean glowing) {
		bukkit.setGlowing(glowing);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.Entity gravity(boolean gravity) {
		bukkit.setGravity(gravity);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.Entity invulnerable(boolean invulnerable) {
		bukkit.setInvulnerable(invulnerable);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.Entity silent(boolean silent) {
		bukkit.setSilent(silent);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.Entity ticksLived(int value) {
		bukkit.setTicksLived(value);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.Entity velocity(Vector velocity) {
		bukkit.setVelocity(new org.bukkit.util.Vector(velocity.x(), velocity.y(), velocity.z()));
		return this;
	}

	@Override
	public boolean teleport(net.avatarverse.avatarversalis.core.platform.entity.Entity destination) {
		return bukkit.teleport(((Entity) destination).bukkit);
	}

	@Override
	public boolean teleport(Location location) {
		return bukkit.teleport(((net.avatarverse.avatarversalis.bukkit.platform.Location) location).bukkit());
	}
}
