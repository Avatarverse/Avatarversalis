package net.avatarverse.avatarversalis.bukkit.platform.block.data.type;

import net.avatarverse.avatarversalis.bukkit.platform.block.data.MultipleFacing;
import net.avatarverse.avatarversalis.core.platform.block.data.Ageable;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Fire extends MultipleFacing implements net.avatarverse.avatarversalis.core.platform.block.data.type.Fire {

	private final org.bukkit.block.data.type.Fire bukkit;

	public Fire(org.bukkit.block.data.type.Fire bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public int age() {
		return bukkit.getAge();
	}

	@Override
	public Ageable age(int age) {
		bukkit.setAge(age);
		return this;
	}

	@Override
	public int maxAge() {
		return bukkit.getMaximumAge();
	}
}
