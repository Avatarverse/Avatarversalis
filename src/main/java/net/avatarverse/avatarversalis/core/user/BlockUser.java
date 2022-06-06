package net.avatarverse.avatarversalis.core.user;

import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.core.temporary.Cooldown;

import lombok.Getter;

public class BlockUser extends User {

	@Getter private final Block block;

	private BlockUser(Block block) {
		super(UUID.randomUUID());
		this.block = block;
		BLOCK_USERS.put(block, this);
	}

	public static BlockUser registerBlockUser(Block block) {
		Validate.notNull(block, "Block cannot be null");
		return new BlockUser(block);
	}

	public static void unregisterBlockUser(Block block) {
		Validate.notNull(block, "Block cannot be null");
		Optional.ofNullable(of(block)).ifPresent(BlockUser::unregister);
	}

	public static @Nullable BlockUser of(Block block) {
		return BLOCK_USERS.get(block);
	}

	public void unregister() {
		USERS.remove(uuid);
		BLOCK_USERS.remove(block);
	}

	@Override
	public @Nullable Cooldown cooldown(Ability ability) {
		return null;
	}

	@Override
	public boolean canBend(Ability ability) {
		return false;
	}

	@Override
	public int currentSlot() {
		return 0;
	}

	@Override
	public Ability selectedAbility() {
		return null;
	}

	@Override
	public Location location() {
		return block.getLocation();
	}

	@Override
	public Location eyeLocation() {
		return location();
	}

	@Override
	public Block locBlock() {
		return block;
	}

	@Override
	public Block headBlock() {
		return block;
	}

	@Override
	public Vector direction() {
		return location().getDirection();
	}

	@Override
	public Vector velocity() {
		return new Vector();
	}

	@Override
	public float yaw() {
		return 0;
	}

	@Override
	public float pitch() {
		return 0;
	}

	@Override
	public World world() {
		return block.getWorld();
	}

	@Override
	public boolean dead() {
		return false;
	}

	@Override
	public boolean valid() {
		return BLOCK_USERS.containsKey(block);
	}

	@Override
	public boolean spectator() {
		return false;
	}

	@Override
	public boolean sneaking() {
		return true;
	}

	@Override
	public boolean flying() {
		return false;
	}

	@Override
	public boolean onGround() {
		return true;
	}
}
