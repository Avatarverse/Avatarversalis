package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.BlockData;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Farmland extends BlockData implements net.avatarverse.avatarversalis.core.platform.block.data.type.Farmland {

	private final org.bukkit.block.data.type.Farmland bukkit;

	public Farmland(org.bukkit.block.data.type.Farmland bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public int moisture() {
		return bukkit.getMoisture();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.type.Farmland moisture(int moisture) {
		bukkit.setMoisture(moisture);
		return this;
	}

	@Override
	public int maxMoisture() {
		return bukkit.getMaximumMoisture();
	}
}
