package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.BisectedDirectionalOpenablePowerable;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Door extends BisectedDirectionalOpenablePowerable implements net.avatarverse.avatarversalis.core.platform.block.data.type.Door {

	private final org.bukkit.block.data.type.Door bukkit;

	public Door(org.bukkit.block.data.type.Door bukkit) {
		super(bukkit, bukkit, bukkit, bukkit);
		this.bukkit = bukkit;
	}
}
