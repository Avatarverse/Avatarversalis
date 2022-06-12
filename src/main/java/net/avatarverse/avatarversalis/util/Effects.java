package net.avatarverse.avatarversalis.util;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.user.EntityUser;
import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.event.AbilityAffectEntityEvent;

import edu.umd.cs.findbugs.annotations.ReturnValuesAreNonnullByDefault;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@ParametersAreNonnullByDefault
@ReturnValuesAreNonnullByDefault
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public final class Effects {

	private final User user;
	private final AbilityInstance ability;
	@Setter private Vector velocity;
	@Setter private Double damage;
	@Setter private Integer fireTicks, freezeTicks;
	private PotionEffect[] potionEffects;

	public static Effects by(User user, AbilityInstance ability) {
		return new Effects(user, ability);
	}

	public void apply(Entity entity) {
		if (new AbilityAffectEntityEvent(this, entity).call().isCancelled()) return;

		if (velocity != null)
			entity.setVelocity(velocity);

		if (fireTicks != null)
			entity.setFireTicks(fireTicks);

		if (freezeTicks != null)
			entity.setFreezeTicks(freezeTicks);

		if (entity instanceof LivingEntity le) {
			if (damage != null)
				le.damage(damage, (user instanceof EntityUser eu ? eu.entity() : null));
			if (potionEffects != null)
				le.addPotionEffects(Arrays.asList(potionEffects));
		}
	}

	public PotionEffect[] potionEffects() {
		return potionEffects;
	}

	public Effects potionEffects(PotionEffect... potionEffects) {
		this.potionEffects = potionEffects;
		return this;
	}

}
