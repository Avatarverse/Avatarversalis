package net.avatarverse.avatarversalis.core.game.policy.type;

import java.util.function.Supplier;

import net.avatarverse.avatarversalis.core.game.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.util.Vector;
import net.avatarverse.avatarversalis.core.platform.block.Block;
import net.avatarverse.avatarversalis.core.util.Geometry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LiquidPolicy implements EndingPolicy {

	private final Supplier<Location> location;
	private final Supplier<Vector> direction;

	public static LiquidPolicy of(Supplier<Location> location) {
		return new LiquidPolicy(location, Vector::new);
	}

	public static LiquidPolicy of(Supplier<Location> location, Supplier<Vector> direction) {
		return new LiquidPolicy(location, direction);
	}

	@Override
	public boolean test(User user) {
		Block block = location.get().block();
		return block.liquid() || Geometry.diagonalWall(block, direction.get(), Block::liquid);
	}
}
