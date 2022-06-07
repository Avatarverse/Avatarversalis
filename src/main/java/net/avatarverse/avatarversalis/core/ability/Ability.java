package net.avatarverse.avatarversalis.core.ability;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import net.avatarverse.avatarversalis.core.element.Element;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents static ability information, as opposed to an instance of the ability.
 * Building an Ability automatically adds it to the AbilityManager collections.
 */
@Getter
public class Ability {

	private final String name, description, instructions;
	private final @Nullable String author, version;
	private final Element element;
	private final Map<Activation, Class<? extends AbilityInstance>> activations;
	private final Map<Class<? extends AbilityInstance>, Activation> controls;
	private final boolean bindable, hidden, passive;

	private Ability(Builder builder) {
		this.name = builder.name;
		this.description = builder.description;
		this.instructions = builder.instructions;
		this.element = builder.element;
		this.activations = builder.activations;
		this.controls = builder.controls;
		this.bindable = builder.bindable;
		this.hidden = builder.hidden;
		this.passive = builder.passive;

		this.author = builder.author;
		this.version = builder.version;

		AbilityManager.ABILITIES_BY_NAME.put(name, this);
		activations.values().forEach(clazz -> AbilityManager.ABILITIES_BY_CLASS.put(clazz, this));
	}

	public static @Nullable Ability byName(String name) {
		return AbilityManager.ABILITIES_BY_NAME.get(name);
	}

	public String displayName() {
		return element.display().color() + name;
	}

	public static Builder builder(String name, Element element) {
		return new Builder(name, element);
	}

	@RequiredArgsConstructor
	public static final class Builder {

		private final String name;
		private final Element element;
		private String description, instructions, author, version;
		private final Map<Activation, Class<? extends AbilityInstance>> activations = new HashMap<>();
		private final Map<Class<? extends AbilityInstance>, Activation> controls = new HashMap<>();
		private boolean bindable, hidden, passive;

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder instructions(String instructions) {
			this.instructions = instructions;
			return this;
		}

		public <T extends AbilityInstance> Builder activation(Activation activation, Class<T> ability) {
			this.activations.put(activation, ability);
			return this;
		}

		public <T extends AbilityInstance> Builder control(Class<T> ability, Activation activation) {
			this.controls.put(ability, activation);
			return this;
		}

		public Builder bindable() {
			this.bindable = true;
			return this;
		}

		public Builder hidden() {
			this.hidden = true;
			return this;
		}

		public Builder passive() {
			this.passive = true;
			return this;
		}

		public Builder author(String author) {
			this.author = author;
			return this;
		}

		public Builder version(String version) {
			this.version = version;
			return this;
		}

		public Ability build() {
			if (description == null || instructions == null) {
				// load from config
			}
			return new Ability(this);
		}

	}

}
