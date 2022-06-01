package net.avatarverse.avatarversalis.core.policy.specific;

import java.util.function.Predicate;
import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import net.avatarverse.avatarversalis.core.policy.RemovalPolicy;
import net.avatarverse.avatarversalis.core.user.User;

public class DamagePolicy implements RemovalPolicy {

	private final double damageThreshold;
	private final double originalHealth;
	private final Supplier<Double> currentHealth;
	private final Predicate<User> condition;

	private DamagePolicy(double damageThreshold, Supplier<Double> currentHealth, @Nullable Predicate<User> condition) {
		this.damageThreshold = damageThreshold;
		this.originalHealth = currentHealth.get();
		this.currentHealth = currentHealth;
		this.condition = condition;
	}

	public static DamagePolicy of(double damageThreshold, Supplier<Double> currentHealth) {
		return new DamagePolicy(damageThreshold, currentHealth, null);
	}

	public static DamagePolicy of(double damageThreshold, Supplier<Double> currentHealth, @Nullable Predicate<User> condition) {
		return new DamagePolicy(damageThreshold, currentHealth, condition);
	}

	@Override
	public boolean test(User user) {
		if (damageThreshold <= 0) return false;
		return currentHealth.get() <= originalHealth - damageThreshold && (condition == null || condition.test(user));
	}

}
