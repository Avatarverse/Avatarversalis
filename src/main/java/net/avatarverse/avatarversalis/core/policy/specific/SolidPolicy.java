package net.avatarverse.avatarversalis.core.policy.specific;

import java.util.function.Supplier;

import org.bukkit.Location;

import net.avatarverse.avatarversalis.core.policy.RemovalPolicy;
import net.avatarverse.avatarversalis.core.user.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SolidPolicy implements RemovalPolicy {

	private final Supplier<Location> location;

	public static SolidPolicy of(Supplier<Location> location) {
		return new SolidPolicy(location);
	}

	@Override
	public boolean test(User user) {
		return !location.get().getBlock().isPassable(); // TODO isSolid
	}
}
