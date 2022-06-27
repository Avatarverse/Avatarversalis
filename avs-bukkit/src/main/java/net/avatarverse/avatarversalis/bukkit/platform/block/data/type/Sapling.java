package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.BlockData;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Sapling extends BlockData implements net.avatarverse.avatarversalis.core.platform.block.data.type.Sapling {

	private final org.bukkit.block.data.type.Sapling bukkit;

	public Sapling(org.bukkit.block.data.type.Sapling bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public int stage() {
		return bukkit.getStage();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.type.Sapling stage(int stage) {
		bukkit.setStage(stage);
		return this;
	}

	@Override
	public int maxStage() {
		return bukkit.getMaximumStage();
	}
}
