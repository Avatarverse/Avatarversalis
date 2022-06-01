package net.avatarverse.avatarversalis.core.policy;

import java.util.function.Predicate;

import net.avatarverse.avatarversalis.core.user.User;

@FunctionalInterface
public interface RemovalPolicy extends Predicate<User> {
	boolean test(User user);
}
