package net.avatarverse.avatarversalis.bukkit.platform.block.data;

import java.util.Set;
import java.util.stream.Collectors;

import net.avatarverse.avatarversalis.bukkit.util.Types;
import net.avatarverse.avatarversalis.core.platform.block.BlockFace;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Directional extends BlockData implements net.avatarverse.avatarversalis.core.platform.block.data.Directional {

	private final org.bukkit.block.data.Directional bukkit;

	public Directional(org.bukkit.block.data.Directional bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public BlockFace facing() {
		return Types.convert(bukkit.getFacing());
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.Directional facing(BlockFace face) {
		bukkit.setFacing(Types.convert(face));
		return this;
	}

	@Override
	public Set<BlockFace> faces() {
		return bukkit.getFaces().stream().map(Types::convert).collect(Collectors.toSet());
	}
}
