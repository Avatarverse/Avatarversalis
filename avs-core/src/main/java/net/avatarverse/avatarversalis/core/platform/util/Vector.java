package net.avatarverse.avatarversalis.core.platform.util;

import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.World;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

@RequiredArgsConstructor
@Getter
@Setter
public class Vector implements Cloneable {

	private final double x, y, z;

	public Vector() {
		x = 0;
		y = 0;
		z = 0;
	}

	public Vector add(Vector other) {
		return null;
	}

	public int blockX() {
		return 0;
	}

	public int blockY() {
		return 0;
	}

	public int blockZ() {
		return 0;
	}

	@SneakyThrows
	public Vector clone() {
		return (Vector) super.clone();
	}

	public Vector crossProduct(Vector other) {
		return this;
	}

	public double distance(Vector other) {
		return 0;
	}

	public double distanceSquared(Vector other) {
		return 0;
	}

	public Vector divide(Vector other) {
		return this;
	}

	public double dot(Vector other) {
		return 0;
	}

	public Vector midpoint(Vector other) {
		return this;
	}

	public Vector multiply(double factor) {
		return this;
	}

	public Vector multiply(Vector other) {
		return this;
	}

	public Vector normalize() {
		return this;
	}

	public boolean normalized() {
		return false;
	}

	public Vector rotateAroundAxis(Vector axis, double angle) {
		return this;
	}

	public Vector rotateAroundNonUnitAxis(Vector axis, double angle) {
		return this;
	}

	public Vector rotateAroundX(double angle) {
		return this;
	}

	public Vector rotateAroundY(double angle) {
		return this;
	}

	public Vector rotateAroundZ(double angle) {
		return this;
	}

	public Location toLocation(World world) {
		return null;
	}

	public Location toLocation(World world, float yaw, float pitch) {
		return null;
	}

	public String toString() {
		return null;
	}

	public boolean equals(Object obj) {
		return false;
	}
}
