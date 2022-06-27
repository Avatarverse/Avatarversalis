package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.BlockData;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Snowable extends BlockData implements net.avatarverse.avatarversalis.core.platform.block.data.type.Snowable {

	private final org.bukkit.block.data.Snowable bukkit;

	public Snowable(org.bukkit.block.data.Snowable bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public boolean snowy() {
		return bukkit.isSnowy();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.type.Snowable snowy(boolean snowy) {
		bukkit.setSnowy(snowy);
		return this;
	}
}
