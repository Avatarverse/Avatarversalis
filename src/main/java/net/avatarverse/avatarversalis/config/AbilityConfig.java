package net.avatarverse.avatarversalis.config;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.function.Consumer;

import org.spongepowered.configurate.CommentedConfigurationNode;

import net.avatarverse.avatarversalis.Avatarversalis;
import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.attribute.Attribute;
import net.avatarverse.avatarversalis.core.attribute.AttributeModifier;
import net.avatarverse.avatarversalis.core.attribute.Modifiable;
import net.avatarverse.avatarversalis.core.attribute.ModifierOperation;

/**
 * Loads ability configs, applies modifiers
 */
public abstract class AbilityConfig implements Cloneable {

	protected CommentedConfigurationNode root;

	protected AbilityConfig() {
		ConfigManager.ABILITY_CONFIGS.add(this);
	}

	public void load() {
		root = Avatarversalis.configManager().config();
		onLoad();
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public <T extends AbilityConfig> T applyModifiers(AbilityInstance instance) {
		T modified;
		try {
			modified = (T) clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return (T) this;
		}

		for (Field field : modified.getClass().getDeclaredFields()) {
			if (!field.isAnnotationPresent(Modifiable.class)) continue;
			boolean wasAccessible = field.isAccessible();
			field.setAccessible(true);
			modifyField(field, instance.modifiers());
			field.setAccessible(wasAccessible);
		}

		return modified;
	}

	private void modifyField(Field field, Collection<AttributeModifier> modifiers) {
		Number value;
		try {
			value = (Number) field.get(this);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return;
		}

		Consumer<AttributeModifier> modify = am -> am.operation().modify(value, am.modifier());
		Collection<AttributeModifier> filtered = modifiers.stream().filter(am -> hasAttribute(field, am.attribute())).toList();
		filtered.stream().filter(am -> am.operation() == ModifierOperation.ADD).forEach(modify);
		filtered.stream().filter(am -> am.operation() == ModifierOperation.MULTIPLY).forEach(modify);

		try {
			field.set(this, value);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private boolean hasAttribute(Field field, Attribute attribute) {
		for (Modifiable modifiable : field.getAnnotationsByType(Modifiable.class))
			if (attribute.equals(modifiable.value()))
				return true;
		return false;
	}

	public abstract void onLoad();

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
