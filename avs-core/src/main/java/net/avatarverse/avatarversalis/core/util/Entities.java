package net.avatarverse.avatarversalis.core.util;

import java.util.Collection;
import java.util.function.Predicate;

import com.google.common.base.Preconditions;

import net.avatarverse.avatarversalis.core.game.user.EntityUser;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.platform.entity.Entity;
import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.block.Block;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

@DefaultAnnotation(NonNull.class)
public final class Entities {

	public static Predicate<Entity> canBuild(Block block) {
		return e -> true; // TODO
	}

	public static Predicate<Entity> excludeUser(User user) {
		return e -> !(user instanceof EntityUser eu) || e != eu.entity();
	}

	public static Predicate<Entity> shouldAffect(User user, Location location) {
		return excludeUser(user).and(canBuild(location.block()));
	}

	public static Collection<Entity> nearby(Location location, double radius) {
		return nearby(location, radius, null);
	}

	public static Collection<Entity> nearby(Location location, double radius, @Nullable Predicate<Entity> filter) {
		Preconditions.checkNotNull(location.world(), "World must not be null");
		return location.world().nearbyEntities(location, radius, radius, radius, filter);
	}

}
