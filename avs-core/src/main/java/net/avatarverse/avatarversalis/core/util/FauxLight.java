package net.avatarverse.avatarversalis.core.util;

import net.avatarverse.avatarversalis.core.platform.Material;
import net.avatarverse.avatarversalis.core.platform.block.data.type.Light;

import lombok.Getter;

@Getter
public class FauxLight {

	private final Light data;

	private FauxLight(int level) {
		this.data = (Light) Material.LIGHT.createBlockData();
		data.level(level);
	}

	public static FauxLight of(int level) {
		return new FauxLight(level);
	}
}
