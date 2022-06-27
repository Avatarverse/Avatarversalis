package net.avatarverse.avatarversalis.bukkit.platform.block.data;

import java.util.Set;
import java.util.stream.Collectors;

import net.avatarverse.avatarversalis.bukkit.util.Types;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Rail extends Waterlogged implements net.avatarverse.avatarversalis.core.platform.block.data.Rail {

	private final org.bukkit.block.data.Rail bukkit;

	public Rail(org.bukkit.block.data.Rail bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public Shape shape() {
		return Types.convert(bukkit.getShape());
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.Rail shape(Shape shape) {
		bukkit.setShape(Types.convert(shape));
		return this;
	}

	@Override
	public Set<Shape> shapes() {
		return bukkit.getShapes().stream().map(Types::convert).collect(Collectors.toSet());
	}
}
