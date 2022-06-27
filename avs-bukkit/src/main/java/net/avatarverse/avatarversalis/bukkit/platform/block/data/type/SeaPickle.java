package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.Waterlogged;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class SeaPickle extends Waterlogged implements net.avatarverse.avatarversalis.core.platform.block.data.type.SeaPickle {

	private final org.bukkit.block.data.type.SeaPickle bukkit;

	public SeaPickle(org.bukkit.block.data.type.SeaPickle bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public int pickles() {
		return bukkit.getPickles();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.type.SeaPickle pickles(int pickles) {
		bukkit.setPickles(pickles);
		return this;
	}

	@Override
	public int maxPickles() {
		return bukkit.getMaximumPickles();
	}

	@Override
	public int minPickles() {
		return bukkit.getMinimumPickles();
	}
}
