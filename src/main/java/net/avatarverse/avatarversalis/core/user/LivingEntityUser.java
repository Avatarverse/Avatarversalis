package net.avatarverse.avatarversalis.core.user;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.util.Geometry;

import edu.umd.cs.findbugs.annotations.ReturnValuesAreNonnullByDefault;
import lombok.Getter;

@ParametersAreNonnullByDefault
@ReturnValuesAreNonnullByDefault
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

	@Override
	public Location eyeLocation() {
		return entity.getEyeLocation();
	}

	@Override
	public Location handLocation() {
		return Geometry.right(location(), 0.55).add(0, 0.7 * entity.getHeight(), 0);
	}

}
