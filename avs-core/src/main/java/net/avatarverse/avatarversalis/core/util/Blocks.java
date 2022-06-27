package net.avatarverse.avatarversalis.core.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import org.spongepowered.configurate.serialize.SerializationException;

import com.google.common.base.Preconditions;

import net.avatarverse.avatarversalis.core.game.Game;
import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.Material;
import net.avatarverse.avatarversalis.core.platform.util.Vector;
import net.avatarverse.avatarversalis.core.platform.block.Block;
import net.avatarverse.avatarversalis.core.platform.block.BlockFace;
import net.avatarverse.avatarversalis.core.util.Geometry.Axis;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

@DefaultAnnotation(NonNull.class)
public final class Blocks {

	public static List<Material> earthbendables;

	static {
		try {
			earthbendables = Game.configManager().config().node("properties", "earth", "earthbendable-blocks").getList(Material.class);
		} catch (SerializationException e) {
			e.printStackTrace();
		}
	}

	public static boolean air(Block block) {
		return block.type().air();
	}

	public static boolean water(Block block) {
		return block.type() == Material.WATER;
	}

	public static boolean earth(Block block) {
		Material type = block.type();
		return earthbendables.contains(type);
	}

	public static boolean lava(Block block) {
		return block.type() == Material.LAVA;
	}

	public static boolean solidLiquid(Block block) {
		return block.solid() || block.liquid();
	}

	public static Collection<Block> nearby(Location location, double radius) {
		return nearby(location, radius, null);
	}

	public static Collection<Block> nearby(Location location, double radius, @Nullable Predicate<Block> filter) {
		Preconditions.checkNotNull(location.world(), "Location must have a world");
		Collection<Block> blocks = new HashSet<>();
		int x = location.blockX(), y = location.blockY(), z = location.blockZ(), r = (int) Math.ceil(radius);
		for (int i = x - r; i <= x + r; i++)
			for (int j = y - r; j <= y +r; j++)
				for (int k = z - r; k <= z + r; k++) {
					Block block = location.world().blockAt(i, j, k);
					if (block.center().distanceSquared(location) <= radius * radius && (filter == null || filter.test(block)))
						blocks.add(block);
				}
		return blocks;
	}

	public static BlockFace faceFromVector(Vector vector, Axis axis) {
		switch (axis) {
		case X -> {
			double x = vector.x();
			if (x != 0) return x > 0 ? BlockFace.EAST : BlockFace.WEST;
			return BlockFace.SELF;
		}
		case Y -> {
			double y = vector.y();
			if (y != 0) return y > 0 ? BlockFace.UP : BlockFace.DOWN;
			return BlockFace.SELF;
		}
		default -> {
			double z = vector.z();
			if (z != 0) return z > 0 ? BlockFace.SOUTH : BlockFace.NORTH;
			return BlockFace.SELF;
		}}
	}
}
