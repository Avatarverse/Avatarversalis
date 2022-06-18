package net.avatarverse.avatarversalis.ability.fire;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.spongepowered.configurate.CommentedConfigurationNode;

import net.avatarverse.avatarversalis.config.AbilityConfig;
import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.attribute.Attribute;
import net.avatarverse.avatarversalis.core.attribute.Modifiable;
import net.avatarverse.avatarversalis.core.element.fire.Fire;
import net.avatarverse.avatarversalis.core.policy.CompositeEndingPolicy;
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
public class FireBlastCharged extends AbilityInstance {

	private static final Config CONFIG = new Config();

	private Config config;
	private CompositeEndingPolicy policy;

	private Location location;
	private Vector direction;
	private Particles fire;
	private Sounds sound;
	private Effects effects;

	private boolean charged = false;
	private boolean launched = false;

	public FireBlastCharged(User user) {
		super(user);

		if (Blocks.liquid(user.headBlock())) return;

		location = user.eyeLocation();
		direction = user.direction().clone().multiply(config.speed);
		fire = Particles.fire(user);
		sound = Sounds.fire();
		effects = Effects.by(user, this)
				.damage(config.damage)
				.fireTicks(config.fireTicks);

		policy = Policies.builder()
				.add(SolidPolicy.of(() -> location, () -> direction, l -> explode()))
				.add(LiquidPolicy.of(() -> location, () -> direction))
				.add(ProtectedRegionPolicy.of(this, () -> location)).build();

		start();
	}

	@Override
	protected void load() {
		config = CONFIG.applyModifiers(this);
	}

	@Override
	protected void postStart() {}

	@Override
	public boolean update() {
		if (policy.test(user) || (!charged && !user.sneaking())) return false;

		if (!charged) {
			location = user.eyeLocation();
			direction = user.direction();
			if (elapsed(config.chargeTime)) {
				charged = true;
				fire.count(3).offset(0.001);
			}
			return true;
		}

		if (!launched) {
			fire.spawn(user.eyeLocation().add(user.direction()));
		} else {
			location.add(direction);
			Fire.ignite(user, this, location.getBlock());
			if (policy.test(user)) return false;

			Blocks.nearby(location, config.hitRadius, Blocks::air).forEach(b -> {
				Location center = Blocks.center(b);
				fire.spawn(center);
				sound.play(center, 0.25);
			});
			Entities.nearby(location, config.hitRadius, Entities.shouldAffect(user, location)).forEach(e -> {
				effects.apply(e);
				explode();
			});
		}
		return true;
	}

	@Override
	public void onSneakRelease() {
		if (!charged) return;
		launched = true;
		location = user.eyeLocation();
		direction = user.direction().clone().multiply(config.speed);
		fire.count(5).offset(0.5);
		policy.remove(SolidPolicy.class).add(RangePolicy.of(config.range, user.location(), () -> location));
		user.addCooldown(ability, config.cooldown);
	}

	public boolean explode() {
		end();
		return true;
	}

	@Getter
	private static class Config extends AbilityConfig {

		@Modifiable(Attribute.COOLDOWN) public long cooldown;
		@Modifiable(Attribute.CHARGE_TIME) public long chargeTime;
		@Modifiable(Attribute.DAMAGE) public double damage;
		@Modifiable(Attribute.RANGE) public double range;
		@Modifiable(Attribute.SPEED) public double speed;
		@Modifiable(Attribute.FIRE_TICKS) public int fireTicks; public boolean damageBlocks;
		@Modifiable(Attribute.RADIUS) public double hitRadius;
		@Modifiable(Attribute.RADIUS) public double particleRadius;
		@Modifiable(Attribute.RADIUS) public double igniteBlockRadius;

		@Override
		public void onLoad() {
			CommentedConfigurationNode ability = root.node("abilities", "fire", "FireBlast", "charged");

			cooldown = ability.node("cooldown").getLong();
			chargeTime = ability.node("charge-time").getLong();
			damage = ability.node("damage").getDouble(4);
			range = ability.node("range").getDouble();
			speed = ability.node("speed").getDouble();
			fireTicks = ability.node("fire-ticks").getInt();
			damageBlocks = ability.node("damage-blocks").getBoolean(false);

			CommentedConfigurationNode radius = ability.node("radius");

			hitRadius = radius.node("hit").getDouble();
			particleRadius = radius.node("particle").getDouble();
			igniteBlockRadius = radius.node("ignite-block").getDouble();
		}
	}
}
