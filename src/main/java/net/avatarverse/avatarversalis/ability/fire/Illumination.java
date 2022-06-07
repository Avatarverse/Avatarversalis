package net.avatarverse.avatarversalis.ability.fire;

import org.bukkit.Location;
import org.spongepowered.configurate.ConfigurationNode;

import net.avatarverse.avatarversalis.config.AbilityConfig;
import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.attribute.Attribute;
import net.avatarverse.avatarversalis.core.attribute.Modifiable;
import net.avatarverse.avatarversalis.core.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.policy.Policies;
import net.avatarverse.avatarversalis.core.policy.specific.LiquidPolicy;
import net.avatarverse.avatarversalis.core.policy.specific.SolidPolicy;
import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.util.Particles;
import net.avatarverse.avatarversalis.util.Sounds;

import lombok.Getter;

@Getter
public class Illumination extends AbilityInstance {

	private static final Config CONFIG = new Config();

	private Config config;
	private final EndingPolicy policy;

	private Location location;
	private final Particles fire;
	private final Sounds sound;

	public Illumination(User user) {
		super(user);

		location = user.handLocation();
		fire = Particles.fire(user);
		sound = Sounds.fire().volume(0.2F);

		policy = Policies.builder()
				.add(SolidPolicy.of(() -> location))
				.add(LiquidPolicy.of(() -> location)).build();

		start();
	}

	@Override
	protected void load() {
		config = CONFIG.applyModifiers(this);
	}

	@Override
	protected void postStart() {
		user.addCooldown(ability, config.cooldown);
	}

	@Override
	protected boolean update() {
		if (policy.test(user)) return false;
		location = user.handLocation();
		fire.spawn(location);
		sound.play(location, 0.25);
		return true;
	}

	@Override
	public void onAttack() {
		end();
	}

	@Getter
	private static class Config extends AbilityConfig {

		@Modifiable(Attribute.COOLDOWN) private long cooldown;
		@Modifiable(Attribute.DURATION) private long duration;

		@Override
		public void onLoad() {
			ConfigurationNode ability = root.node("abilities", "fire", "Illumination");

			cooldown = ability.node("cooldown").getLong();
			duration = ability.node("duration").getLong();
		}
	}
}
