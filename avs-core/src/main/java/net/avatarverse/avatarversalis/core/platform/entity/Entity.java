package net.avatarverse.avatarversalis.core.platform.entity;

import java.util.List;
import java.util.UUID;

import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.util.Vector;
import net.avatarverse.avatarversalis.core.platform.World;

public interface Entity {
	UUID uuid();
	String name();
	Location location();
	Vector direction();
	float yaw();
	float pitch();
	Vector velocity();
	double width();
	World world();
	float fallDistance();
	int fireTicks();
	int freezeTicks();
	double height();
	int maxFireTicks();
	int maxFreezeTicks();
	void remove();
	Entity vehicle();
	boolean eject();
	boolean insideVehicle();
	boolean leaveVehicle();
	boolean addPassenger(Entity passenger);
	boolean removePassenger(Entity passenger);
	List<Entity> passengers();
	List<Entity> nearbyEntities(double x, double y, double z);
	boolean hasGravity();
	boolean dead();
	boolean empty();
	boolean glowing();
	boolean invulnerable();
	boolean onGround();
	boolean silent();
	boolean valid();
	Entity fallDistance(float distance);
	Entity fireTicks(int ticks);
	Entity freezeTicks(int ticks);
	Entity glowing(boolean glowing);
	Entity gravity(boolean gravity);
	Entity invulnerable(boolean invulnerable);
	Entity silent(boolean silent);
	Entity ticksLived(int value);
	Entity velocity(Vector velocity);
	boolean teleport(Entity destination);
	boolean teleport(Location location);
}
