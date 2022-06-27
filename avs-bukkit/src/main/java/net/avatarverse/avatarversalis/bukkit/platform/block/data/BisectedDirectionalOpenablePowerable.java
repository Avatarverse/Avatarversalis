package net.avatarverse.avatarversalis.bukkit.platform.block.data;

import java.util.Set;
import java.util.stream.Collectors;

import net.avatarverse.avatarversalis.bukkit.util.Types;
import net.avatarverse.avatarversalis.core.platform.block.BlockFace;
import net.avatarverse.avatarversalis.core.platform.block.data.Bisected;
import net.avatarverse.avatarversalis.core.platform.block.data.Directional;
import net.avatarverse.avatarversalis.core.platform.block.data.Powerable;
import net.avatarverse.avatarversalis.core.platform.block.data.type.Openable;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class BisectedDirectionalOpenablePowerable extends BlockData implements Bisected, Directional, Openable, Powerable {

	private final org.bukkit.block.data.Bisected bisected;
	private final org.bukkit.block.data.Directional directional;
	private final org.bukkit.block.data.Openable openable;
	private final org.bukkit.block.data.Powerable powerable;

	public BisectedDirectionalOpenablePowerable(org.bukkit.block.data.Bisected bisected, org.bukkit.block.data.Directional directional, org.bukkit.block.data.Openable openable, org.bukkit.block.data.Powerable powerable) {
		super(bisected);
		this.bisected = bisected;
		this.directional = directional;
		this.openable = openable;
		this.powerable = powerable;
	}

	@Override
	public Half half() {
		return Types.convert(bisected.getHalf());
	}

	@Override
	public Bisected half(Half half) {
		bisected.setHalf(Types.convert(half));
		return this;
	}

	@Override
	public BlockFace facing() {
		return Types.convert(directional.getFacing());
	}

	@Override
	public Directional facing(BlockFace face) {
		directional.setFacing(Types.convert(face));
		return this;
	}

	@Override
	public Set<BlockFace> faces() {
		return directional.getFaces().stream().map(Types::convert).collect(Collectors.toSet());
	}

	@Override
	public boolean powered() {
		return powerable.isPowered();
	}

	@Override
	public Powerable powered(boolean powered) {
		powerable.setPowered(powered);
		return this;
	}

	@Override
	public boolean open() {
		return openable.isOpen();
	}

	@Override
	public Openable open(boolean open) {
		openable.setOpen(open);
		return this;
	}
}
