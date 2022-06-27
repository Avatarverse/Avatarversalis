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
public class MultipleFacing extends BlockData implements net.avatarverse.avatarversalis.core.platform.block.data.MultipleFacing {

	private final org.bukkit.block.data.MultipleFacing bukkit;

	public MultipleFacing(org.bukkit.block.data.MultipleFacing bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public Set<BlockFace> faces() {
		return bukkit.getFaces().stream().map(Types::convert).collect(Collectors.toSet());
	}

	@Override
	public Set<BlockFace> allowedFaces() {
		return bukkit.getAllowedFaces().stream().map(Types::convert).collect(Collectors.toSet());
	}

	@Override
	public boolean hasFace(BlockFace face) {
		return bukkit.hasFace(Types.convert(face));
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.MultipleFacing setFace(BlockFace face, boolean has) {
		bukkit.setFace(Types.convert(face), has);
		return this;
	}
}
