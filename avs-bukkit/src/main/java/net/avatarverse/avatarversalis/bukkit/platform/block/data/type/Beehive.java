package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.Directional;

import lombok.Getter;

@Getter
public class Beehive extends Directional implements net.avatarverse.avatarversalis.core.platform.block.data.type.Beehive {

	private final org.bukkit.block.data.type.Beehive bukkit;

	public Beehive(org.bukkit.block.data.type.Beehive bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public int honeyLevel() {
		return bukkit.getHoneyLevel();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.type.Beehive honeyLevel(int honeyLevel) {
		bukkit.setHoneyLevel(honeyLevel);
		return this;
	}

	@Override
	public int maxHoneyLevel() {
		return bukkit.getMaximumHoneyLevel();
	}
}
