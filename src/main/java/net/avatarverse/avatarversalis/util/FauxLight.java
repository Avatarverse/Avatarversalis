package net.avatarverse.avatarversalis.util;

import org.bukkit.Material;
import org.bukkit.block.data.type.Light;

import lombok.Getter;

@Getter
public class FauxLight {

	private final Light data;

	private FauxLight(int level) {
		this.data = (Light) Material.LIGHT.createBlockData();
		data.setLevel(level);
	}

	public static FauxLight of(int level) {
		return new FauxLight(level);
	}
}
