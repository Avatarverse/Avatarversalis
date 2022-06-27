package net.avatarverse.avatarversalis.core.game.policy.specific;

import java.util.function.Supplier;

import net.avatarverse.avatarversalis.core.game.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.game.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.platform.Location;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProtectedRegionPolicy implements EndingPolicy {

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
