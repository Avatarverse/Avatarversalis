package net.avatarverse.avatarversalis.core.config;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import org.spongepowered.configurate.CommentedConfigurationNode;

import net.avatarverse.avatarversalis.core.game.Game;
import net.avatarverse.avatarversalis.core.game.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.game.attribute.Attribute;
import net.avatarverse.avatarversalis.core.game.attribute.AttributeModifier;
import net.avatarverse.avatarversalis.core.game.attribute.Modifiable;
import net.avatarverse.avatarversalis.core.game.attribute.ModifierOperation;

import lombok.SneakyThrows;

/**
 * Loads ability configs, applies modifiers
 */
public abstract class AbilityConfig implements Cloneable {

	protected CommentedConfigurationNode root;

	protected AbilityConfig() {
		ConfigManager.ABILITY_CONFIGS.add(this);
	}

	public void load() {
		root = Game.configManager().config();
		onLoad();
	}

	@SneakyThrows
	@SuppressWarnings({"unchecked", "deprecation"})
	public <T extends AbilityConfig> T applyModifiers(AbilityInstance instance) {
		T modified = (T) clone();

		for (Field field : modified.getClass().getDeclaredFields()) {
			if (!field.isAnnotationPresent(Modifiable.class)) continue;
			try {
				boolean accessible = field.isAccessible();
				field.setAccessible(true);
				modifyField(field, instance.modifiers());
				field.setAccessible(accessible);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return modified;
	}

	private void modifyField(Field field, Set<AttributeModifier> modifiers) throws IllegalAccessException {
		Number value = (Number) field.get(this);

		Consumer<AttributeModifier> modify = am -> am.operation().modify(value, am.modifier());
		List<AttributeModifier> filtered = modifiers.stream().filter(am -> hasAttribute(field, am.attribute())).toList();
		filtered.stream().filter(am -> am.operation() == ModifierOperation.ADD).forEach(modify);
		filtered.stream().filter(am -> am.operation() == ModifierOperation.MULTIPLY).forEach(modify);

		field.set(this, value);
	}

	private boolean hasAttribute(Field field, Attribute attribute) {
		return Arrays.stream(field.getAnnotationsByType(Modifiable.class)).anyMatch(m -> attribute == m.value());
	}

	public abstract void onLoad();

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
