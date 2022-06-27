package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.Directional;
import net.avatarverse.avatarversalis.core.platform.block.data.Powerable;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Repeater extends Directional implements net.avatarverse.avatarversalis.core.platform.block.data.type.Repeater {

	private final org.bukkit.block.data.type.Repeater bukkit;

	public Repeater(org.bukkit.block.data.type.Repeater bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public int delay() {
		return bukkit.getDelay();
	}

	@Override
	public int maxDelay() {
		return bukkit.getMaximumDelay();
	}

	@Override
	public int minDelay() {
		return bukkit.getMinimumDelay();
	}

	@Override
	public boolean locked() {
		return bukkit.isLocked();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.type.Repeater delay(int delay) {
		bukkit.setDelay(delay);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.type.Repeater locked(boolean locked) {
		bukkit.setLocked(locked);
		return this;
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
