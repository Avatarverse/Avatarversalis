package net.avatarverse.avatarversalis.core.policy.specific;

import java.util.function.Predicate;
import java.util.function.Supplier;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import net.avatarverse.avatarversalis.core.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.user.User;

public class SolidLiquidPolicy implements EndingPolicy {

	private final SolidPolicy solid;
	private final LiquidPolicy liquid;

	private SolidLiquidPolicy(Supplier<Location> location, Supplier<Vector> direction, Predicate<Location> condition) {
		solid = SolidPolicy.of(location, direction, condition);
		liquid = LiquidPolicy.of(location, direction);
	}

	public static SolidLiquidPolicy of(Supplier<Location> location) {
		return new SolidLiquidPolicy(location, Vector::new, null);
	}

	public static SolidLiquidPolicy of(Supplier<Location> location, Supplier<Vector> direction) {
		return new SolidLiquidPolicy(location, direction, null);
	}

	public static SolidLiquidPolicy of(Supplier<Location> location, Supplier<Vector> direction, Predicate<Location> condition) {
		return new SolidLiquidPolicy(location, direction, condition);
	}

	@Override
	public boolean test(User user) {
		return solid.test(user) || liquid.test(user);
	}
}
