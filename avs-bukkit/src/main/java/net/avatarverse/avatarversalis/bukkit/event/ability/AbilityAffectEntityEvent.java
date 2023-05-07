package net.avatarverse.avatarversalis.bukkit.event.ability;

import net.avatarverse.avatarversalis.core.platform.entity.Entity;
import net.avatarverse.avatarversalis.core.platform.potion.PotionEffect;
import net.avatarverse.avatarversalis.core.platform.util.Vector;
import net.avatarverse.avatarversalis.core.util.Effects;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
public class AbilityAffectEntityEvent extends AbilityEvent implements net.avatarverse.avatarversalis.core.event.ability.AbilityAffectEntityEvent {

	@Getter private final Entity entity;
	private final Vector velocity;
	private final Double damage;
	private final Integer fireTicks, freezeTicks;
	private final PotionEffect[] potionEffects;

	public AbilityAffectEntityEvent(Effects effects, Entity entity) {
		super(effects.user(), effects.ability());
		this.entity = entity;

		this.velocity = effects.velocity();
		this.damage = effects.damage();
		this.fireTicks = effects.fireTicks();
		this.freezeTicks = effects.freezeTicks();
		this.potionEffects = effects.potionEffects();
	}

	public @Nullable Vector velocity() {
		return this.velocity;
	}

	public @Nullable Double damage() {
		return this.damage;
	}

	public @Nullable Integer fireTicks() {
		return this.fireTicks;
	}

	public @Nullable Integer freezeTicks() {
		return this.freezeTicks;
	}

	public @Nullable PotionEffect[] potionEffects() {
		return this.potionEffects;
	}
}
