package net.avatarverse.avatarversalis.core.platform.potion;

import net.avatarverse.avatarversalis.core.platform.entity.LivingEntity;

public interface PotionEffect {
	int duration();
	int amplifier();
	boolean ambient();
	boolean hasParticles();
	boolean hasIcon();
	boolean apply(LivingEntity entity);
}
