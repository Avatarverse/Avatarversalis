package net.avatarverse.avatarversalis.core.element.fire;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Lightable;

import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.event.AbilityKindleEvent;

import lombok.Getter;
import lombok.Setter;

public final class Fire {

	@Getter @Setter private static DayFactor dayFactor = new DayFactor();
	@Getter @Setter private static BlueFireFactor blueFireFactor = new BlueFireFactor();

	public static boolean ignitable(Block block) {
		return ignitable(block.getType());
	}

	public static boolean ignitable(Material material) {
		return material.isFlammable() || material.isBurnable();
	}

	/**
	 * Attempts to ignite a block, only if it is a {@link Lightable}, such as a Furnace
	 * <br>
	 * @see AbilityKindleEvent
	 * @param user the {@link User} lighting the Block
	 * @param ability the {@link AbilityInstance} lighting the Block
	 * @param block the {@link Block} to light
	 */
	public static void ignite(User user, AbilityInstance ability, Block block) {
		BlockData data = block.getBlockData();
		if (!(data instanceof Lightable lightable)) return;

		if (new AbilityKindleEvent(user, ability).call().isCancelled()) return;

		lightable.setLit(true);

		if (lightable instanceof Furnace furnace) {
			furnace.setBurnTime((short) 800);
			furnace.update();
		}
	}

}
