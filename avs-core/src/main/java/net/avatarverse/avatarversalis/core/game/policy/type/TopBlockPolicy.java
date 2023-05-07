package net.avatarverse.avatarversalis.core.game.policy.type;

import java.util.function.Predicate;
import java.util.function.Supplier;

import net.avatarverse.avatarversalis.core.game.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.platform.block.Block;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TopBlockPolicy implements EndingPolicy {

	private final Supplier<Block> block;
	private final Predicate<Block> condition;

	public static TopBlockPolicy of(Supplier<Block> block, Predicate<Block> condition) {
		return new TopBlockPolicy(block, condition);
	}

	@Override
	public boolean test(User user) {
		return condition.test(block.get()); // TODO
	}
}
