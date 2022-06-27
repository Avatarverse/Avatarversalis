package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import java.util.Set;
import java.util.stream.Collectors;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.Waterlogged;
import net.avatarverse.avatarversalis.bukkit.util.Types;
import net.avatarverse.avatarversalis.core.platform.block.BlockFace;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class PointedDripstone extends Waterlogged implements net.avatarverse.avatarversalis.core.platform.block.data.type.PointedDripstone {

	private final org.bukkit.block.data.type.PointedDripstone bukkit;

	public PointedDripstone(org.bukkit.block.data.type.PointedDripstone bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public Thickness thickness() {
		return Types.convert(bukkit.getThickness());
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.type.PointedDripstone thickness(Thickness thickness) {
		bukkit.setThickness(Types.convert(thickness));
		return this;
	}

	@Override
	public BlockFace verticalDirection() {
		return Types.convert(bukkit.getVerticalDirection());
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.type.PointedDripstone verticalDirection(BlockFace direction) {
		bukkit.setVerticalDirection(Types.convert(direction));
		return this;
	}

	@Override
	public Set<BlockFace> verticalDirections() {
		return bukkit.getVerticalDirections().stream().map(Types::convert).collect(Collectors.toSet());
	}
}
