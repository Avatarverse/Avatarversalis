package net.avatarverse.avatarversalis.bending.ability.fire;

import org.spongepowered.configurate.CommentedConfigurationNode;

import net.avatarverse.avatarversalis.bending.Bending;
import net.avatarverse.avatarversalis.core.config.AbilityConfig;
import net.avatarverse.avatarversalis.core.game.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.game.attribute.Attribute;
import net.avatarverse.avatarversalis.core.game.attribute.Modifiable;
import net.avatarverse.avatarversalis.core.game.element.fire.Fire;
import net.avatarverse.avatarversalis.core.game.policy.CompositeEndingPolicy;
import net.avatarverse.avatarversalis.core.game.policy.Policies;
import net.avatarverse.avatarversalis.core.game.policy.type.LiquidPolicy;
import net.avatarverse.avatarversalis.core.game.policy.type.ProtectedRegionPolicy;
import net.avatarverse.avatarversalis.core.game.policy.type.RangePolicy;
import net.avatarverse.avatarversalis.core.game.policy.type.SolidPolicy;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.util.Vector;
import net.avatarverse.avatarversalis.core.util.Blocks;
import net.avatarverse.avatarversalis.core.util.Effects;
import net.avatarverse.avatarversalis.core.util.Entities;
import net.avatarverse.avatarversalis.core.util.Particles;
import net.avatarverse.avatarversalis.core.util.Sounds;

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

		if (user.locBlock().liquid()) return;

		location = user.eyeLocation();
		direction = user.direction().clone().multiply(config.speed);
		fire = !user.hasElement(Bending.BLUE_FIRE) ? Particles.fire() : Particles.blueFire();
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
			Fire.ignite(user, this, location.block());
			if (policy.test(user)) return false;

			Blocks.nearby(location, config.hitRadius, Blocks::air).forEach(b -> {
				Location center = b.center();
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
		policy.remove(SolidPolicy.class)
				.add(RangePolicy.of(config.range, user.location(), () -> location));
		user.addCooldown(ability, config.cooldown);
	}

	public boolean explode() {
		stop();
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
