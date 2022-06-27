package net.avatarverse.avatarversalis.core.util;

import java.util.Arrays;

import net.avatarverse.avatarversalis.core.game.Game;
import net.avatarverse.avatarversalis.core.game.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.game.user.EntityUser;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.platform.entity.Entity;
import net.avatarverse.avatarversalis.core.platform.entity.LivingEntity;
import net.avatarverse.avatarversalis.core.platform.potion.PotionEffect;
import net.avatarverse.avatarversalis.core.platform.util.Vector;
import net.avatarverse.avatarversalis.core.util.data.Wrapper;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@DefaultAnnotation(NonNull.class)
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
		if (Wrapper.of(EntityUser.of(entity)).check(User::invincible)) return;
		if (!Game.eventBus().postAbilityAffectEntityEvent(this, entity)) return;

		if (velocity != null)
			entity.velocity(velocity);

		if (fireTicks != null)
			entity.fireTicks(fireTicks);

		if (freezeTicks != null)
			entity.freezeTicks(freezeTicks);

		if (entity instanceof LivingEntity le) {
			if (damage != null)
				le.damage(damage, (user instanceof EntityUser eu ? eu.entity() : null));
			if (potionEffects != null)
				le.addEffects(Arrays.asList(potionEffects));
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
