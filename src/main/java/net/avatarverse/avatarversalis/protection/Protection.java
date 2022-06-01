package net.avatarverse.avatarversalis.protection;

import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;

@FunctionalInterface
public interface Protection {
	boolean canBuild(LivingEntity entity, Block block);
}
