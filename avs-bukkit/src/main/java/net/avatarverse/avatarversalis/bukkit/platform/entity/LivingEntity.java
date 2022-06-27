package net.avatarverse.avatarversalis.bukkit.platform.entity;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import net.avatarverse.avatarversalis.bukkit.util.Types;
import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.Material;
import net.avatarverse.avatarversalis.core.platform.block.Block;
import net.avatarverse.avatarversalis.core.platform.entity.Damageable;
import net.avatarverse.avatarversalis.core.platform.potion.PotionEffect;
import net.avatarverse.avatarversalis.core.platform.potion.PotionEffectType;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class LivingEntity extends Entity implements net.avatarverse.avatarversalis.core.platform.entity.LivingEntity {

	private final org.bukkit.entity.LivingEntity bukkit;

	public LivingEntity(org.bukkit.entity.LivingEntity bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public boolean addEffect(PotionEffect effect) {
		return bukkit.addPotionEffect(((net.avatarverse.avatarversalis.bukkit.platform.potion.PotionEffect) effect).bukkit());
	}

	@Override
	public boolean addEffects(Collection<PotionEffect> effects) {
		return bukkit.addPotionEffects(effects.stream().map(effect -> ((net.avatarverse.avatarversalis.bukkit.platform.potion.PotionEffect) effect).bukkit()).collect(Collectors.toSet()));
	}

	@Override
	public Collection<PotionEffect> activeEffects() {
		return bukkit.getActivePotionEffects().stream().map(bkt -> (PotionEffect) new net.avatarverse.avatarversalis.bukkit.platform.potion.PotionEffect(bkt)).collect(Collectors.toSet());
	}

	@Override
	public boolean canPickupItems() {
		return bukkit.getCanPickupItems();
	}

	@Override
	public double eyeHeight() {
		return bukkit.getEyeHeight();
	}

	@Override
	public double eyeHeight(boolean ignorePose) {
		return bukkit.getEyeHeight(ignorePose);
	}

	@Override
	public Location eyeLocation() {
		return new net.avatarverse.avatarversalis.bukkit.platform.Location(bukkit.getEyeLocation());
	}

	@Override
	public double lastDamage() {
		return bukkit.getLastDamage();
	}

	@Override
	public List<Block> lineOfSight(Set<Material> transparent, int maxDistance) {
		return bukkit.getLineOfSight(transparent.stream().map(Types::convert).collect(Collectors.toSet()), maxDistance).stream().map(bkt -> (Block) new net.avatarverse.avatarversalis.bukkit.platform.block.Block(bkt)).toList();
	}

	@Override
	public int maxAir() {
		return bukkit.getMaximumAir();
	}

	@Override
	public int maxNoDamageTicks() {
		return bukkit.getMaximumNoDamageTicks();
	}

	@Override
	public int noDamageTicks() {
		return bukkit.getNoDamageTicks();
	}

	@Override
	public PotionEffect effect(PotionEffectType type) {
		return new net.avatarverse.avatarversalis.bukkit.platform.potion.PotionEffect(bukkit.getPotionEffect(Types.convert(type)));
	}

	@Override
	public int remainingAir() {
		return bukkit.getRemainingAir();
	}

	@Override
	public Block targetBlock(Set<Material> transparent, int maxDistance) {
		return new net.avatarverse.avatarversalis.bukkit.platform.block.Block(bukkit.getTargetBlock(transparent.stream().map(Types::convert).collect(Collectors.toSet()), maxDistance));
	}

	@Override
	public boolean ai() {
		return bukkit.hasAI();
	}

	@Override
	public boolean hasLineOfSight(net.avatarverse.avatarversalis.core.platform.entity.Entity other) {
		return bukkit.hasLineOfSight(((Entity) other).bukkit());
	}

	@Override
	public boolean hasEffect(PotionEffectType type) {
		return bukkit.hasPotionEffect(Types.convert(type));
	}

	@Override
	public boolean collidable() {
		return bukkit.isCollidable();
	}

	@Override
	public boolean gliding() {
		return bukkit.isGliding();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.LivingEntity removeEffect(PotionEffectType type) {
		bukkit.removePotionEffect(Types.convert(type));
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.LivingEntity ai(boolean ai) {
		bukkit.setAI(ai);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.LivingEntity canPickupItems(boolean canPickupItems) {
		bukkit.setCanPickupItems(canPickupItems);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.LivingEntity collidable(boolean collidable) {
		bukkit.setCollidable(collidable);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.LivingEntity gliding(boolean gliding) {
		bukkit.setGliding(gliding);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.LivingEntity lastDamage(double damage) {
		bukkit.setLastDamage(damage);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.LivingEntity maxAir(int ticks) {
		bukkit.setMaximumAir(ticks);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.LivingEntity maxNoDamageTicks(int ticks) {
		bukkit.setMaximumNoDamageTicks(ticks);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.LivingEntity noDamageTicks(int ticks) {
		bukkit.setNoDamageTicks(ticks);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.LivingEntity remainingAir(int ticks) {
		bukkit.setRemainingAir(ticks);
		return this;
	}

	@Override
	public boolean visible() {
		return !bukkit.isInvisible();
	}

	@Override
	public void damage(double amount) {
		bukkit.damage(amount);
	}

	@Override
	public void damage(double amount, net.avatarverse.avatarversalis.core.platform.entity.Entity source) {
		bukkit.damage(amount, ((Entity) source).bukkit());
	}

	@Override
	public double health() {
		return bukkit.getHealth();
	}

	@Override
	public double maxHealth() {
		return bukkit.getMaxHealth();
	}

	@Override
	public void resetMaxHealth() {
		bukkit.resetMaxHealth();
	}

	@Override
	public Damageable health(double health) {
		bukkit.setHealth(health);
		return this;
	}

	@Override
	public Damageable maxHealth(double maxHealth) {
		bukkit.setMaxHealth(maxHealth);
		return this;
	}
}
