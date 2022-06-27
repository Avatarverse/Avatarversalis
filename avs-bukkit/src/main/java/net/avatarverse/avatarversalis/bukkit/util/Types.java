package net.avatarverse.avatarversalis.bukkit.util;

import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.FaceAttachable;
import org.bukkit.block.data.type.PointedDripstone;
import org.bukkit.block.data.type.RedstoneWire;
import org.bukkit.block.data.type.SculkSensor;
import org.bukkit.block.data.type.Slab;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.block.data.type.Wall;

import net.avatarverse.avatarversalis.core.platform.GameMode;
import net.avatarverse.avatarversalis.core.platform.Material;
import net.avatarverse.avatarversalis.core.platform.Particle;
import net.avatarverse.avatarversalis.core.platform.Sound;
import net.avatarverse.avatarversalis.core.platform.SoundCategory;
import net.avatarverse.avatarversalis.core.platform.block.BlockFace;
import net.avatarverse.avatarversalis.core.platform.block.data.Bisected.Half;
import net.avatarverse.avatarversalis.core.platform.block.data.FaceAttachable.AttachedFace;
import net.avatarverse.avatarversalis.core.platform.block.data.Rail;
import net.avatarverse.avatarversalis.core.platform.block.data.type.Bamboo;
import net.avatarverse.avatarversalis.core.platform.block.data.type.Bamboo.Leaves;
import net.avatarverse.avatarversalis.core.platform.block.data.type.BigDripleaf;
import net.avatarverse.avatarversalis.core.platform.block.data.type.PointedDripstone.Thickness;
import net.avatarverse.avatarversalis.core.platform.block.data.type.RedstoneWire.Connection;
import net.avatarverse.avatarversalis.core.platform.block.data.type.SculkSensor.Phase;
import net.avatarverse.avatarversalis.core.platform.block.data.type.Slab.Type;
import net.avatarverse.avatarversalis.core.platform.block.data.type.Stairs.Shape;
import net.avatarverse.avatarversalis.core.platform.block.data.type.Wall.Height;
import net.avatarverse.avatarversalis.core.platform.inventory.MainHand;
import net.avatarverse.avatarversalis.core.platform.potion.PotionEffectType;
import net.avatarverse.avatarversalis.core.platform.scoreboard.DisplaySlot;
import net.avatarverse.avatarversalis.core.platform.util.Vector;
import net.avatarverse.avatarversalis.core.util.Geometry.Axis;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
public class Types {

	public static Vector convert(org.bukkit.util.Vector bukkit) {
		return new Vector(bukkit.getX(), bukkit.getY(), bukkit.getZ());
	}

	public static org.bukkit.util.Vector convert(Vector avs) {
		return new org.bukkit.util.Vector(avs.x(), avs.y(), avs.z());
	}

	public static Material convert(org.bukkit.Material bukkit) {
		try {
			return Material.valueOf(bukkit.name());
		} catch (IllegalArgumentException e) {
			return Material.AIR;
		}
	}

	public static org.bukkit.Material convert(Material avs) {
		try {
			return org.bukkit.Material.valueOf(avs.name());
		} catch (IllegalArgumentException e) {
			return org.bukkit.Material.AIR;
		}
	}

	public static Particle convert(org.bukkit.Particle bukkit) {
		try {
			return Particle.valueOf(bukkit.name());
		} catch (IllegalArgumentException e) {
			return Particle.SPELL;
		}
	}

	public static org.bukkit.Particle convert(Particle avs) {
		try {
			return org.bukkit.Particle.valueOf(avs.name());
		} catch (IllegalArgumentException e) {
			return org.bukkit.Particle.SPELL;
		}
	}

	public static Sound convert(org.bukkit.Sound bukkit) {
		try {
			return Sound.valueOf(bukkit.name());
		} catch (IllegalArgumentException e) {
			return Sound.AMBIENT_CAVE;
		}
	}

	public static org.bukkit.Sound convert(Sound avs) {
		try {
			return org.bukkit.Sound.valueOf(avs.name());
		} catch (IllegalArgumentException e) {
			return org.bukkit.Sound.AMBIENT_CAVE;
		}
	}

	public static SoundCategory convert(org.bukkit.SoundCategory bukkit) {
		try {
			return SoundCategory.valueOf(bukkit.name());
		} catch (IllegalArgumentException e) {
			return SoundCategory.NEUTRAL;
		}
	}

	public static org.bukkit.SoundCategory convert(SoundCategory avs) {
		try {
			return org.bukkit.SoundCategory.valueOf(avs.name());
		} catch (IllegalArgumentException e) {
			return org.bukkit.SoundCategory.NEUTRAL;
		}
	}

	public static GameMode convert(org.bukkit.GameMode bukkit) {
		try {
			return GameMode.valueOf(bukkit.name());
		} catch (IllegalArgumentException e) {
			return GameMode.SURVIVAL;
		}
	}

	public static org.bukkit.GameMode convert(GameMode avs) {
		try {
			return org.bukkit.GameMode.valueOf(avs.name());
		} catch (IllegalArgumentException e) {
			return org.bukkit.GameMode.SURVIVAL;
		}
	}

	public static MainHand convert(org.bukkit.inventory.MainHand bukkit) {
		return switch (bukkit) {
			case LEFT -> MainHand.LEFT;
			case RIGHT -> MainHand.RIGHT;
		};
	}

	public static org.bukkit.inventory.MainHand convert(MainHand avs) {
		return switch (avs) {
			case LEFT -> org.bukkit.inventory.MainHand.LEFT;
			case RIGHT -> org.bukkit.inventory.MainHand.RIGHT;
		};
	}

	public static DisplaySlot convert(org.bukkit.scoreboard.DisplaySlot bukkit) {
		return DisplaySlot.SIDEBAR;
	}

	public static org.bukkit.scoreboard.DisplaySlot convert(DisplaySlot avs) {
		return org.bukkit.scoreboard.DisplaySlot.SIDEBAR;
	}

	public static BlockFace convert(org.bukkit.block.BlockFace bukkit) {
		try {
			return BlockFace.valueOf(bukkit.name());
		} catch (IllegalArgumentException e) {
			return BlockFace.SELF;
		}
	}

	public static org.bukkit.block.BlockFace convert(BlockFace avs) {
		try {
			return org.bukkit.block.BlockFace.valueOf(avs.name());
		} catch (IllegalArgumentException e) {
			return org.bukkit.block.BlockFace.SELF;
		}
	}

	public static PotionEffectType convert(org.bukkit.potion.PotionEffectType bukkit) {
		PotionEffectType type = PotionEffectType.byId(bukkit.getId());
		return type != null ? type : PotionEffectType.LUCK;
	}

	public static org.bukkit.potion.PotionEffectType convert(PotionEffectType avs) {
		org.bukkit.potion.PotionEffectType type = org.bukkit.potion.PotionEffectType.getById(avs.id());
		return type != null ? type : org.bukkit.potion.PotionEffectType.LUCK;
	}

	public static Half convert(Bisected.Half bukkit) {
		return switch (bukkit) {
			case TOP -> Half.TOP;
			case BOTTOM -> Half.BOTTOM;
		};
	}

	public static Bisected.Half convert(Half avs) {
		return switch (avs) {
			case TOP -> Bisected.Half.TOP;
			case BOTTOM -> Bisected.Half.BOTTOM;
		};
	}

	public static AttachedFace convert(FaceAttachable.AttachedFace bukkit) {
		return switch (bukkit) {
			case FLOOR -> AttachedFace.FLOOR;
			case CEILING -> AttachedFace.CEILING;
			case WALL -> AttachedFace.WALL;
		};
	}

	public static FaceAttachable.AttachedFace convert(AttachedFace avs) {
		return switch (avs) {
			case FLOOR -> FaceAttachable.AttachedFace.FLOOR;
			case CEILING -> FaceAttachable.AttachedFace.CEILING;
			case WALL -> FaceAttachable.AttachedFace.WALL;
		};
	}

	public static Axis convert(org.bukkit.Axis bukkit) {
		return switch (bukkit) {
			case X -> Axis.X;
			case Y -> Axis.Y;
			case Z -> Axis.Z;
		};
	}

	public static org.bukkit.Axis convert(Axis avs) {
		return switch (avs) {
			case X -> org.bukkit.Axis.X;
			case Y -> org.bukkit.Axis.Y;
			case Z -> org.bukkit.Axis.Z;
		};
	}

	public static Rail.Shape convert(org.bukkit.block.data.Rail.Shape bukkit) {
		try {
			return Rail.Shape.valueOf(bukkit.name());
		} catch (IllegalArgumentException e) {
			return Rail.Shape.NORTH_SOUTH;
		}
	}

	public static org.bukkit.block.data.Rail.Shape convert(Rail.Shape avs) {
		try {
			return org.bukkit.block.data.Rail.Shape.valueOf(avs.name());
		} catch (IllegalArgumentException e) {
			return org.bukkit.block.data.Rail.Shape.NORTH_SOUTH;
		}
	}

	public static Bamboo.Leaves convert(org.bukkit.block.data.type.Bamboo.Leaves bukkit) {
		return switch (bukkit) {
			case SMALL -> Leaves.SMALL;
			case LARGE -> Leaves.LARGE;
			case NONE -> Leaves.NONE;
		};
	}

	public static org.bukkit.block.data.type.Bamboo.Leaves convert(Bamboo.Leaves avs) {
		return switch (avs) {
			case SMALL -> org.bukkit.block.data.type.Bamboo.Leaves.SMALL;
			case LARGE -> org.bukkit.block.data.type.Bamboo.Leaves.LARGE;
			case NONE -> org.bukkit.block.data.type.Bamboo.Leaves.NONE;
		};
	}

	public static BigDripleaf.Tilt convert(org.bukkit.block.data.type.BigDripleaf.Tilt bukkit) {
		try {
			return BigDripleaf.Tilt.valueOf(bukkit.name());
		} catch (IllegalArgumentException e) {
			return BigDripleaf.Tilt.NONE;
		}
	}

	public static org.bukkit.block.data.type.BigDripleaf.Tilt convert(BigDripleaf.Tilt avs) {
		try {
			return org.bukkit.block.data.type.BigDripleaf.Tilt.valueOf(avs.name());
		} catch (IllegalArgumentException e) {
			return org.bukkit.block.data.type.BigDripleaf.Tilt.NONE;
		}
	}

	public static Thickness convert(PointedDripstone.Thickness bukkit) {
		try {
			return Thickness.valueOf(bukkit.name());
		} catch (IllegalArgumentException e) {
			return Thickness.BASE;
		}
	}

	public static PointedDripstone.Thickness convert(Thickness avs) {
		try {
			return PointedDripstone.Thickness.valueOf(avs.name());
		} catch (IllegalArgumentException e) {
			return PointedDripstone.Thickness.BASE;
		}
	}

	public static Connection convert(RedstoneWire.Connection bukkit) {
		return switch (bukkit) {
			case UP -> Connection.UP;
			case SIDE -> Connection.SIDE;
			case NONE -> Connection.NONE;
		};
	}

	public static RedstoneWire.Connection convert(Connection avs) {
		return switch (avs) {
			case UP -> RedstoneWire.Connection.UP;
			case SIDE -> RedstoneWire.Connection.SIDE;
			case NONE -> RedstoneWire.Connection.NONE;
		};
	}

	public static Phase convert(SculkSensor.Phase bukkit) {
		return switch (bukkit) {
			case ACTIVE -> Phase.ACTIVE;
			case INACTIVE -> Phase.INACTIVE;
			case COOLDOWN -> Phase.COOLDOWN;
		};
	}

	public static SculkSensor.Phase convert(Phase avs) {
		return switch (avs) {
			case ACTIVE -> SculkSensor.Phase.ACTIVE;
			case INACTIVE -> SculkSensor.Phase.INACTIVE;
			case COOLDOWN -> SculkSensor.Phase.COOLDOWN;
		};
	}

	public static Type convert(Slab.Type bukkit) {
		return switch (bukkit) {
			case BOTTOM -> Type.BOTTOM;
			case TOP -> Type.TOP;
			case DOUBLE -> Type.DOUBLE;
		};
	}

	public static Slab.Type convert(Type avs) {
		return switch (avs) {
			case BOTTOM -> Slab.Type.BOTTOM;
			case TOP -> Slab.Type.TOP;
			case DOUBLE -> Slab.Type.DOUBLE;
		};
	}

	public static Shape convert(Stairs.Shape bukkit) {
		try {
			return Shape.valueOf(bukkit.name());
		} catch (IllegalArgumentException e) {
			return Shape.STRAIGHT;
		}
	}

	public static Stairs.Shape convert(Shape avs) {
		try {
			return Stairs.Shape.valueOf(avs.name());
		} catch (IllegalArgumentException e) {
			return Stairs.Shape.STRAIGHT;
		}
	}

	public static Height convert(Wall.Height bukkit) {
		return switch (bukkit) {
			case LOW -> Height.LOW;
			case TALL -> Height.TALL;
			case NONE -> Height.NONE;
		};
	}

	public static Wall.Height convert(Height avs) {
		return switch (avs) {
			case LOW -> Wall.Height.LOW;
			case TALL -> Wall.Height.TALL;
			case NONE -> Wall.Height.NONE;
		};
	}

}
