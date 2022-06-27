package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.MultipleFacingWaterlogged;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class GlassPane extends MultipleFacingWaterlogged implements net.avatarverse.avatarversalis.core.platform.block.data.type.GlassPane {

	private final org.bukkit.block.data.type.GlassPane bukkit;

	public GlassPane(org.bukkit.block.data.type.GlassPane bukkit) {
		super(bukkit, bukkit);
		this.bukkit = bukkit;
	}
}
