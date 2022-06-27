package net.avatarverse.avatarversalis.core.game.user;

import net.avatarverse.avatarversalis.core.game.ability.Ability;
import net.avatarverse.avatarversalis.core.platform.entity.LivingEntity;
import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.util.Geometry;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
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
		return entity.eyeLocation();
	}

	@Override
	public Location handLocation() {
		return Geometry.right(location(), 0.55).add(0, 0.7 * entity.height(), 0);
	}

}
