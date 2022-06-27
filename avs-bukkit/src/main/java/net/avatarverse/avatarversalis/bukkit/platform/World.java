package net.avatarverse.avatarversalis.bukkit.platform;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import net.avatarverse.avatarversalis.bukkit.util.Types;
import net.avatarverse.avatarversalis.core.platform.entity.Entity;
import net.avatarverse.avatarversalis.core.platform.entity.LivingEntity;
import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.Particle;
import net.avatarverse.avatarversalis.core.platform.entity.Player;
import net.avatarverse.avatarversalis.core.platform.Sound;
import net.avatarverse.avatarversalis.core.platform.SoundCategory;
import net.avatarverse.avatarversalis.core.platform.block.Block;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@DefaultAnnotation(NonNull.class)
@RequiredArgsConstructor
@Getter
public class World implements net.avatarverse.avatarversalis.core.platform.World {

	private final org.bukkit.World bukkit;

	@Override
	public UUID uid() {
		return bukkit.getUID();
	}

	@Override
	public String name() {
		return bukkit.getName();
	}

	@Override
	public Block blockAt(int x, int y, int z) {
		return new net.avatarverse.avatarversalis.bukkit.platform.block.Block(bukkit.getBlockAt(x, y, z));
	}

	@Override
	public Block blockAt(Location location) {
		return new net.avatarverse.avatarversalis.bukkit.platform.block.Block(bukkit.getBlockAt(((net.avatarverse.avatarversalis.bukkit.platform.Location) location).bukkit()));
	}

	@Override
	public Block highestBlockAt(int x, int z) {
		return new net.avatarverse.avatarversalis.bukkit.platform.block.Block(bukkit.getHighestBlockAt(x, z));
	}

	@Override
	public Block highestBlockAt(Location location) {
		return new net.avatarverse.avatarversalis.bukkit.platform.block.Block(bukkit.getHighestBlockAt(((net.avatarverse.avatarversalis.bukkit.platform.Location) location).bukkit()));
	}

	@Override
	public Location location(double x, double y, double z) {
		return new net.avatarverse.avatarversalis.bukkit.platform.Location(new org.bukkit.Location(bukkit, x, y, z));
	}

	@Override
	public List<Entity> entities() {
		return bukkit.getEntities().stream().map(bkt -> (Entity) new net.avatarverse.avatarversalis.bukkit.platform.entity.Entity(bkt)).toList();
	}

	@Override
	public List<LivingEntity> livingEntities() {
		return bukkit.getLivingEntities().stream().map(bkt -> (LivingEntity) new net.avatarverse.avatarversalis.bukkit.platform.entity.LivingEntity(bkt)).toList();
	}

	@Override
	public List<Player> players() {
		return bukkit.getPlayers().stream().map(bkt -> (Player) new net.avatarverse.avatarversalis.bukkit.platform.entity.Player(bkt)).toList();
	}

	@Override
	public long time() {
		return bukkit.getTime();
	}

	@Override
	public long fullTime() {
		return bukkit.getFullTime();
	}

	@Override
	public double humidity(int x, int y, int z) {
		return bukkit.getHumidity(x, y, z);
	}

	@Override
	public double temperature(int x, int y, int z) {
		return bukkit.getTemperature(x, y, z);
	}

	@Override
	public int maxHeight() {
		return bukkit.getMaxHeight();
	}

	@Override
	public int seaLevel() {
		return bukkit.getSeaLevel();
	}

	@Override
	public Collection<Entity> nearbyEntities(Location location, double x, double y, double z, Predicate<Entity> filter) {
		return bukkit.getNearbyEntities(((net.avatarverse.avatarversalis.bukkit.platform.Location) location).bukkit(), x, y, z, bkt -> filter.test(new net.avatarverse.avatarversalis.bukkit.platform.entity.Entity(bkt))).stream().map(bkt -> (Entity) new net.avatarverse.avatarversalis.bukkit.platform.entity.Entity(bkt)).toList();
	}

	@Override
	public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable T data, boolean force) {
		bukkit.spawnParticle(Types.convert(particle), ((net.avatarverse.avatarversalis.bukkit.platform.Location) location).bukkit(), count, offsetX, offsetY, offsetZ, extra, data, force);
	}

	@Override
	public void playSound(Location location, Sound sound, SoundCategory category, float volume, float pitch) {
		bukkit.playSound(((net.avatarverse.avatarversalis.bukkit.platform.Location) location).bukkit(), Types.convert(sound), Types.convert(category), volume, pitch);
	}
}
