package net.avatarverse.avatarversalis.core.game.policy;

import java.util.HashSet;
import java.util.Set;

import net.avatarverse.avatarversalis.core.game.policy.type.ExpirationPolicy;
import net.avatarverse.avatarversalis.core.game.user.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Policies implements EndingPolicy {

	DEAD(User::dead),
	OFFLINE(User::valid),
	SNEAKING(User::sneaking),
	NOT_SNEAKING(u -> !u.sneaking()),
	FLYING(User::flying),
	IN_LIQUID(u -> u.locBlock().liquid() || u.headBlock().liquid()),
	FEET_IN_LIQUID(u -> u.locBlock().liquid()),
	HEAD_IN_LIQUID(u -> u.headBlock().liquid());

	private final EndingPolicy policy;

	public static Builder builder() {
		return new Builder()
				.add(DEAD)
				.add(OFFLINE);
	}

	@Override
	public boolean test(User user) {
		return policy.test(user);
	}

	public static final class Builder {
		private final Set<EndingPolicy> policies;

		private Builder() {
			policies = new HashSet<>();
		}

		public Builder add(EndingPolicy policy) {
			policies.add(policy);
			return this;
		}

		public Builder duration(long duration) {
			policies.add(ExpirationPolicy.of(duration));
			return this;
		}

		public CompositeEndingPolicy build() {
			return new CompositeEndingPolicy(policies);
		}
	}

}
