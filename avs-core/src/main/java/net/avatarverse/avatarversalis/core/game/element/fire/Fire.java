package net.avatarverse.avatarversalis.core.game.element.fire;

import net.avatarverse.avatarversalis.core.game.EventBus;
import net.avatarverse.avatarversalis.core.game.Game;
import net.avatarverse.avatarversalis.core.game.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.platform.Material;
import net.avatarverse.avatarversalis.core.platform.block.Block;
import net.avatarverse.avatarversalis.core.platform.block.Furnace;
import net.avatarverse.avatarversalis.core.platform.block.data.BlockData;
import net.avatarverse.avatarversalis.core.platform.block.data.Lightable;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;
import lombok.Setter;

@DefaultAnnotation(NonNull.class)
public final class Fire {

	@Getter @Setter private static DayFactor dayFactor = new DayFactor();
	@Getter @Setter private static BlueFireFactor blueFireFactor = new BlueFireFactor();

	public static boolean ignitable(Block block) {
		return ignitable(block.type());
	}

	public static boolean ignitable(Material material) {
		return material.flammable() || material.burnable();
	}

	/**
	 * Attempts to ignite a block, only if it is a {@link Lightable}
	 * <br>
	 * @see EventBus#postAbilityKindleEvent(User, AbilityInstance)
	 * @param user the {@link User} lighting the Block
	 * @param ability the {@link AbilityInstance} lighting the Block
	 * @param block the {@link Block} to light
	 */
	public static void ignite(User user, AbilityInstance ability, Block block) {
		BlockData data = block.data();
		if (!(data instanceof Lightable lightable)) return;

		if (!Game.eventBus().postAbilityKindleEvent(user, ability)) return;

		lightable.lit(true);

		if (block.state() instanceof Furnace furnace) {
			furnace.burnTime((short) 800);
			furnace.update();
		}
	}

}
