package net.avatarverse.avatarversalis.core.game.attribute;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.spongepowered.configurate.CommentedConfigurationNode;

import com.google.common.base.CaseFormat;

import lombok.Getter;

public abstract class ModifierSet {

	@Getter private final Set<AttributeModifier> modifiers = new HashSet<>();

	public <T extends ModifierSet> ModifierSet(Class<T> clazz, CommentedConfigurationNode root) {
		for (Entry<Object, CommentedConfigurationNode> entry : root.childrenMap().entrySet()) {
			String name = entry.getKey().toString(); // lower-hyphen case
			Optional.ofNullable(
					Attributes.parseExpression(entry.getValue().getString(""))
			).ifPresent(p -> {
				Attribute attribute;
				try {
					attribute = Attribute.valueOf(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, name)); // UPPER_CAMEL case
				} catch (IllegalArgumentException e) {
					// TODO log
					return;
				}
				AttributeModifier modifier = new AttributeModifier(attribute, p.key(), p.value());
				try {
					Field field = clazz.getField(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, name)); // lowerCamel case
					field.set(this, modifier);
				} catch (NoSuchFieldException | IllegalAccessException e) {
					// TODO log
					return;
				}
				modifiers.add(modifier);
			});
		}
	}

}
