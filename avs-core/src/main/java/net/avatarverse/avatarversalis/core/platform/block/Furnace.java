package net.avatarverse.avatarversalis.core.platform.block;

public interface Furnace extends BlockState {
	short burnTime();
	short cookTime();
	Furnace burnTime(short burnTime);
	Furnace cookTime(short cookTime);
}
