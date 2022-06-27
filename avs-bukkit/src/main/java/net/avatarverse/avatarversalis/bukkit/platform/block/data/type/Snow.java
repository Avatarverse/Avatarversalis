package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.BlockData;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Snow extends BlockData implements net.avatarverse.avatarversalis.core.platform.block.data.type.Snow {

	private final org.bukkit.block.data.type.Snow bukkit;

	public Snow(org.bukkit.block.data.type.Snow bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public int layers() {
		return bukkit.getLayers();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.type.Snow layers(int layers) {
		bukkit.setLayers(layers);
		return this;
	}

	@Override
	public int maxLayers() {
		return bukkit.getMaximumLayers();
	}

	@Override
	public int minLayers() {
		return bukkit.getMinimumLayers();
	}
}
