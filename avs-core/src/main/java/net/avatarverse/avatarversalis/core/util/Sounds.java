package net.avatarverse.avatarversalis.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.google.common.base.Preconditions;

import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.Sound;
import net.avatarverse.avatarversalis.core.platform.SoundCategory;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Setter;

@DefaultAnnotation(NonNull.class)
@Setter
public final class Sounds {

	private final Sound sound;
	private final List<Sound> sounds;
	private SoundCategory category;
	private float volume = 1, pitch = 1;

	private Sounds(Sound sound) {
		this.sound = sound;
		this.sounds = new ArrayList<>(Collections.singletonList(sound));
	}

	public static Sounds of(Sound sound) {
		return new Sounds(sound);
	}

	public void play(Location location) {
		play(location, 1);
	}

	/**
	 * Plays the sound at the given location with a given chance.
	 * @param location the {@link Location} at which to play the sound. Must have a valid {@link net.avatarverse.avatarversalis.core.platform.World}.
	 * @param chance a double between 0 and 1, meaning 0% and 100% respectively. Should be 1.0 if the sound should always play.
	 */
	public void play(Location location, double chance) {
		if (ThreadLocalRandom.current().nextInt((int) Math.round(1 / chance)) != 0) return;
		Preconditions.checkNotNull(location.world(), "Location must have a world in order to be used for sounds");

		Sound sound = sounds.isEmpty() ? this.sound : sounds.get(ThreadLocalRandom.current().nextInt(sounds.size()));
		location.world().playSound(location, sound, category, volume, pitch);
	}

	public Sounds and(Sound... sounds) {
		this.sounds.addAll(Arrays.asList(sounds));
		return this;
	}

	public static Sounds water() {
		return new Sounds(Sound.BLOCK_WATER_AMBIENT);
	}

	public static Sounds earth() {
		return new Sounds(Sound.ENTITY_STRIDER_STEP).and(Sound.ENTITY_BOAT_PADDLE_LAND);
	}

	public static Sounds fire() {
		return new Sounds(Sound.BLOCK_FIRE_AMBIENT);
	}

	public static Sounds air() {
		return new Sounds(Sound.ENTITY_HORSE_BREATHE);
	}

	public static Sounds ice() {
		return new Sounds(Sound.ITEM_FLINTANDSTEEL_USE);
	}

	public static Sounds plant() {
		return new Sounds(Sound.BLOCK_GRASS_STEP);
	}

	public static Sounds lava() {
		return new Sounds(Sound.BLOCK_LAVA_AMBIENT);
	}

	public static Sounds metal() {
		return new Sounds(Sound.ENTITY_IRON_GOLEM_HURT);
	}

	public static Sounds sand() {
		return new Sounds(Sound.BLOCK_SAND_BREAK);
	}

	public static Sounds combustion() {
		return new Sounds(Sound.ENTITY_FIREWORK_ROCKET_BLAST);
	}

	public static Sounds lightning() {
		return new Sounds(Sound.BLOCK_BEEHIVE_WORK);
	}

}
