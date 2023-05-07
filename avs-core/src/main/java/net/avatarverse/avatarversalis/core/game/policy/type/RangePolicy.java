package net.avatarverse.avatarversalis.core.game.policy.type;

import java.util.function.Predicate;
import java.util.function.Supplier;

import net.avatarverse.avatarversalis.core.game.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.platform.Location;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

@DefaultAnnotation(NonNull.class)
public class RangePolicy implements EndingPolicy {

	private final double range;
	private final Location origin;
	private final Supplier<Location> current;
	private final Predicate<User> condition;

	private RangePolicy(double range, Location origin, Supplier<Location> current, @Nullable Predicate<User> condition) {
		this.range = Math.abs(range);
		this.origin = origin;
		this.current = current;
		this.condition = condition;
	}

	public static RangePolicy of(double range, Location origin, Supplier<Location> current) {
		return new RangePolicy(range, origin, current, null);
	}

	public static RangePolicy of(double range, Location origin, Supplier<Location> current, @Nullable Predicate<User> condition) {
		return new RangePolicy(range, origin, current, condition);
	}

	@Override
	public boolean test(User user) {
		return current.get().distanceSquared(origin) >= range * range && (condition == null || condition.test(user));
	}
}
