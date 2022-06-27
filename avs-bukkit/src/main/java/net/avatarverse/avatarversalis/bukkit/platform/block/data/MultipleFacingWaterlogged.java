package net.avatarverse.avatarversalis.bukkit.platform.block.data;

import java.util.Set;
import java.util.stream.Collectors;

import net.avatarverse.avatarversalis.bukkit.util.Types;
import net.avatarverse.avatarversalis.core.platform.block.BlockFace;
import net.avatarverse.avatarversalis.core.platform.block.data.MultipleFacing;
import net.avatarverse.avatarversalis.core.platform.block.data.Waterlogged;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class MultipleFacingWaterlogged extends BlockData implements MultipleFacing, Waterlogged {

	private final org.bukkit.block.data.MultipleFacing multipleFacing;
	private final org.bukkit.block.data.Waterlogged waterlogged;

	public MultipleFacingWaterlogged(org.bukkit.block.data.MultipleFacing multipleFacing, org.bukkit.block.data.Waterlogged waterlogged) {
		super(multipleFacing);
		this.multipleFacing = multipleFacing;
		this.waterlogged = waterlogged;
	}

	@Override
	public Set<BlockFace> faces() {
		return multipleFacing.getFaces().stream().map(Types::convert).collect(Collectors.toSet());
	}

	@Override
	public Set<BlockFace> allowedFaces() {
		return multipleFacing.getAllowedFaces().stream().map(Types::convert).collect(Collectors.toSet());
	}

	@Override
	public boolean hasFace(BlockFace face) {
		return multipleFacing.hasFace(Types.convert(face));
	}

	@Override
	public MultipleFacing setFace(BlockFace face, boolean has) {
		multipleFacing.setFace(Types.convert(face), has);
		return this;
	}

	@Override
	public boolean waterlogged() {
		return waterlogged.isWaterlogged();
	}

	@Override
	public Waterlogged waterlogged(boolean waterlogged) {
		this.waterlogged.setWaterlogged(waterlogged);
		return this;
	}
}
