package net.avatarverse.avatarversalis.ability.fire.combo;

import org.spongepowered.configurate.ConfigurationNode;

import net.avatarverse.avatarversalis.config.AbilityConfig;
import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.attribute.Attribute;
import net.avatarverse.avatarversalis.core.attribute.Modifiable;
import net.avatarverse.avatarversalis.core.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.policy.Policies;
import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.util.Blocks;
import net.avatarverse.avatarversalis.util.Particles;
import net.avatarverse.avatarversalis.util.Sounds;

import lombok.Getter;

public class FireSpin extends AbilityInstance {

	private static final Config CONFIG = new Config();

	private Config config;
	private EndingPolicy policy;

	private Particles fire;
	private Sounds sound;

	public FireSpin(User user) {
		super(user);

		if (Blocks.liquid(user.locBlock())) return;

		fire = Particles.fire(user);
		sound = Sounds.fire();

		policy = Policies.builder().build();

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

		return true;
	}

	@Getter
	private static class Config extends AbilityConfig {

		@Modifiable(Attribute.COOLDOWN) private long cooldown;
		@Modifiable(Attribute.DAMAGE) private double damage;
		@Modifiable(Attribute.RANGE) private double range;
		@Modifiable(Attribute.SPEED) private double speed;
		@Modifiable(Attribute.KNOCKBACK) private double knockback;

		@Override
		public void onLoad() {
			ConfigurationNode ability = root.node("abilities", "fire", "combo", "FireSpin");

			cooldown = ability.node("cooldown").getLong();
			damage = ability.node("damage").getDouble();
			range = ability.node("range").getDouble();
			speed = ability.node("speed").getDouble();
			knockback = ability.node("knockback").getDouble();
		}
	}
}
