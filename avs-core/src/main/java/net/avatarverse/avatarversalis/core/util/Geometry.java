package net.avatarverse.avatarversalis.core.util;

import java.util.function.Predicate;

import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.inventory.MainHand;
import net.avatarverse.avatarversalis.core.platform.util.Vector;
import net.avatarverse.avatarversalis.core.platform.block.Block;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
public final class Geometry {

	public enum Axis {
		X, Y, Z
	}

	public static boolean diagonalWall(Block block, Vector direction) {
		return diagonalWall(block, direction, Block::solid);
	}

	public static boolean diagonalWall(Block block, Vector direction, Predicate<Block> isWall) {
		boolean[] nearbyWall = {
				isWall.test(block.relative(Blocks.faceFromVector(direction, Axis.X))),
				isWall.test(block.relative(Blocks.faceFromVector(direction, Axis.Y))),
				isWall.test(block.relative(Blocks.faceFromVector(direction, Axis.Z)))
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
		location.yaw(Location.normalizeYaw(location.yaw() + (hand == MainHand.RIGHT ? -90 : 90)));
		return location.add(location.direction().multiply(distance));
	}

}
