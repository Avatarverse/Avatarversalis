package net.avatarverse.avatarversalis.core.platform.block.data;

import java.util.Set;

import net.avatarverse.avatarversalis.core.util.Geometry.Axis;

public interface Orientable {
	Axis axis();
	Orientable axis(Axis axis);
	Set<Axis> axes();
}
