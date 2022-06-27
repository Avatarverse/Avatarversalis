package net.avatarverse.avatarversalis.core.platform.entity;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.avatarverse.avatarversalis.core.game.collision.Ray;
import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.Material;
import net.avatarverse.avatarversalis.core.platform.potion.PotionEffect;
import net.avatarverse.avatarversalis.core.platform.potion.PotionEffectType;
import net.avatarverse.avatarversalis.core.platform.block.Block;

public interface LivingEntity extends Entity, Damageable {
	boolean addEffect(PotionEffect effect);
	boolean addEffects(Collection<PotionEffect> effects);
	Collection<PotionEffect> activeEffects();
	boolean canPickupItems();
	double eyeHeight();
	double eyeHeight(boolean ignorePose);
	Location eyeLocation();
	double lastDamage();
	List<Block> lineOfSight(Set<Material> transparent, int maxDistance);
	int maxAir();
	int maxNoDamageTicks();
	int noDamageTicks();
	PotionEffect effect(PotionEffectType type);
	int remainingAir();
	Block targetBlock(Set<Material> transparent, int maxDistance);
	boolean ai();
	boolean hasLineOfSight(Entity other);
	boolean hasEffect(PotionEffectType type);
	boolean collidable();
	boolean gliding();
	LivingEntity removeEffect(PotionEffectType type);
	LivingEntity ai(boolean ai);
	LivingEntity canPickupItems(boolean canPickupItems);
	LivingEntity collidable(boolean collidable);
	LivingEntity gliding(boolean gliding);
	LivingEntity lastDamage(double damage);
	LivingEntity maxAir(int ticks);
	LivingEntity maxNoDamageTicks(int ticks);
	LivingEntity noDamageTicks(int ticks);
	LivingEntity remainingAir(int ticks);
	boolean visible();

	default Ray ray() {
		return new Ray(); // TODO
	}
}
