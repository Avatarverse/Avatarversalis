package net.avatarverse.avatarversalis.ability.fire;

import org.spongepowered.configurate.CommentedConfigurationNode;

import net.avatarverse.avatarversalis.config.AbilityConfig;
import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.attribute.Attribute;
import net.avatarverse.avatarversalis.core.attribute.Modifiable;
import net.avatarverse.avatarversalis.core.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.policy.Policies;
import net.avatarverse.avatarversalis.core.policy.specific.SolidLiquidPolicy;
import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.util.Particles;
import net.avatarverse.avatarversalis.util.Sounds;

import lombok.Getter;

@Getter
public class FireStream extends AbilityInstance {

	private static final Config CONFIG = new Config();

	private Config config;
	private EndingPolicy policy;

	private Particles fire;
	private Sounds sound;

	public FireStream(User user) {
		super(user);

		fire = Particles.fire(user).count(config.particleAmount).offset(config.particleSpread);
		sound = Sounds.fire();

		policy = Policies.builder()
				.add(Policies.NOT_SNEAKING)
				.add(SolidLiquidPolicy.of(user::location))
				.add(SolidLiquidPolicy.of(user::eyeLocation)).build();

		start();
	}

	@Override
	protected void load() {
		config = CONFIG.applyModifiers(this);
	}

	@Override
	protected void postStart() {

	}

	@Override
	protected boolean update() {
		if (policy.test(user)) return false;
		
		return true;
	}

	@Getter
	private static class Config extends AbilityConfig {

		@Modifiable(Attribute.COOLDOWN) private long cooldown;
		@Modifiable(Attribute.DURATION) private long duration;
		@Modifiable(Attribute.DAMAGE) private double damage;
		@Modifiable(Attribute.COOLDOWN) private long damageInterval;
		@Modifiable(Attribute.RANGE) private double range;
		@Modifiable(Attribute.FIRE_TICKS) private int fireTicks;
		@Modifiable(Attribute.RADIUS) private double hitRadius;
		@Modifiable(Attribute.RADIUS) private double igniteBlockRadius;
		@Modifiable(Attribute.RADIUS) private double particleSpread;
		@Modifiable(Attribute.AMOUNT) private int particleAmount;

		@Override
		public void onLoad() {
			CommentedConfigurationNode ability = root.node("abilities", "fire", "FireStream");

			cooldown = ability.node("cooldown").getLong();
			duration = ability.node("duration").getLong();
			damage = ability.node("damage").getDouble();
			damageInterval = ability.node("damage-interval").getLong();
			range = ability.node("range").getDouble();
			fireTicks = ability.node("fire-ticks").getInt();
			hitRadius = ability.node("hit-radius").getDouble();
			igniteBlockRadius = ability.node("ignite-block-radius").getDouble();

			CommentedConfigurationNode particle = ability.node("particle");

			particleAmount = particle.node("amount").getInt();
			particleSpread = particle.node("spread").getDouble();
		}
	}
}
