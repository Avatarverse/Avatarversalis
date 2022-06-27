package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.Waterlogged;
import net.avatarverse.avatarversalis.bukkit.util.Types;
import net.avatarverse.avatarversalis.core.platform.block.BlockFace;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Wall extends Waterlogged implements net.avatarverse.avatarversalis.core.platform.block.data.type.Wall {

	private final org.bukkit.block.data.type.Wall bukkit;

	public Wall(org.bukkit.block.data.type.Wall bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public Height height(BlockFace face) {
		return Types.convert(bukkit.getHeight(Types.convert(face)));
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.type.Wall height(BlockFace face, Height height) {
		bukkit.setHeight(Types.convert(face), Types.convert(height));
		return this;
	}

	@Override
	public boolean up() {
		return bukkit.isUp();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.type.Wall up(boolean up) {
		bukkit.setUp(up);
		return this;
	}
}
