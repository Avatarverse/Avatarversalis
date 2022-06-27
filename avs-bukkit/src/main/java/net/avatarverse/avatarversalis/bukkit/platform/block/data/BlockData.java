package net.avatarverse.avatarversalis.bukkit.platform.block.data;

import net.avatarverse.avatarversalis.bukkit.util.Types;
import net.avatarverse.avatarversalis.core.platform.Material;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@DefaultAnnotation(NonNull.class)
@RequiredArgsConstructor
@Getter
public class BlockData implements net.avatarverse.avatarversalis.core.platform.block.data.BlockData {

	private final org.bukkit.block.data.BlockData bukkit;

	@Override
	public Material material() {
		return Types.convert(bukkit.getMaterial());
	}

	@Override
	public String asString() {
		return bukkit.getAsString();
	}

	@SneakyThrows
	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.BlockData clone() {
		return (net.avatarverse.avatarversalis.core.platform.block.data.BlockData) super.clone();
	}
}
