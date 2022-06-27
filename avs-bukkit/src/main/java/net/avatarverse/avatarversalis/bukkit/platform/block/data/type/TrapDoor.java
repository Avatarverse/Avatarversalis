package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.BisectedDirectionalOpenablePowerable;
import net.avatarverse.avatarversalis.core.platform.block.data.Waterlogged;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class TrapDoor extends BisectedDirectionalOpenablePowerable implements net.avatarverse.avatarversalis.core.platform.block.data.type.TrapDoor {

	private final org.bukkit.block.data.type.TrapDoor bukkit;

	public TrapDoor(org.bukkit.block.data.type.TrapDoor bukkit) {
		super(bukkit, bukkit, bukkit, bukkit);
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
