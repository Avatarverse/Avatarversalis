package net.avatarverse.avatarversalis.core.platform;

import net.avatarverse.avatarversalis.core.platform.block.data.BlockData;
import net.avatarverse.avatarversalis.core.platform.inventory.ItemStack;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@DefaultAnnotation(NonNull.class)
@RequiredArgsConstructor
@SuppressWarnings("unused")
public enum Particle {
	EXPLOSION_NORMAL,
	EXPLOSION_LARGE,
	EXPLOSION_HUGE,
	FIREWORKS_SPARK,
	WATER_BUBBLE,
	WATER_SPLASH,
	WATER_WAKE,
	SUSPENDED,
	SUSPENDED_DEPTH,
	CRIT,
	CRIT_MAGIC,
	SMOKE_NORMAL,
	SMOKE_LARGE,
	SPELL,
	SPELL_INSTANT,
	SPELL_MOB,
	SPELL_MOB_AMBIENT,
	SPELL_WITCH,
	DRIP_WATER,
	DRIP_LAVA,
	VILLAGER_ANGRY,
	VILLAGER_HAPPY,
	TOWN_AURA,
	NOTE,
	PORTAL,
	ENCHANTMENT_TABLE,
	FLAME,
	LAVA,
	CLOUD,
	REDSTONE(DustOptions.class),
	SNOWBALL,
	SNOW_SHOVEL,
	SLIME,
	HEART,
	ITEM_CRACK(ItemStack.class),
	BLOCK_CRACK(BlockData.class),
	BLOCK_DUST(BlockData.class),
	WATER_DROP,
	MOB_APPEARANCE,
	DRAGON_BREATH,
	END_ROD,
	DAMAGE_INDICATOR,
	SWEEP_ATTACK,
	FALLING_DUST(BlockData.class),
	TOTEM,
	SPIT,
	SQUID_INK,
	BUBBLE_POP,
	CURRENT_DOWN,
	BUBBLE_COLUMN_UP,
	NAUTILUS,
	DOLPHIN,
	SNEEZE,
	CAMPFIRE_COSY_SMOKE,
	CAMPFIRE_SIGNAL_SMOKE,
	COMPOSTER,
	FLASH,
	FALLING_LAVA,
	LANDING_LAVA,
	FALLING_WATER,
	DRIPPING_HONEY,
	FALLING_HONEY,
	LANDING_HONEY,
	FALLING_NECTAR,
	SOUL_FIRE_FLAME,
	ASH,
	CRIMSON_SPORE,
	WARPED_SPORE,
	SOUL,
	DRIPPING_OBSIDIAN_TEAR,
	FALLING_OBSIDIAN_TEAR,
	LANDING_OBSIDIAN_TEAR,
	REVERSE_PORTAL,
	WHITE_ASH,
	DUST_COLOR_TRANSITION(DustTransition.class),
	VIBRATION,
	FALLING_SPORE_BLOSSOM,
	SPORE_BLOSSOM_AIR,
	SMALL_FLAME,
	SNOWFLAKE,
	DRIPPING_DRIPSTONE_LAVA,
	FALLING_DRIPSTONE_LAVA,
	DRIPPING_DRIPSTONE_WATER,
	FALLING_DRIPSTONE_WATER,
	GLOW_SQUID_INK,
	GLOW,
	WAX_ON,
	WAX_OFF,
	ELECTRIC_SPARK,
	SCRAPE,
	SONIC_BOOM,
	SCULK_SOUL,
	SCULK_CHARGE(Float.class),
	SCULK_CHARGE_POP,
	SHRIEK(Integer.class);

	@Getter private final Class<?> data;

	Particle() {
		data = Void.class;
	}

	@RequiredArgsConstructor
	@Getter
	public static class DustOptions {
		private final Color color;
		private final float size;
	}

	@Getter
	public static class DustTransition extends DustOptions {
		private final Color toColor;

		public DustTransition(Color fromColor, Color toColor, float size) {
			super(fromColor, size);
			this.toColor = toColor;
		}
	}
}
