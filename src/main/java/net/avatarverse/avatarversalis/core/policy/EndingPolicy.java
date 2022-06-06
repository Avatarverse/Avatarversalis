package net.avatarverse.avatarversalis.core.policy;

import java.util.function.Predicate;

import net.avatarverse.avatarversalis.core.user.User;

@FunctionalInterface
public interface EndingPolicy extends Predicate<User> {
	boolean test(User user);
}
