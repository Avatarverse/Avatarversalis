package net.avatarverse.avatarversalis.core.policy;

import java.util.Set;

import net.avatarverse.avatarversalis.core.user.User;

public class CompositeEndingPolicy implements EndingPolicy {

	private final Set<EndingPolicy> policies;

	CompositeEndingPolicy(Set<EndingPolicy> policies) {
		this.policies = policies;
	}

	public CompositeEndingPolicy add(EndingPolicy policy) {
		policies.add(policy);
		return this;
	}

	public CompositeEndingPolicy remove(Class<? extends EndingPolicy> clazz) {
		policies.removeIf(policy -> policy.getClass().equals(clazz));
		return this;
	}

	@Override
	public boolean test(User user) {
		return user == null || policies.stream().anyMatch(p -> p.test(user));
	}

}
