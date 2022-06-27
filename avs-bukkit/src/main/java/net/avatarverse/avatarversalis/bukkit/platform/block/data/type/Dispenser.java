package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.Directional;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Dispenser extends Directional implements net.avatarverse.avatarversalis.core.platform.block.data.type.Dispenser {

	private final org.bukkit.block.data.type.Dispenser bukkit;

	public Dispenser(org.bukkit.block.data.type.Dispenser bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public boolean triggered() {
		return bukkit.isTriggered();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.type.Dispenser triggered(boolean triggered) {
		bukkit.setTriggered(triggered);
		return this;
	}
}
