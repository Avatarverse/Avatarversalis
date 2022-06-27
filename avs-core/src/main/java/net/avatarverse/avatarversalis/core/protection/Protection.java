package net.avatarverse.avatarversalis.core.protection;

import net.avatarverse.avatarversalis.core.platform.block.Block;
import net.avatarverse.avatarversalis.core.platform.entity.LivingEntity;

@FunctionalInterface
public interface Protection {
	boolean canBuild(LivingEntity entity, Block block);
}
