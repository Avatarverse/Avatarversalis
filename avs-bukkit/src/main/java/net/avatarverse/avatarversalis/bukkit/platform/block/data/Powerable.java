package net.avatarverse.avatarversalis.bukkit.platform.block.data;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Powerable extends BlockData implements net.avatarverse.avatarversalis.core.platform.block.data.Powerable {

	private final org.bukkit.block.data.Powerable bukkit;

	public Powerable(org.bukkit.block.data.Powerable bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public boolean powered() {
		return bukkit.isPowered();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.Powerable powered(boolean powered) {
		bukkit.setPowered(powered);
		return this;
	}
}
