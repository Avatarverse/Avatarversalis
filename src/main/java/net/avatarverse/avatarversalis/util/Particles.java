package net.avatarverse.avatarversalis.util;

import org.apache.commons.lang.Validate;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;

import net.avatarverse.avatarversalis.core.element.Element;
import net.avatarverse.avatarversalis.core.temporary.TempBlock;
import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.util.text.Colors;

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
		Validate.notNull(location.getWorld(), "Location must have a world in order to be used for particles");
		location.getWorld().spawnParticle(particle, location, count, offsetX, offsetY, offsetZ, extra, data, true);
		if (light != null)
			TempBlock.builder(light.data()).duration(50).build(location.getBlock());
	}

	public Particles offset(double offset) {
		return offsetX(offset).offsetY(offset).offsetZ(offset);
	}

	public static Particles color(String hexValue) {
		return color(hexValue, 1);
	}

	public static Particles color(String hexValue, float size) {
		return new Particles(Colors.hexToBukkitColor(hexValue), size);
	}

	public static Particles air() {
		return new Particles(Particle.SPELL);
	}

	public static Particles fire(User user) {
		Particle particle = user.hasElement(Element.BLUE_FIRE) ? Particle.SOUL_FIRE_FLAME : Particle.FLAME;
		return new Particles(particle).light(FauxLight.of(15));
	}

	public static Particles fire() {
		return new Particles(Particle.FLAME).light(FauxLight.of(15));
	}

	public static Particles bubble() {
		return new Particles(Particle.WATER_BUBBLE).count(3).offset(0.25);
	}

}
