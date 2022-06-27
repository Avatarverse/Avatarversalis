package net.avatarverse.avatarversalis.bukkit.platform.block.data;

import java.util.Set;
import java.util.stream.Collectors;

import net.avatarverse.avatarversalis.bukkit.util.Types;
import net.avatarverse.avatarversalis.core.platform.block.BlockFace;
import net.avatarverse.avatarversalis.core.platform.block.data.Directional;
import net.avatarverse.avatarversalis.core.platform.block.data.FaceAttachable;
import net.avatarverse.avatarversalis.core.platform.block.data.Powerable;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class DirectionalFaceAttachablePowerable extends BlockData implements Directional, FaceAttachable, Powerable {

	private final org.bukkit.block.data.Directional directional;
	private final org.bukkit.block.data.FaceAttachable faceAttachable;
	private final org.bukkit.block.data.Powerable powerable;

	public DirectionalFaceAttachablePowerable(org.bukkit.block.data.Directional directional, org.bukkit.block.data.FaceAttachable faceAttachable, org.bukkit.block.data.Powerable powerable) {
		super(directional);
		this.directional = directional;
		this.faceAttachable = faceAttachable;
		this.powerable = powerable;
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
	public AttachedFace attachedFace() {
		return Types.convert(faceAttachable.getAttachedFace());
	}

	@Override
	public FaceAttachable attachedFace(AttachedFace face) {
		faceAttachable.setAttachedFace(Types.convert(face));
		return this;
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
}
