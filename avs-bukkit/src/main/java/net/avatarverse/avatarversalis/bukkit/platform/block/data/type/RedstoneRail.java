package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.Rail;
import net.avatarverse.avatarversalis.core.platform.block.data.Powerable;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class RedstoneRail extends Rail implements net.avatarverse.avatarversalis.core.platform.block.data.type.RedstoneRail {

	private final org.bukkit.block.data.type.RedstoneRail bukkit;

	public RedstoneRail(org.bukkit.block.data.type.RedstoneRail bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public boolean powered() {
		return bukkit.isPowered();
	}

	@Override
	public Powerable powered(boolean powered) {
		bukkit.setPowered(powered);
		return this;
	}
}
