package net.avatarverse.avatarversalis.bukkit.platform.block.data;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Levelled extends BlockData implements net.avatarverse.avatarversalis.core.platform.block.data.Levelled {

	private final org.bukkit.block.data.Levelled bukkit;

	public Levelled(org.bukkit.block.data.Levelled bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public int level() {
		return bukkit.getLevel();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.Levelled level(int level) {
		bukkit.setLevel(level);
		return this;
	}

	@Override
	public int maxLevel() {
		return bukkit.getMaximumLevel();
	}
}
