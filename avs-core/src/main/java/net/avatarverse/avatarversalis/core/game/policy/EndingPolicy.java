package net.avatarverse.avatarversalis.core.game.policy;

import java.util.function.Predicate;

import net.avatarverse.avatarversalis.core.game.user.User;

@FunctionalInterface
public interface EndingPolicy extends Predicate<User> {
	boolean test(User user);
}
