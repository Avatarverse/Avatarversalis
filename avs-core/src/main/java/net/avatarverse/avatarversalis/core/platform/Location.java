package net.avatarverse.avatarversalis.core.platform;

import net.avatarverse.avatarversalis.core.platform.block.Block;
import net.avatarverse.avatarversalis.core.platform.util.Vector;

public interface Location {
	World world();
	double x();
	double y();
	double z();
	float yaw();
	float pitch();
	Location world(World world);
	Location x(double x);
	Location y(double y);
	Location z(double z);
	Location yaw(float yaw);
	Location pitch(float pitch);

	Location add(double x, double y, double z);
	Location add(Location location);
	Location add(Vector vector);
	Block block();
	int blockX();
	int blockY();
	int blockZ();
	Location clone();
	double distance(Location other);
	double distanceSquared(Location other);
	Vector direction();
	double length();
	double lengthSquared();
	Location subtract(double x, double y, double z);
	Location subtract(Location location);
	Location subtract(Vector vector);
	Vector toVector();
	String toString();
	boolean equals(Object obj);

	static float normalizeYaw(float yaw) {
		return 0;
	}

	static float normalizePitch(float pitch) {
		return 0;
	}
}
