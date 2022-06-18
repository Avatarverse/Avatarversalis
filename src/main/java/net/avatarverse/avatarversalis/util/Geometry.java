package net.avatarverse.avatarversalis.util;

import java.util.function.Predicate;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.MainHand;
import org.bukkit.util.Vector;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
public final class Geometry {

	public enum Axis {
		X, Y, Z
	}

	public static boolean diagonalWall(Block block, Vector direction) {
		return diagonalWall(block, direction, Blocks::solid);
	}

	public static boolean diagonalWall(Block block, Vector direction, Predicate<Block> isWall) {
		boolean[] nearbyWall = {
				isWall.test(block.getRelative(Blocks.faceFromVector(direction, Axis.X))),
				isWall.test(block.getRelative(Blocks.faceFromVector(direction, Axis.Y))),
				isWall.test(block.getRelative(Blocks.faceFromVector(direction, Axis.Z)))
		};
		boolean xy = nearbyWall[0] && nearbyWall[1];
		boolean xz = nearbyWall[0] && nearbyWall[2];
		boolean yz = nearbyWall[1] && nearbyWall[2];
		return xy || xz || yz;
	}

	public static Location left(Location location, double distance) {
		return move(location, distance, MainHand.LEFT);
	}

	public static Location right(Location location, double distance) {
		return move(location, distance, MainHand.RIGHT);
	}

	public static Location move(Location location, double distance, MainHand hand) {
		location = location.clone();
		location.setYaw(Location.normalizeYaw(location.getYaw() + (hand == MainHand.RIGHT ? -90 : 90)));
		return location.add(location.getDirection().multiply(distance));
	}

}
