package net.avatarverse.avatarversalis.core.policy.specific;

import java.util.function.Supplier;

import org.bukkit.Location;

import net.avatarverse.avatarversalis.core.policy.RemovalPolicy;
import net.avatarverse.avatarversalis.core.user.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LiquidPolicy implements RemovalPolicy {

	private final Supplier<Location> location;

	public static LiquidPolicy of(Supplier<Location> location) {
		return new LiquidPolicy(location);
	}

	@Override
	public boolean test(User user) {
		return location.get().getBlock().isLiquid();
	}
}
