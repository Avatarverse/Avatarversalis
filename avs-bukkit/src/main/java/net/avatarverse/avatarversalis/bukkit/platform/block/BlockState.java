package net.avatarverse.avatarversalis.bukkit.platform.block;

import net.avatarverse.avatarversalis.bukkit.util.Types;
import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.Material;
import net.avatarverse.avatarversalis.core.platform.World;
import net.avatarverse.avatarversalis.core.platform.block.Block;
import net.avatarverse.avatarversalis.core.platform.block.data.BlockData;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@DefaultAnnotation(NonNull.class)
@RequiredArgsConstructor
@Getter
public class BlockState implements net.avatarverse.avatarversalis.core.platform.block.BlockState {

	private final org.bukkit.block.BlockState bukkit;

	@Override
	public Block block() {
		return new net.avatarverse.avatarversalis.bukkit.platform.block.Block(bukkit.getBlock());
	}

	@Override
	public Location location() {
		return new net.avatarverse.avatarversalis.bukkit.platform.Location(bukkit.getLocation());
	}

	@Override
	public World world() {
		return new net.avatarverse.avatarversalis.bukkit.platform.World(bukkit.getWorld());
	}

	@Override
	public int x() {
		return bukkit.getX();
	}

	@Override
	public int y() {
		return bukkit.getY();
	}

	@Override
	public int z() {
		return bukkit.getZ();
	}

	@Override
	public BlockData data() {
		return new net.avatarverse.avatarversalis.bukkit.platform.block.data.BlockData(bukkit.getBlockData());
	}

	@Override
	public Material type() {
		return Types.convert(bukkit.getType());
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.BlockState data(BlockData data) {
		bukkit.setBlockData(((net.avatarverse.avatarversalis.bukkit.platform.block.data.BlockData) data).bukkit());
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.BlockState type(Material type) {
		bukkit.setType(Types.convert(type));
		return this;
	}

	@Override
	public boolean update() {
		return bukkit.update();
	}

	@Override
	public boolean update(boolean force) {
		return bukkit.update(force);
	}

	@Override
	public boolean update(boolean force, boolean applyPhysics) {
		return bukkit.update(force, applyPhysics);
	}
}
