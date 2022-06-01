package net.avatarverse.avatarversalis.core.policy.specific;

import java.util.function.Supplier;

import org.bukkit.Location;

import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.policy.RemovalPolicy;
import net.avatarverse.avatarversalis.core.user.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProtectedRegionPolicy implements RemovalPolicy {

	private final AbilityInstance ability;
	private final Supplier<Location> location;

	public static ProtectedRegionPolicy of(AbilityInstance ability, Supplier<Location> location) {
		return new ProtectedRegionPolicy(ability, location);
	}

	@Override
	public boolean test(User user) {
		return false; // TODO
	}
}
