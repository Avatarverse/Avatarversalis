package net.avatarverse.avatarversalis.core.platform.block.data;

public interface FaceAttachable extends BlockData {

	AttachedFace attachedFace();
	FaceAttachable attachedFace(AttachedFace face);

	enum AttachedFace {
		FLOOR,
		CEILING,
		WALL
	}
}
