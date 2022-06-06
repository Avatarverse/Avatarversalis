package net.avatarverse.avatarversalis.core.user;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import net.avatarverse.avatarversalis.core.ability.Ability;

import lombok.Getter;

public class LivingEntityUser extends EntityUser {

	@Getter private final LivingEntity entity;

	public LivingEntityUser(LivingEntity entity) {
		super(entity);
		this.entity = entity;
	}

	@Override
	public boolean canBend(Ability ability) {
		return false;
	}

	public Location eyeLocation() {
		return entity.getEyeLocation();
	}

}
