package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.Waterlogged;
import net.avatarverse.avatarversalis.bukkit.util.Types;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Slab extends Waterlogged implements net.avatarverse.avatarversalis.core.platform.block.data.type.Slab {

	private final org.bukkit.block.data.type.Slab bukkit;

	public Slab(org.bukkit.block.data.type.Slab bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public Type type() {
		return Types.convert(bukkit.getType());
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.type.Slab type(Type type) {
		bukkit.setType(Types.convert(type));
		return this;
	}
}
