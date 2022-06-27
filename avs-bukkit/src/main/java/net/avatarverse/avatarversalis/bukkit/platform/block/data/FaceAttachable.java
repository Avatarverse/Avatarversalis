package net.avatarverse.avatarversalis.bukkit.platform.block.data;

import net.avatarverse.avatarversalis.bukkit.util.Types;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class FaceAttachable extends BlockData implements net.avatarverse.avatarversalis.core.platform.block.data.FaceAttachable {

	private final org.bukkit.block.data.FaceAttachable bukkit;

	public FaceAttachable(org.bukkit.block.data.FaceAttachable bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public AttachedFace attachedFace() {
		return Types.convert(bukkit.getAttachedFace());
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.FaceAttachable attachedFace(AttachedFace face) {
		bukkit.setAttachedFace(Types.convert(face));
		return this;
	}
}
