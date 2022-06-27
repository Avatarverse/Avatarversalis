package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import java.util.Set;
import java.util.stream.Collectors;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.AnaloguePowerable;
import net.avatarverse.avatarversalis.bukkit.util.Types;
import net.avatarverse.avatarversalis.core.platform.block.BlockFace;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class RedstoneWire extends AnaloguePowerable implements net.avatarverse.avatarversalis.core.platform.block.data.type.RedstoneWire {

	private final org.bukkit.block.data.type.RedstoneWire bukkit;

	public RedstoneWire(org.bukkit.block.data.type.RedstoneWire bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public Connection face(BlockFace face) {
		return Types.convert(bukkit.getFace(Types.convert(face)));
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.type.RedstoneWire face(BlockFace face, Connection connection) {
		bukkit.setFace(Types.convert(face), Types.convert(connection));
		return this;
	}

	@Override
	public Set<BlockFace> allowedFaces() {
		return bukkit.getAllowedFaces().stream().map(Types::convert).collect(Collectors.toSet());
	}
}
