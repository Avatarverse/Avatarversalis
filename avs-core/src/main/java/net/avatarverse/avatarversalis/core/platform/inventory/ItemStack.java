package net.avatarverse.avatarversalis.core.platform.inventory;

import net.avatarverse.avatarversalis.core.platform.Material;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ItemStack {

	private final Material type;
	private final int amount;

	public ItemStack(Material type) {
		this(type, 1);
	}
}
