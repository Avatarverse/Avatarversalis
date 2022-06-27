package net.avatarverse.avatarversalis.bukkit.platform.block.data;

import net.avatarverse.avatarversalis.bukkit.util.Types;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Bisected extends BlockData implements net.avatarverse.avatarversalis.core.platform.block.data.Bisected {

	private final org.bukkit.block.data.Bisected bukkit;

	public Bisected(org.bukkit.block.data.Bisected bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public Half half() {
		return Types.convert(bukkit.getHalf());
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.Bisected half(Half half) {
		bukkit.setHalf(Types.convert(half));
		return this;
	}
}
