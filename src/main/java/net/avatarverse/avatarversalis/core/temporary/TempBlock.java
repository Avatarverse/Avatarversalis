package net.avatarverse.avatarversalis.core.temporary;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.Nullable;

import net.avatarverse.avatarversalis.core.element.Element;

import net.jodah.expiringmap.ExpirationListener;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class TempBlock implements Revertible {

	private static final ExpiringMap<Block, TempBlock> INSTANCES = ExpiringMap.builder()
			.variableExpiration()
			.expirationPolicy(ExpirationPolicy.CREATED)
			.expirationListener((ExpirationListener<Block, TempBlock>) (b, tb) -> tb.revert())
			.build();

	@Getter private final Block block;
	@Getter private Set<Element> bendableElements;
	@Getter private BlockData newData;
	@Getter private boolean reverted = false;

	private TempBlock(Builder builder) {
		this.block = builder.block;
		this.newData = builder.data;
		this.bendableElements = builder.bendableElements;
		INSTANCES.put(block, this, builder.duration, TimeUnit.MILLISECONDS);
	}

	public static Builder builder(BlockData data) {
		return new Builder(data);
	}

	public static @Nullable TempBlock of(Block block) {
		return INSTANCES.get(block);
	}

	public TempBlock update(BlockData newData) {
		this.newData = newData;
		return this;
	}

	public TempBlock update(Set<Element> bendableElements) {
		this.bendableElements = bendableElements;
		return this;
	}

	public TempBlock update(long duration) {
		INSTANCES.setExpiration(block, System.currentTimeMillis() + duration, TimeUnit.MILLISECONDS);
		return this;
	}

	@Override
	public void revert() {
		reverted = true;
	}

	public boolean bendable(Element element) {
		return bendableElements.contains(element);
	}

	@RequiredArgsConstructor
	public static final class Builder {
		private final BlockData data;
		private Block block;
		private Set<Element> bendableElements;
		private long duration;

		public Builder bendableBy(Element... elements) {
			bendableElements = Set.of(elements);
			return this;
		}

		public Builder duration(long duration) {
			this.duration = duration;
			return this;
		}

		public TempBlock build(Block block) {
			this.block = block;
			TempBlock tb = INSTANCES.get(block);
			if (tb != null) {
				return tb.update(data)
						.update(bendableElements)
						.update(duration);
			}
			return new TempBlock(this);
		}
	}
}
