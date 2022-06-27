package net.avatarverse.avatarversalis.bukkit.platform.block.data;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Waterlogged extends BlockData implements net.avatarverse.avatarversalis.core.platform.block.data.Waterlogged {

	private final org.bukkit.block.data.Waterlogged bukkit;

	public Waterlogged(org.bukkit.block.data.Waterlogged bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public boolean waterlogged() {
		return bukkit.isWaterlogged();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.Waterlogged waterlogged(boolean waterlogged) {
		bukkit.setWaterlogged(waterlogged);
		return this;
	}
}
