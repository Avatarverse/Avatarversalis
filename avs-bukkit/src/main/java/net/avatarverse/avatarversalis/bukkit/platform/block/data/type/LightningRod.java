package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.Powerable;
import net.avatarverse.avatarversalis.core.platform.block.data.Waterlogged;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class LightningRod extends Powerable implements net.avatarverse.avatarversalis.core.platform.block.data.type.LightningRod {

	private final org.bukkit.block.data.type.LightningRod bukkit;

	public LightningRod(org.bukkit.block.data.type.LightningRod bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
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
