package net.avatarverse.avatarversalis.core.policy;

import java.util.HashSet;
import java.util.Set;

import net.avatarverse.avatarversalis.core.policy.specific.ExpirationPolicy;
import net.avatarverse.avatarversalis.core.user.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Policies implements RemovalPolicy {

	DEAD(User::dead),
	OFFLINE(User::valid),
	SNEAKING(User::sneaking),
	NOT_SNEAKING(u -> !u.sneaking()),
	FLYING(User::flying),
	IN_LIQUID(u -> u.locBlock().isLiquid() || u.headBlock().isLiquid()),
	FEET_IN_LIQUID(u -> u.locBlock().isLiquid()),
	HEAD_IN_LIQUID(u -> u.headBlock().isLiquid());

	private final RemovalPolicy policy;

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
		private final Set<RemovalPolicy> policies;

		private Builder() {
			policies = new HashSet<>();
		}

		public Builder add(RemovalPolicy policy) {
			policies.add(policy);
			return this;
		}

		public Builder duration(long duration) {
			policies.add(ExpirationPolicy.of(duration));
			return this;
		}

		public CompositeRemovalPolicy build() {
			return new CompositeRemovalPolicy(policies);
		}
	}

}
