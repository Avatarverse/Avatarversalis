package net.avatarverse.avatarversalis.core.game.policy.type;

import java.util.function.Predicate;

import net.avatarverse.avatarversalis.core.game.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.game.user.User;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.RequiredArgsConstructor;

@DefaultAnnotation(NonNull.class)
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
