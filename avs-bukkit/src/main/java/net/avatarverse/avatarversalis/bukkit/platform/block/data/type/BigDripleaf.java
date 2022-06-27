package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.Directional;
import net.avatarverse.avatarversalis.bukkit.util.Types;
import net.avatarverse.avatarversalis.core.platform.block.data.Waterlogged;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class BigDripleaf extends Directional implements net.avatarverse.avatarversalis.core.platform.block.data.type.BigDripleaf {

	private final org.bukkit.block.data.type.BigDripleaf bukkit;

	public BigDripleaf(org.bukkit.block.data.type.BigDripleaf bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public Tilt tilt() {
		return Types.convert(bukkit.getTilt());
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.type.BigDripleaf tilt(Tilt tilt) {
		bukkit.setTilt(Types.convert(tilt));
		return this;
	}

	@Override
	public boolean waterlogged() {
		return bukkit.isWaterlogged();
	}

	@Override
	public Waterlogged waterlogged(boolean waterlogged) {
		bukkit.setWaterlogged(waterlogged);
		return this;
	}
}
