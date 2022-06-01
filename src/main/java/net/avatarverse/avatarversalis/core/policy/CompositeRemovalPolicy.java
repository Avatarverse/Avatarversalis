package net.avatarverse.avatarversalis.core.policy;

import java.util.Set;

import net.avatarverse.avatarversalis.core.user.User;

public class CompositeRemovalPolicy implements RemovalPolicy {

	private final Set<RemovalPolicy> policies;

	CompositeRemovalPolicy(Set<RemovalPolicy> policies) {
		this.policies = policies;
	}

	@Override
	public boolean test(User user) {
		return user == null || policies.stream().anyMatch(p -> p.test(user));
	}

}
