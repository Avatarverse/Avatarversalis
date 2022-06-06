package net.avatarverse.avatarversalis.util;

import java.util.function.Predicate;

import org.bukkit.block.Block;
import org.bukkit.util.Vector;

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
		boolean xz = nearbyWall[0] && nearbyWall[2];
		boolean xy = nearbyWall[0] && nearbyWall[1];
		boolean yz = nearbyWall[1] && nearbyWall[2];
		return xz || xy || yz;
	}

}
