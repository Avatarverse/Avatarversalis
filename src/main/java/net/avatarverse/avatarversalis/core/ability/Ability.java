package net.avatarverse.avatarversalis.core.ability;

import java.util.EnumSet;
import java.util.Set;

import net.avatarverse.avatarversalis.core.element.Element;

import lombok.Data;

@Data
public class Ability {

	private final String name, description, instructions;
	private final Element element;
	private final EnumSet<Activation> activations;
	private final Set<String> permissions;
	private final boolean bindable, hidden;

	private Ability(Builder builder) {
		this.name = builder.name;
		this.description = builder.description;
		this.instructions = builder.instructions;
		this.element = builder.element;
		this.activations = builder.activations;
		this.permissions = builder.permissions;
		this.bindable = builder.bindable;
		this.hidden = builder.hidden;
	}

	public String displayName() {
		return element.display().color() + name;
	}

	private static class Builder {

		private String name, description, instructions;
		private Element element;
		private EnumSet<Activation> activations;
		private Set<String> permissions;
		private boolean bindable, hidden;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder instructions(String instructions) {
			this.instructions = instructions;
			return this;
		}

		public Builder element(Element element) {
			this.element = element;
			return this;
		}

		public Builder activations(Activation activation, Activation... activations) {
			this.activations = EnumSet.of(activation, activations);
			return this;
		}

		public Builder permissions(String... permissions) {
			this.permissions = Set.of(permissions);
			return this;
		}

		public Builder bindable(boolean bindable) {
			this.bindable = bindable;
			return this;
		}

		public Builder hidden(boolean hidden) {
			this.hidden = hidden;
			return this;
		}

		public Ability build() {
			return new Ability(this);
		}

	}

}
