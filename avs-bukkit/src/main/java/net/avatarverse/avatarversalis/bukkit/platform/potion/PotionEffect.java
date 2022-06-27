package net.avatarverse.avatarversalis.bukkit.platform.potion;

import net.avatarverse.avatarversalis.core.platform.entity.LivingEntity;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@DefaultAnnotation(NonNull.class)
@RequiredArgsConstructor
@Getter
public class PotionEffect implements net.avatarverse.avatarversalis.core.platform.potion.PotionEffect {

	private final org.bukkit.potion.PotionEffect bukkit;

	@Override
	public int duration() {
		return bukkit.getDuration();
	}

	@Override
	public int amplifier() {
		return bukkit.getAmplifier();
	}

	@Override
	public boolean ambient() {
		return bukkit.isAmbient();
	}

	@Override
	public boolean hasParticles() {
		return bukkit.hasParticles();
	}

	@Override
	public boolean hasIcon() {
		return bukkit.hasIcon();
	}

	@Override
	public boolean apply(LivingEntity entity) {
		return bukkit.apply(((net.avatarverse.avatarversalis.bukkit.platform.entity.LivingEntity) entity).bukkit());
	}
}
