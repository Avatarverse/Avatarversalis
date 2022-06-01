package net.avatarverse.avatarversalis.core.policy.specific;

import net.avatarverse.avatarversalis.core.policy.RemovalPolicy;
import net.avatarverse.avatarversalis.core.user.User;

public class ExpirationPolicy implements RemovalPolicy {

	private final long expireTime;
	private final boolean finite;

	private ExpirationPolicy(long duration) {
		expireTime = System.currentTimeMillis() + duration;
		finite = duration > 0;
	}

	public static ExpirationPolicy of(long duration) {
		return new ExpirationPolicy(duration);
	}

	@Override
	public boolean test(User user) {
		return finite && System.currentTimeMillis() > expireTime;
	}
}
