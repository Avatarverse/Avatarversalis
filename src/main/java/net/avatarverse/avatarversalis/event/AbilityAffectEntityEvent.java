package net.avatarverse.avatarversalis.event;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

import net.avatarverse.avatarversalis.util.Effects;

import edu.umd.cs.findbugs.annotations.ReturnValuesAreNonnullByDefault;
import lombok.Getter;

/**
 * An AbilityEvent representing a number of effects an ability can have on an {@link Entity}
 * <br>
 * Note that any of the effects may be null.
 */
@ParametersAreNonnullByDefault
@ReturnValuesAreNonnullByDefault
@Getter
public class AbilityAffectEntityEvent extends AbilityEvent {

	private final Entity entity;
	private final @Nullable Vector velocity;
	private final @Nullable Double damage;
	private final @Nullable Integer fireTicks, freezeTicks;
	private final @Nullable PotionEffect[] potionEffects;

	public AbilityAffectEntityEvent(Effects effects, Entity entity) {
		super(effects.user(), effects.ability());
		this.entity = entity;

		this.velocity = effects.velocity();
		this.damage = effects.damage();
		this.fireTicks = effects.fireTicks();
		this.freezeTicks = effects.freezeTicks();
		this.potionEffects = effects.potionEffects();
	}
}
