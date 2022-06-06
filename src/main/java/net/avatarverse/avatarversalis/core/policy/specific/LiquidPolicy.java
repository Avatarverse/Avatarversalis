package net.avatarverse.avatarversalis.core.policy.specific;

import java.util.function.Supplier;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import net.avatarverse.avatarversalis.core.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.util.Blocks;
import net.avatarverse.avatarversalis.util.Geometry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LiquidPolicy implements EndingPolicy {

	private final Supplier<Location> location;
	private final Supplier<Vector> direction;

	public static LiquidPolicy of(Supplier<Location> location, Supplier<Vector> direction) {
		return new LiquidPolicy(location, direction);
	}

	@Override
	public boolean test(User user) {
		Block block = location.get().getBlock();
		return block.isLiquid() || Geometry.diagonalWall(block, direction.get(), Blocks::liquid);
	}
}
