package net.avatarverse.avatarversalis.bukkit.platform;

import net.avatarverse.avatarversalis.bukkit.util.Types;
import net.avatarverse.avatarversalis.core.platform.util.Vector;
import net.avatarverse.avatarversalis.core.platform.World;
import net.avatarverse.avatarversalis.core.platform.block.Block;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@DefaultAnnotation(NonNull.class)
@RequiredArgsConstructor
@Getter
public class Location implements net.avatarverse.avatarversalis.core.platform.Location {

	private final org.bukkit.Location bukkit;

	@Override
	public World world() {
		return new net.avatarverse.avatarversalis.bukkit.platform.World(bukkit.getWorld());
	}

	@Override
	public double x() {
		return bukkit.getX();
	}

	@Override
	public double y() {
		return bukkit.getY();
	}

	@Override
	public double z() {
		return bukkit.getZ();
	}

	@Override
	public float yaw() {
		return bukkit.getYaw();
	}

	@Override
	public float pitch() {
		return bukkit.getPitch();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.Location world(World world) {
		bukkit.setWorld(((net.avatarverse.avatarversalis.bukkit.platform.World) world).bukkit());
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.Location x(double x) {
		bukkit.setX(x);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.Location y(double y) {
		bukkit.setY(y);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.Location z(double z) {
		bukkit.setZ(z);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.Location yaw(float yaw) {
		bukkit.setYaw(yaw);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.Location pitch(float pitch) {
		bukkit.setPitch(pitch);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.Location add(double x, double y, double z) {
		bukkit.add(x, y, z);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.Location add(net.avatarverse.avatarversalis.core.platform.Location location) {
		bukkit.add(((Location) location).bukkit);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.Location add(Vector vector) {
		bukkit.add(Types.convert(vector));
		return this;
	}

	@Override
	public Block block() {
		return new net.avatarverse.avatarversalis.bukkit.platform.block.Block(bukkit.getBlock());
	}

	@Override
	public int blockX() {
		return bukkit.getBlockX();
	}

	@Override
	public int blockY() {
		return bukkit.getBlockY();
	}

	@Override
	public int blockZ() {
		return bukkit.getBlockZ();
	}

	@SneakyThrows
	@Override
	public net.avatarverse.avatarversalis.core.platform.Location clone() {
		return (net.avatarverse.avatarversalis.core.platform.Location) super.clone();
	}

	@Override
	public double distance(net.avatarverse.avatarversalis.core.platform.Location other) {
		return bukkit.distance(((Location) other).bukkit);
	}

	@Override
	public double distanceSquared(net.avatarverse.avatarversalis.core.platform.Location other) {
		return bukkit.distanceSquared(((Location) other).bukkit);
	}

	@Override
	public Vector direction() {
		return Types.convert(bukkit.getDirection());
	}

	@Override
	public double length() {
		return bukkit.length();
	}

	@Override
	public double lengthSquared() {
		return bukkit.lengthSquared();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.Location subtract(double x, double y, double z) {
		bukkit.subtract(x, y, z);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.Location subtract(net.avatarverse.avatarversalis.core.platform.Location location) {
		bukkit.subtract(((Location) location).bukkit);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.Location subtract(Vector vector) {
		bukkit.subtract(Types.convert(vector));
		return this;
	}

	@Override
	public Vector toVector() {
		return Types.convert(bukkit.toVector());
	}
}
