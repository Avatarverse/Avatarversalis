package net.avatarverse.avatarversalis.ability.fire;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.spongepowered.configurate.CommentedConfigurationNode;

import net.avatarverse.avatarversalis.config.AbilityConfig;
import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.attribute.Attribute;
import net.avatarverse.avatarversalis.core.attribute.Modifiable;
import net.avatarverse.avatarversalis.core.element.fire.Fire;
import net.avatarverse.avatarversalis.core.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.policy.Policies;
import net.avatarverse.avatarversalis.core.policy.specific.LiquidPolicy;
import net.avatarverse.avatarversalis.core.policy.specific.ProtectedRegionPolicy;
import net.avatarverse.avatarversalis.core.policy.specific.RangePolicy;
import net.avatarverse.avatarversalis.core.policy.specific.SolidPolicy;
import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.util.Blocks;
import net.avatarverse.avatarversalis.util.Effects;
import net.avatarverse.avatarversalis.util.Entities;
import net.avatarverse.avatarversalis.util.Particles;
import net.avatarverse.avatarversalis.util.Sounds;

import lombok.Getter;

@Getter
public class FireBlast extends AbilityInstance {

	private static final Config CONFIG = new Config();

	private Config config;
	private EndingPolicy policy;

	private Location location;
	private Vector direction;
	private Particles fire;
	private Sounds sound;
	private Effects effects;

	public FireBlast(User user) {
		super(user);

		if (Blocks.solidLiquid(user.headBlock())) return;

		location = user.eyeLocation();
		direction = user.direction().clone().multiply(config.speed);
		fire = Particles.fire(user).count(6).offset(config.particleRadius);
		sound = Sounds.fire();
		effects = Effects.by(user, this)
				.damage(config.damage)
				.fireTicks(config.fireTicks);

		policy = Policies.builder()
				.add(SolidPolicy.of(() -> location, () -> direction))
				.add(LiquidPolicy.of(() -> location, () -> direction))
				.add(ProtectedRegionPolicy.of(this, () -> location))
				.add(RangePolicy.of(config.range, user.location(), () -> location)).build();

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
		location.add(direction);
		Fire.ignite(user, this, location.getBlock());
		if (policy.test(user))
			return false;

		fire.spawn(location);
		sound.play(location, 0.25);

		Entities.nearby(location, config.hitRadius, Entities.shouldAffect(user, location)).forEach(e -> {
			effects.apply(e);
			end();
		});

		return true;
	}

	@Getter
	private static class Config extends AbilityConfig {

		@Modifiable(Attribute.COOLDOWN) private long cooldown;
		@Modifiable(Attribute.DAMAGE) private double damage;
		@Modifiable(Attribute.RANGE) private double range;
		@Modifiable(Attribute.SPEED) private double speed;
		@Modifiable(Attribute.FIRE_TICKS) private int fireTicks;
		@Modifiable(Attribute.RADIUS) private double hitRadius;
		@Modifiable(Attribute.RADIUS) private double particleRadius;
		@Modifiable(Attribute.RADIUS) private double igniteBlockRadius;

		@Override
		public void onLoad() {
			CommentedConfigurationNode ability = root.node("abilities", "fire", "FireBlast");

			cooldown = ability.node("cooldown").getLong(1500);
			damage = ability.node("damage").getDouble(2);
			range = ability.node("range").getDouble(20);
			speed = ability.node("speed").getDouble(1);
			fireTicks = ability.node("fire-ticks").getInt(1);

			CommentedConfigurationNode radius = ability.node("radius");

			hitRadius = radius.node("hit").getDouble(1);
			particleRadius = radius.node("particle").getDouble(0.275);
			igniteBlockRadius = radius.node("ignite-block").getDouble(1);
		}
	}
}
