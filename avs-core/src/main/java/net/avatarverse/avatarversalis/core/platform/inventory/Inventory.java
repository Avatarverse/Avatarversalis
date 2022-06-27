package net.avatarverse.avatarversalis.core.platform.inventory;

public interface Inventory {
	ItemStack mainHand();
	ItemStack offHand();
	Inventory mainHand(ItemStack item);
	Inventory offHand(ItemStack item);
}
