package net.avatarverse.avatarversalis.ability.fire.passive;

import java.util.HashMap;
import java.util.Map;

import org.spongepowered.configurate.ConfigurationNode;

import net.avatarverse.avatarversalis.config.AbilityConfig;
import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.attribute.Attribute;
import net.avatarverse.avatarversalis.core.attribute.Modifiable;
import net.avatarverse.avatarversalis.core.policy.EndingPolicy;
import net.avatarverse.avatarversalis.core.user.User;

import lombok.Getter;

public class Illumination extends AbilityInstance {

	public static final Map<User, Illumination> DISABLED = new HashMap<>();
	private static final Config CONFIG = new Config();

	private Config config;
	private EndingPolicy policy;

	public Illumination(User user) {
		super(user);

		if (DISABLED.containsKey(user)) return;

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
		return false;
	}

	@Override
	protected void cleanup() {}

	private static class Config extends AbilityConfig {

		@Modifiable(Attribute.COOLDOWN)
		@Getter private long cooldown;
		@Modifiable(Attribute.RANGE)
		@Getter private double range;
		private int lightThreshold;

		@Override
		public void onLoad() {
			ConfigurationNode ability = root.node("abilities", "fire", "Illumination");

			cooldown = ability.node("cooldown").getLong();
			range = ability.node("range").getDouble();
			lightThreshold = ability.node("light-threshold").getInt();
		}
	}
}
