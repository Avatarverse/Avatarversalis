package net.avatarverse.avatarversalis.core.platform;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import net.avatarverse.avatarversalis.core.platform.block.Block;
import net.avatarverse.avatarversalis.core.platform.entity.Entity;
import net.avatarverse.avatarversalis.core.platform.entity.LivingEntity;
import net.avatarverse.avatarversalis.core.platform.entity.Player;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

@DefaultAnnotation(NonNull.class)
public interface World {
	UUID uid();
	String name();
	Block blockAt(int x, int y, int z);
	Block blockAt(Location location);
	Block highestBlockAt(int x, int z);
	Block highestBlockAt(Location location);
	Location location(double x, double y, double z);
	List<Entity> entities();
	List<LivingEntity> livingEntities();
	List<Player> players();
	long time();
	long fullTime();
	double humidity(int x, int y, int z);
	double temperature(int x, int y, int z);
	int maxHeight();
	int seaLevel();
	Collection<Entity> nearbyEntities(Location location, double x, double y, double z, Predicate<Entity> filter);
	<T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable T data, boolean force);
	void playSound(Location location, Sound sound, SoundCategory category, float volume, float pitch);
}
