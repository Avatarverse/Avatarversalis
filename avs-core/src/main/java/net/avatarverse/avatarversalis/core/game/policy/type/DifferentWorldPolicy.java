package net.avatarverse.avatarversalis.core.game.policy.type;

import java.util.function.Supplier;

import net.avatarverse.avatarversalis.core.game.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.platform.World;

public class DifferentWorldPolicy implements EndingPolicy {

	private final World original;
	private final Supplier<World> current;

	private DifferentWorldPolicy(Supplier<World> current) {
		this.original = current.get();
		this.current = current;
	}

	public static DifferentWorldPolicy of(Supplier<World> current) {
		return new DifferentWorldPolicy(current);
	}

	@Override
	public boolean test(User user) {
		return !original.equals(current.get());
	}
}
