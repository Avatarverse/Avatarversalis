package net.avatarverse.avatarversalis.bending.ability.fire;

import org.spongepowered.configurate.CommentedConfigurationNode;

import net.avatarverse.avatarversalis.bending.Bending;
import net.avatarverse.avatarversalis.core.config.AbilityConfig;
import net.avatarverse.avatarversalis.core.game.ability.Ability;
import net.avatarverse.avatarversalis.core.game.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.game.ability.Activation;
import net.avatarverse.avatarversalis.core.game.attribute.Attribute;
import net.avatarverse.avatarversalis.core.game.attribute.Modifiable;
import net.avatarverse.avatarversalis.core.game.element.fire.Fire;
import net.avatarverse.avatarversalis.core.game.policy.EndingPolicy;
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
		fire = (!user.hasElement(Bending.BLUE_FIRE) ? Particles.fire() : Particles.blueFire()).count(6).offset(config.particleRadius);
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
		Fire.ignite(user, this, location.block());
		if (policy.test(user))
			return false;

		fire.spawn(location);
		sound.play(location, 0.25);

		Entities.nearby(location, config.hitRadius, Entities.shouldAffect(user, location)).forEach(e -> {
			effects.apply(e);
			stop();
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

	public static void register() {
		Ability.builder("FireBlast", Bending.FIRE)
				.activation(Activation.ATTACK, FireBlast.class)
				.activation(Activation.SNEAK, FireBlastCharged.class)
				.control(FireBlastCharged.class, Activation.SNEAK_RELEASE)
				.bindable().build();
	}
}
