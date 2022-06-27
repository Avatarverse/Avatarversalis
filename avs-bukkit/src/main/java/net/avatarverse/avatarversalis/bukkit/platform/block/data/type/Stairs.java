package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.BisectedDirectionalWaterlogged;
import net.avatarverse.avatarversalis.bukkit.util.Types;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Stairs extends BisectedDirectionalWaterlogged implements net.avatarverse.avatarversalis.core.platform.block.data.type.Stairs {

	private final org.bukkit.block.data.type.Stairs bukkit;

	public Stairs(org.bukkit.block.data.type.Stairs bukkit) {
		super(bukkit, bukkit, bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public Shape shape() {
		return Types.convert(bukkit.getShape());
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.type.Stairs shape(Shape shape) {
		bukkit.setShape(Types.convert(shape));
		return this;
	}
}
