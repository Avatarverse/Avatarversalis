package net.avatarverse.avatarversalis.bukkit.platform.block.data;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Lightable extends BlockData implements net.avatarverse.avatarversalis.core.platform.block.data.Lightable {

	private final org.bukkit.block.data.Lightable bukkit;

	public Lightable(org.bukkit.block.data.Lightable bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public boolean lit() {
		return bukkit.isLit();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.block.data.Lightable lit(boolean lit) {
		bukkit.setLit(lit);
		return this;
	}
}
