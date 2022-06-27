package net.avatarverse.avatarversalis.core.game.element.fire;

import org.spongepowered.configurate.CommentedConfigurationNode;

import net.avatarverse.avatarversalis.core.game.attribute.AttributeModifier;
import net.avatarverse.avatarversalis.core.game.attribute.ModifierSet;

import lombok.Getter;

public class FireFactor extends ModifierSet {

	@Getter private AttributeModifier damage, range, cooldown, duration, speed, chargeTime, radius, height, amount, fireTicks;

	public <T extends FireFactor> FireFactor(Class<T> clazz, CommentedConfigurationNode root) {
		super(clazz, root);
	}
}
