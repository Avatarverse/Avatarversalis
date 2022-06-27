package net.avatarverse.avatarversalis.core.game.collision;

import net.avatarverse.avatarversalis.core.platform.Location;

public class AABB implements Collider {
	@Override
	public boolean contains(Location point) {
		return false;
	}

	@Override
	public boolean intersects(Collider other) {
		return false;
	}
}
