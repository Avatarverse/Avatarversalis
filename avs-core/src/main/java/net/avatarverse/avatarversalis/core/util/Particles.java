package net.avatarverse.avatarversalis.core.util;

import com.google.common.base.Preconditions;

import net.avatarverse.avatarversalis.core.game.temporary.TempBlock;
import net.avatarverse.avatarversalis.core.platform.Color;
import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.Particle;
import net.avatarverse.avatarversalis.core.platform.Particle.DustOptions;
import net.avatarverse.avatarversalis.core.util.text.Colors;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@DefaultAnnotation(NonNull.class)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Particles {

	private final Particle particle;
	@Setter private int count = 1;
	@Setter private double offsetX = 0, offsetY = 0, offsetZ = 0;
	@Setter private double extra = 0;
	@Setter private Object data;
	@Setter private FauxLight light;

	private Particles(Color color, float size) {
		particle = Particle.REDSTONE;
		data = new DustOptions(color, size);
	}

	public static Particles of(Particle particle) {
		return new Particles(particle);
	}

	public void spawn(Location location) {
		Preconditions.checkNotNull(location.world(), "Location must have a world in order to be used for particles");
		location.world().spawnParticle(particle, location, count, offsetX, offsetY, offsetZ, extra, data, true);
		if (light != null)
			TempBlock.builder(light.data()).duration(50).build(location.block());
	}

	public Particles offset(double offset) {
		return offsetX(offset).offsetY(offset).offsetZ(offset);
	}

	public static Particles color(String hexValue) {
		return color(hexValue, 1);
	}

	public static Particles color(String hexValue, float size) {
		return new Particles(Colors.hexToColor(hexValue), size);
	}

	public static Particles air() {
		return new Particles(Particle.SPELL);
	}

	public static Particles fire() {
		return new Particles(Particle.FLAME).light(FauxLight.of(15));
	}

	public static Particles blueFire() {
		return new Particles(Particle.SOUL_FIRE_FLAME).light(FauxLight.of(15));
	}

	public static Particles bubble() {
		return new Particles(Particle.WATER_BUBBLE).count(3).offset(0.25);
	}

}
