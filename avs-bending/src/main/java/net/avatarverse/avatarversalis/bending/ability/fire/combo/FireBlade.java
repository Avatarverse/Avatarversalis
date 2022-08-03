package net.avatarverse.avatarversalis.bending.ability.fire.combo;

import org.spongepowered.configurate.CommentedConfigurationNode;

import net.avatarverse.avatarversalis.bending.Bending;
import net.avatarverse.avatarversalis.core.config.AbilityConfig;
import net.avatarverse.avatarversalis.core.game.ability.Ability;
import net.avatarverse.avatarversalis.core.game.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.game.ability.Activation;
import net.avatarverse.avatarversalis.core.game.ability.ComboStep;
import net.avatarverse.avatarversalis.core.game.attribute.Attribute;
import net.avatarverse.avatarversalis.core.game.attribute.Modifiable;
import net.avatarverse.avatarversalis.core.game.user.User;

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
			CommentedConfigurationNode ability = root.node("abilities", "fire", "combo", "FireBlade");

			cooldown = ability.node("cooldown").getLong();
			damage = ability.node("damage").getDouble();
			range = ability.node("range").getDouble();
			speed = ability.node("speed").getDouble();
		}
	}

	public static void register() {
		Ability.builder("FireBlade", Bending.FIRE)
				.combo(FireBlade.class,
						new ComboStep("FireBlast", Activation.ATTACK),
						new ComboStep("FireBlast", Activation.ATTACK),
						new ComboStep("FireBlast", Activation.SNEAK),
						new ComboStep("FireBlast", Activation.ATTACK)).build();
	}
}
