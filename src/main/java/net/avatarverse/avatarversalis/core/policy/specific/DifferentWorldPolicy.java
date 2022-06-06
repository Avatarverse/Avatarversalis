package net.avatarverse.avatarversalis.core.policy.specific;

import java.util.function.Supplier;

import org.bukkit.World;

import net.avatarverse.avatarversalis.core.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.user.User;

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
