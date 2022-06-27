package net.avatarverse.avatarversalis.bukkit.platform.block.data;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Ageable extends BlockData implements net.avatarverse.avatarversalis.core.platform.block.data.Ageable {

	private final org.bukkit.block.data.Ageable bukkit;

	public Ageable(org.bukkit.block.data.Ageable bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public int age() {
		return bukkit.getAge();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.Ageable age(int age) {
		bukkit.setAge(age);
		return this;
	}

	@Override
	public int maxAge() {
		return bukkit.getMaximumAge();
	}
}
