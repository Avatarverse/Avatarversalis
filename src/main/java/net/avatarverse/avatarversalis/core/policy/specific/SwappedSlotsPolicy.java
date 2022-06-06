package net.avatarverse.avatarversalis.core.policy.specific;

import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;

import net.avatarverse.avatarversalis.core.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SwappedSlotsPolicy implements EndingPolicy {

	private final int originalSlot;
	private final Predicate<Integer> condition;

	public static SwappedSlotsPolicy of(int originalSlot) {
		return of(originalSlot, null);
	}

	public static SwappedSlotsPolicy of(int originalSlot, @Nullable Predicate<Integer> condition) {
		return new SwappedSlotsPolicy(originalSlot, condition);
	}

	@Override
	public boolean test(User user) {
		return originalSlot == user.currentSlot() && (condition != null && condition.test(user.currentSlot()));
	}
}
