package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.BlockData;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Openable extends BlockData implements net.avatarverse.avatarversalis.core.platform.block.data.type.Openable {

	private final org.bukkit.block.data.Openable bukkit;

	public Openable(org.bukkit.block.data.Openable bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public boolean open() {
		return bukkit.isOpen();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.type.Openable open(boolean open) {
		bukkit.setOpen(open);
		return this;
	}
}
