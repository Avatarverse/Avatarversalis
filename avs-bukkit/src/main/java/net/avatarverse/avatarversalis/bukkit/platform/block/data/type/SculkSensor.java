package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.AnaloguePowerable;
import net.avatarverse.avatarversalis.bukkit.util.Types;
import net.avatarverse.avatarversalis.core.platform.block.data.Waterlogged;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class SculkSensor extends AnaloguePowerable implements net.avatarverse.avatarversalis.core.platform.block.data.type.SculkSensor {

	private final org.bukkit.block.data.type.SculkSensor bukkit;

	public SculkSensor(org.bukkit.block.data.type.SculkSensor bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public Phase phase() {
		return Types.convert(bukkit.getPhase());
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.type.SculkSensor phase(Phase phase) {
		bukkit.setPhase(Types.convert(phase));
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
