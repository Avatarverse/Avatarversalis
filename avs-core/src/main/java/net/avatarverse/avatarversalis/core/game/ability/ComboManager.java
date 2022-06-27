package net.avatarverse.avatarversalis.core.game.ability;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import net.avatarverse.avatarversalis.core.game.Game;
import net.avatarverse.avatarversalis.core.game.user.User;

public final class ComboManager {

	private static final int CACHE_SIZE = 12;

	private final LoadingCache<User, Deque<ComboStep>> cache;

	public ComboManager() {
		cache = Caffeine.newBuilder()
				.expireAfterAccess(Duration.ofSeconds(10))
				.build(user -> new ArrayDeque<>(CACHE_SIZE));
	}

	public void addStep(User user, Ability ability, Activation activation) {
		Deque<ComboStep> deque = cache.get(user);
		if (deque.size() >= CACHE_SIZE)
			deque.removeFirst();
		deque.addLast(new ComboStep(ability.name(), activation));

		for (Ability combo : AbilityManager.COMBOS) {
			if (match(combo, deque)) {
				Game.controller().activate(user, combo, Activation.COMBO);
			}
		}
	}

	private boolean match(Ability ability, Deque<ComboStep> deque) {
		List<ComboStep> expectedSteps = ability.comboSteps();
		List<ComboStep> actualSteps = new ArrayList<>(deque);
		if (actualSteps.size() < expectedSteps.size())
			return false;

		for (int i = 0; i <= expectedSteps.size(); i++) {
			ComboStep expected = expectedSteps.get(i);
			ComboStep actual = actualSteps.get(actualSteps.size() - 1 - i);
			if (!expected.equals(actual))
				return false;
		}

		return true;
	}

}
