package net.avatarverse.avatarversalis.core.user;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.block.Block;

public final class UserManager {

	public static final Map<UUID, User> USERS = new HashMap<>();
	public static final Map<Block, BlockUser> BLOCK_USERS = new HashMap<>();

}
