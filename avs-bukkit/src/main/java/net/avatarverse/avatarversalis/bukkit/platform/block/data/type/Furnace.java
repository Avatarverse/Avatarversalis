package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.Directional;
import net.avatarverse.avatarversalis.core.platform.block.data.Lightable;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Furnace extends Directional implements net.avatarverse.avatarversalis.core.platform.block.data.type.Furnace {

	private final org.bukkit.block.data.type.Furnace bukkit;

	public Furnace(org.bukkit.block.data.type.Furnace bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public boolean lit() {
		return bukkit.isLit();
	}

	@Override
	public Lightable lit(boolean lit) {
		bukkit.setLit(lit);
		return this;
	}
}
