package net.avatarverse.avatarversalis.bukkit.platform.block.data;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class AnaloguePowerable extends BlockData implements net.avatarverse.avatarversalis.core.platform.block.data.AnaloguePowerable {

	private final org.bukkit.block.data.AnaloguePowerable bukkit;

	public AnaloguePowerable(org.bukkit.block.data.AnaloguePowerable bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public int power() {
		return bukkit.getPower();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.AnaloguePowerable power(int power) {
		bukkit.setPower(power);
		return this;
	}

	@Override
	public int maxPower() {
		return bukkit.getMaximumPower();
	}
}
