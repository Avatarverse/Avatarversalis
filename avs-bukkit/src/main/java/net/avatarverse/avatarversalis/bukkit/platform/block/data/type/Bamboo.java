package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.BlockData;
import net.avatarverse.avatarversalis.bukkit.util.Types;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Bamboo extends BlockData implements net.avatarverse.avatarversalis.core.platform.block.data.type.Bamboo {

	private final org.bukkit.block.data.type.Bamboo bukkit;

	public Bamboo(org.bukkit.block.data.type.Bamboo bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public Leaves leaves() {
		return Types.convert(bukkit.getLeaves());
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.type.Bamboo leaves(Leaves leaves) {
		bukkit.setLeaves(Types.convert(leaves));
		return this;
	}
}
