package net.avatarverse.avatarversalis.core.game.user;

import java.util.UUID;

import com.google.common.base.Preconditions;

import net.avatarverse.avatarversalis.core.game.ability.Ability;
import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.util.Vector;
import net.avatarverse.avatarversalis.core.platform.World;
import net.avatarverse.avatarversalis.core.platform.block.Block;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
public class BlockUser extends User {

	@Getter private final Block block;
	private int currentSlot;

	private BlockUser(Block block) {
		super(UUID.randomUUID());
		this.block = block;
		BLOCK_USERS.put(block, this);
	}

	public static BlockUser registerBlockUser(Block block) {
		Preconditions.checkNotNull(block, "Block cannot be null");
		return new BlockUser(block);
	}

	public static @Nullable BlockUser of(Block block) {
		return BLOCK_USERS.get(block);
	}

	public void unregister() {
		USERS.remove(uuid);
		BLOCK_USERS.remove(block);
	}

	@Override
	public String name() {
		return toString();
	}

	@Override
	public boolean canBend(Ability ability) {
		return false;
	}

	@Override
	public int currentSlot() {
		return currentSlot;
	}

	@Override
	public void currentSlot(int slot) {
		this.currentSlot = slot;
	}

	@Override
	public Ability selectedAbility() {
		return null;
	}

	@Override
	public Location location() {
		return block.center();
	}

	@Override
	public Location eyeLocation() {
		return location();
	}

	@Override
	public Location handLocation() {
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
		return location().direction();
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
		return block.world();
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

	@Override
	public String toString() {
		return "BlockUser:{" + block.world().name() + "," + block.x() + "," + block.y() + "," + block.z() + "}";
	}
}
