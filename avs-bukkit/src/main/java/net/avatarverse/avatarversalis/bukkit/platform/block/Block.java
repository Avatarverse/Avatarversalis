package net.avatarverse.avatarversalis.bukkit.platform.block;

import net.avatarverse.avatarversalis.bukkit.util.Types;
import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.Material;
import net.avatarverse.avatarversalis.core.platform.World;
import net.avatarverse.avatarversalis.core.platform.block.BlockFace;
import net.avatarverse.avatarversalis.core.platform.block.BlockState;
import net.avatarverse.avatarversalis.core.platform.block.data.BlockData;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@DefaultAnnotation(NonNull.class)
@RequiredArgsConstructor
@Getter
public class Block implements net.avatarverse.avatarversalis.core.platform.block.Block {

	private final org.bukkit.block.Block bukkit;

	@Override
	public BlockData data() {
		return new net.avatarverse.avatarversalis.bukkit.platform.block.data.BlockData(bukkit.getBlockData());
	}

	@Override
	public BlockState state() {
		return new net.avatarverse.avatarversalis.bukkit.platform.block.BlockState(bukkit.getState());
	}

	@Override
	public Material type() {
		return Types.convert(bukkit.getType());
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
	public net.avatarverse.avatarversalis.core.platform.block.Block data(BlockData data) {
		bukkit.setBlockData(((net.avatarverse.avatarversalis.bukkit.platform.block.data.BlockData) data).bukkit());
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.Block type(Material type) {
		bukkit.setType(Types.convert(type));
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.Block type(Material type, boolean applyPhysics) {
		bukkit.setType(Types.convert(type), applyPhysics);
		return this;
	}

	@Override
	public boolean breakNaturally() {
		return bukkit.breakNaturally();
	}

	@Override
	public boolean solid() {
		return type().solid();
	}

	@Override
	public boolean liquid() {
		return bukkit.isLiquid();
	}

	@Override
	public Location location() {
		return new net.avatarverse.avatarversalis.bukkit.platform.Location(bukkit.getLocation());
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.Block relative(BlockFace face) {
		return new Block(bukkit.getRelative(Types.convert(face)));
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.Block relative(BlockFace face, int distance) {
		return new Block(bukkit.getRelative(Types.convert(face), distance));
	}
}
