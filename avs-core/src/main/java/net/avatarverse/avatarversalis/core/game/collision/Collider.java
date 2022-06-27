package net.avatarverse.avatarversalis.core.game.collision;

import net.avatarverse.avatarversalis.core.platform.Location;

public interface Collider {

	boolean contains(Location point);

	boolean intersects(Collider other);

}
