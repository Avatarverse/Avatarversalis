package net.avatarverse.avatarversalis.core.game.policy.type;

import java.util.function.Predicate;
import java.util.function.Supplier;

import net.avatarverse.avatarversalis.core.game.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.util.Vector;
import net.avatarverse.avatarversalis.core.platform.block.Block;
import net.avatarverse.avatarversalis.core.util.Geometry;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@DefaultAnnotation(NonNull.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SolidPolicy implements EndingPolicy {

	private final Supplier<Location> location;
	private final Supplier<Vector> direction;
	private final Predicate<Location> condition;

	public static SolidPolicy of(Supplier<Location> location) {
		return new SolidPolicy(location, Vector::new, null);
	}

	public static SolidPolicy of(Supplier<Location> location, Supplier<Vector> direction) {
		return new SolidPolicy(location, direction, null);
	}

	public static SolidPolicy of(Supplier<Location> location, Supplier<Vector> direction, @Nullable Predicate<Location> condition) {
		return new SolidPolicy(location, direction, condition);
	}

	@Override
	public boolean test(User user) {
		Block block = location.get().block();
		return (!block.solid() || Geometry.diagonalWall(block, direction.get())) && (condition == null || condition.test(location.get()));
	}
}
