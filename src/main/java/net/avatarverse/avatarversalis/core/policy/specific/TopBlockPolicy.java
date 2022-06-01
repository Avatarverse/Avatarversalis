package net.avatarverse.avatarversalis.core.policy.specific;

import java.util.function.Predicate;
import java.util.function.Supplier;

import org.bukkit.block.Block;

import net.avatarverse.avatarversalis.core.policy.RemovalPolicy;
import net.avatarverse.avatarversalis.core.user.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TopBlockPolicy implements RemovalPolicy {

	private final Supplier<Block> block;
	private final Predicate<Block> condition;

	public static TopBlockPolicy of(Supplier<Block> block, Predicate<Block> condition) {
		return new TopBlockPolicy(block, condition);
	}

	@Override
	public boolean test(User user) {
		return condition.test(block.get());
	}
}
