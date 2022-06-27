package net.avatarverse.avatarversalis.bukkit.platform.block.data;

import java.util.Set;
import java.util.stream.Collectors;

import net.avatarverse.avatarversalis.bukkit.util.Types;
import net.avatarverse.avatarversalis.core.util.Geometry.Axis;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Orientable extends BlockData implements net.avatarverse.avatarversalis.core.platform.block.data.Orientable {

	private final org.bukkit.block.data.Orientable bukkit;

	public Orientable(org.bukkit.block.data.Orientable bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public Axis axis() {
		return Types.convert(bukkit.getAxis());
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.Orientable axis(Axis axis) {
		bukkit.setAxis(Types.convert(axis));
		return this;
	}

	@Override
	public Set<Axis> axes() {
		return bukkit.getAxes().stream().map(Types::convert).collect(Collectors.toSet());
	}
}
