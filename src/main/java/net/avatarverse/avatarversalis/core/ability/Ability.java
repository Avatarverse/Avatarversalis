package net.avatarverse.avatarversalis.core.ability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.avatarverse.avatarversalis.core.element.Element;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Represents static ability information, as opposed to an instance of the ability.
 * Building an Ability automatically adds it to the AbilityManager collections.
 */
@DefaultAnnotation(NonNull.class)
@Getter
public class Ability {

	private final String name, description, instructions;
	private final @Nullable String author, version;
	private final Element element;
	private final Map<Activation, Class<? extends AbilityInstance>> activations;
	private final Map<Class<? extends AbilityInstance>, Set<Activation>> controls;
	private final List<ComboStep> comboSteps;
	private final List<Ability> subAbilities;
	private final boolean bindable, hidden, combo, passive, multiAbility, subAbility;

	private Ability(Builder builder) {
		this.name = builder.name;
		this.description = builder.description;
		this.instructions = builder.instructions;
		this.element = builder.element;
		this.activations = builder.activations;
		this.controls = builder.controls;
		this.comboSteps = builder.comboSteps;
		this.subAbilities = builder.subAbilities;
		this.bindable = builder.bindable;
		this.hidden = builder.hidden;
		this.combo = builder.combo;
		this.passive = builder.passive;
		this.multiAbility = builder.multiAbility;
		this.subAbility = builder.subAbility;

		this.author = builder.author;
		this.version = builder.version;

		AbilityManager.ABILITIES_BY_NAME.put(name, this);
		activations.values().forEach(clazz -> AbilityManager.ABILITIES_BY_CLASS.put(clazz, this));
		if (combo)
			AbilityManager.COMBOS.add(this);
	}

	public static @Nullable Ability byName(String name) {
		return AbilityManager.ABILITIES_BY_NAME.get(name);
	}

	public static @Nullable Ability byNameIgnoreCase(String name) {
		return AbilityManager.ABILITIES_BY_NAME.values().stream().filter(a -> a.name.equalsIgnoreCase(name)).findAny().orElse(null);
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
		private final Map<Class<? extends AbilityInstance>, Set<Activation>> controls = new HashMap<>();
		private final List<ComboStep> comboSteps = new ArrayList<>();
		private List<Ability> subAbilities = new ArrayList<>();
		private boolean bindable, hidden, combo, passive, multiAbility, subAbility;

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

		public <T extends AbilityInstance> Builder control(Class<T> ability, Activation... activations) {
			this.controls.putIfAbsent(ability, new HashSet<>());
			this.controls.get(ability).addAll(Arrays.asList(activations));
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

		public Builder combo(Class<? extends AbilityInstance> ability, ComboStep... steps) {
			this.combo = true;
			comboSteps.clear();
			comboSteps.addAll(Arrays.asList(steps));
			return activation(Activation.COMBO, ability);
		}

		public Builder multiAbility(List<Ability> subAbilities) {
			this.multiAbility = true;
			this.subAbilities = subAbilities;
			return this;
		}

		public Builder subAbility() {
			this.subAbility = true;
			return hidden();
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
			if (combo && (bindable || passive)) {
				bindable = false;
				passive = false;
			}
			if (multiAbility && passive) {
				passive = false;
			}
			return new Ability(this);
		}

		@NoArgsConstructor
		public static class MultiAbilityBuilder {

			private final List<Ability> subAbilities = new ArrayList<>();

			public MultiAbilityBuilder add(Ability ability) {
				subAbilities.add(ability);
				return this;
			}

			public List<Ability> build() {
				return subAbilities;
			}
		}

	}

}
