package net.avatarverse.avatarversalis.bending.ability.fire.combo;

import org.spongepowered.configurate.ConfigurationNode;

import net.avatarverse.avatarversalis.bending.Bending;
import net.avatarverse.avatarversalis.core.config.AbilityConfig;
import net.avatarverse.avatarversalis.core.game.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.game.attribute.Attribute;
import net.avatarverse.avatarversalis.core.game.attribute.Modifiable;
import net.avatarverse.avatarversalis.core.game.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.game.policy.Policies;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.util.Particles;
import net.avatarverse.avatarversalis.core.util.Sounds;

import lombok.Getter;

public class FireSpin extends AbilityInstance {

	private static final Config CONFIG = new Config();

	private Config config;
	private EndingPolicy policy;

	private Particles fire;
	private Sounds sound;

	public FireSpin(User user) {
		super(user);

		if (user.locBlock().liquid()) return;

		fire = !user.hasElement(Bending.BLUE_FIRE) ? Particles.fire() : Particles.blueFire();
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
