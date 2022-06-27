package net.avatarverse.avatarversalis.core.game.element.water;

import org.spongepowered.configurate.CommentedConfigurationNode;

import net.avatarverse.avatarversalis.core.game.attribute.AttributeModifier;
import net.avatarverse.avatarversalis.core.game.attribute.ModifierSet;

import lombok.Getter;

public class WaterFactor extends ModifierSet {

	@Getter private AttributeModifier damage, range, sourceRange, cooldown, duration, speed, chargeTime, radius, height, amount, freezeTicks;

	public <T extends WaterFactor> WaterFactor(Class<T> clazz, CommentedConfigurationNode root) {
		super(clazz, root);
	}
}
