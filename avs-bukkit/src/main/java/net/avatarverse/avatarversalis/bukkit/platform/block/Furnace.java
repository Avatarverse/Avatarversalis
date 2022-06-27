package net.avatarverse.avatarversalis.bukkit.platform.block;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Furnace extends BlockState implements net.avatarverse.avatarversalis.core.platform.block.Furnace {

	private final org.bukkit.block.Furnace bukkit;

	public Furnace(org.bukkit.block.Furnace bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public short burnTime() {
		return bukkit.getBurnTime();
	}

	@Override
	public short cookTime() {
		return bukkit.getCookTime();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.Furnace burnTime(short burnTime) {
		bukkit.setBurnTime(burnTime);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.Furnace cookTime(short cookTime) {
		bukkit.setCookTime(cookTime);
		return this;
	}
}
