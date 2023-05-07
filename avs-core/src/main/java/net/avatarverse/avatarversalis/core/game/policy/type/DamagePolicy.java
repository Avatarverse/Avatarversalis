package net.avatarverse.avatarversalis.core.game.policy.type;

import java.util.function.Predicate;
import java.util.function.Supplier;

import net.avatarverse.avatarversalis.core.game.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.game.user.User;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

@DefaultAnnotation(NonNull.class)
public class DamagePolicy implements EndingPolicy {

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
