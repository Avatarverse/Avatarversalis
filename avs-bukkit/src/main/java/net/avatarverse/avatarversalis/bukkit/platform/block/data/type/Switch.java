package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.DirectionalFaceAttachablePowerable;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Switch extends DirectionalFaceAttachablePowerable implements net.avatarverse.avatarversalis.core.platform.block.data.type.Switch {

	private final org.bukkit.block.data.type.Switch bukkit;

	public Switch(org.bukkit.block.data.type.Switch bukkit) {
		super(bukkit, bukkit, bukkit);
		this.bukkit = bukkit;
	}
}
