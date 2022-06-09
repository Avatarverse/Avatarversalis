package net.avatarverse.avatarversalis.ability.fire.combo;

import org.spongepowered.configurate.ConfigurationNode;

import net.avatarverse.avatarversalis.config.AbilityConfig;
import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.attribute.Attribute;
import net.avatarverse.avatarversalis.core.attribute.Modifiable;
import net.avatarverse.avatarversalis.core.user.User;

import lombok.Getter;

public class FireBlade extends AbilityInstance {

	public FireBlade(User user) {
		super(user);
	}

	@Override
	protected void load() {

	}

	@Override
	protected void postStart() {

	}

	@Override
	protected boolean update() {
		return false;
	}

	@Getter
	private static class Config extends AbilityConfig {

		@Modifiable(Attribute.COOLDOWN) public long cooldown;
		@Modifiable(Attribute.DAMAGE) public double damage;
		@Modifiable(Attribute.RANGE) public double range;
		@Modifiable(Attribute.SPEED) public double speed;

		@Override
		public void onLoad() {
			ConfigurationNode ability = root.node("abilities", "fire", "combo", "FireBlade");

			cooldown = ability.node("cooldown").getLong();
			damage = ability.node("damage").getDouble();
			range = ability.node("range").getDouble();
			speed = ability.node("speed").getDouble();
		}
	}
}
