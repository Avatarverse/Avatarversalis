package net.avatarverse.avatarversalis.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;

import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

import net.avatarverse.avatarversalis.util.Geometry.Axis;

public final class Blocks {

	public static boolean air(Block block) {
		Material type = block.getType();
		return type == Material.AIR || type == Material.CAVE_AIR || type == Material.VOID_AIR || type == Material.LIGHT;
	}

	public static boolean liquid(Block block) {
		return block.isLiquid();
	}

	public static boolean solidLiquid(Block block) {
		return solid(block) || block.isLiquid();
	}

	public static boolean solid(Block block) {
		return block.getType().isSolid();
	}

	public static Location center(Block block) {
		return block.getLocation().add(0.5, 0.5, 0.5);
	}

	public static Collection<Block> nearby(Location location, double radius) {
		return nearby(location, radius, null);
	}

	public static Collection<Block> nearby(Location location, double radius, @Nullable Predicate<Block> filter) {
		Validate.notNull(location.getWorld(), "Location must have a world");
		Collection<Block> blocks = new HashSet<>();
		int x = location.getBlockX(), y = location.getBlockY(), z = location.getBlockZ(), r = (int) Math.ceil(radius);
		for (int i = x - r; i <= x + r; i++)
			for (int j = y - r; j <= y +r; j++)
				for (int k = z - r; k <= z + r; k++) {
					Block block = location.getWorld().getBlockAt(i, j, k);
					if (center(block).distanceSquared(location) <= radius * radius && (filter == null || filter.test(block)))
						blocks.add(block);
				}
		return blocks;
	}

	public static BlockFace faceFromVector(Vector vector, Axis axis) {
		switch (axis) {
		case X -> {
			double x = vector.getX();
			if (x != 0) return x > 0 ? BlockFace.EAST : BlockFace.WEST;
			return BlockFace.SELF;
		}
		case Y -> {
			double y = vector.getY();
			if (y != 0) return y > 0 ? BlockFace.UP : BlockFace.DOWN;
			return BlockFace.SELF;
		}
		default -> {
			double z = vector.getZ();
			if (z != 0) return z > 0 ? BlockFace.SOUTH : BlockFace.NORTH;
			return BlockFace.SELF;
		}}
	}
}
