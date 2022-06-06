package net.avatarverse.avatarversalis.core.policy.specific;

import java.util.function.Predicate;
import java.util.function.Supplier;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

import net.avatarverse.avatarversalis.core.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.util.Blocks;
import net.avatarverse.avatarversalis.util.Geometry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SolidPolicy implements EndingPolicy {

	private final Supplier<Location> location;
	private final Supplier<Vector> direction;
	private final Predicate<Location> condition;

	public static SolidPolicy of(Supplier<Location> location, Supplier<Vector> direction) {
		return new SolidPolicy(location, direction, null);
	}

	public static SolidPolicy of(Supplier<Location> location, Supplier<Vector> direction, @Nullable Predicate<Location> condition) {
		return new SolidPolicy(location, direction, condition);
	}

	@Override
	public boolean test(User user) {
		Block block = location.get().getBlock();
		return (!Blocks.solid(block) || Geometry.diagonalWall(block, direction.get())) && (condition == null || condition.test(location.get()));
	}
}
