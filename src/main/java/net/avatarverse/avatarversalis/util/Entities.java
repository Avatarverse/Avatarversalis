package net.avatarverse.avatarversalis.util;

import java.util.Collection;
import java.util.function.Predicate;

import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;

import net.avatarverse.avatarversalis.core.user.EntityUser;
import net.avatarverse.avatarversalis.core.user.User;

public final class Entities {

	public static Predicate<Entity> canBuild(Block block) {
		return e -> true;
	}

	public static Predicate<Entity> excludeUser(User user) {
		return e -> !(user instanceof EntityUser eu) || e != eu.entity();
	}

	public static Predicate<Entity> shouldAffect(User user, Location location) {
		return excludeUser(user).and(canBuild(location.getBlock()));
	}

	public static Collection<Entity> nearby(Location location, double radius) {
		return nearby(location, radius, null);
	}

	public static Collection<Entity> nearby(Location location, double radius, @Nullable Predicate<Entity> filter) {
		Validate.notNull(location.getWorld(), "World must not be null");
		return location.getWorld().getNearbyEntities(location, radius, radius, radius, filter);
	}

}
