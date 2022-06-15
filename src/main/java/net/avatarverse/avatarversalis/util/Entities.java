package net.avatarverse.avatarversalis.util;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.function.Predicate;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;

import com.google.common.base.Preconditions;

import net.avatarverse.avatarversalis.core.user.EntityUser;
import net.avatarverse.avatarversalis.core.user.User;

import edu.umd.cs.findbugs.annotations.ReturnValuesAreNonnullByDefault;

@ParametersAreNonnullByDefault
@ReturnValuesAreNonnullByDefault
public final class Entities {

	public static Predicate<Entity> canBuild(Block block) {
		return e -> true; // TODO
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
		Preconditions.checkNotNull(location.getWorld(), "World must not be null");
		return location.getWorld().getNearbyEntities(location, radius, radius, radius, filter);
	}

}
