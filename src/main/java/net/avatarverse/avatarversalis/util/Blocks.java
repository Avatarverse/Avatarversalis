package net.avatarverse.avatarversalis.util;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.serialize.SerializationException;

import net.avatarverse.avatarversalis.Avatarversalis;
import net.avatarverse.avatarversalis.util.Geometry.Axis;

import edu.umd.cs.findbugs.annotations.ReturnValuesAreNonnullByDefault;

@ParametersAreNonnullByDefault
@ReturnValuesAreNonnullByDefault
public final class Blocks {

	public static List<Material> earthbendables;

	static {
		try {
			earthbendables = Avatarversalis.configManager().config().node("properties", "earth", "earthbendable-blocks").getList(Material.class);
		} catch (SerializationException e) {
			e.printStackTrace();
		}
	}

	public static boolean air(Block block) {
		Material type = block.getType();
		return type == Material.AIR || type == Material.CAVE_AIR || type == Material.VOID_AIR || type == Material.LIGHT;
	}

	public static boolean earth(Block block) {
		Material type = block.getType();
		return earthbendables.contains(type);
	}

	public static boolean fire(Block block) {
		Material type = block.getType();
		return type == Material.FIRE || type == Material.SOUL_FIRE;
	}

	public static boolean water(Block block) {
		Material type = block.getType();
		return type == Material.WATER;
	}

	public static boolean ice(Block block) {
		Material type = block.getType();
		return type == Material.ICE || type == Material.PACKED_ICE || type == Material.BLUE_ICE;
	}

	public static boolean snow(Block block) {
		Material type = block.getType();
		return type == Material.SNOW || type == Material.SNOW_BLOCK || type == Material.POWDER_SNOW;
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
