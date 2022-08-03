package net.avatarverse.avatarversalis.bending.ability.fire;

import org.spongepowered.configurate.ConfigurationNode;

import net.avatarverse.avatarversalis.bending.Bending;
import net.avatarverse.avatarversalis.core.config.AbilityConfig;
import net.avatarverse.avatarversalis.core.game.ability.Ability;
import net.avatarverse.avatarversalis.core.game.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.game.ability.Activation;
import net.avatarverse.avatarversalis.core.game.attribute.Attribute;
import net.avatarverse.avatarversalis.core.game.attribute.Modifiable;
import net.avatarverse.avatarversalis.core.game.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.game.policy.Policies;
import net.avatarverse.avatarversalis.core.game.policy.specific.ExpirationPolicy;
import net.avatarverse.avatarversalis.core.game.policy.specific.LiquidPolicy;
import net.avatarverse.avatarversalis.core.game.policy.specific.SolidPolicy;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.util.Particles;
import net.avatarverse.avatarversalis.core.util.Sounds;

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
		fire = !user.hasElement(Bending.BLUE_FIRE) ? Particles.fire() : Particles.blueFire();
		sound = Sounds.fire().volume(0.2F);

		policy = Policies.builder()
				.add(ExpirationPolicy.of(config.duration))
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

	public static void register() {
		Ability.builder("Illumination", Bending.FIRE)
				.activation(Activation.ATTACK, Illumination.class)
				.control(Illumination.class, Activation.ATTACK)
				.bindable().build();
	}
}
