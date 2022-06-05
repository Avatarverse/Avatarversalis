package net.avatarverse.avatarversalis.ability.fire;

import org.bukkit.Location;

import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.ability.Activation;
import net.avatarverse.avatarversalis.core.policy.Policies;
import net.avatarverse.avatarversalis.core.policy.RemovalPolicy;
import net.avatarverse.avatarversalis.core.policy.specific.ExpirationPolicy;
import net.avatarverse.avatarversalis.core.policy.specific.LiquidPolicy;
import net.avatarverse.avatarversalis.core.policy.specific.ProtectedRegionPolicy;
import net.avatarverse.avatarversalis.core.policy.specific.RangePolicy;
import net.avatarverse.avatarversalis.core.policy.specific.SolidPolicy;
import net.avatarverse.avatarversalis.core.user.User;

public class FireBlast extends AbilityInstance {

	private RemovalPolicy policy;
	private Location location;

	public FireBlast(User user, Ability ability) {
		super(user, ability);
	}

	@Override
	protected boolean start(Activation method) {
		policy = Policies.builder()
				.duration(2000)
				.add(LiquidPolicy.of(() -> location))
				.add(ProtectedRegionPolicy.of(this, () -> location))
				.add(RangePolicy.of(20, user.location(), () -> location))
				.add(SolidPolicy.of(() -> location)).build();
		return true;
	}

	@Override
	public boolean update() {
		return !policy.test(user);
	}

	@Override
	protected void end() {

	}

	@Override
	protected void load() {
		
	}
}
