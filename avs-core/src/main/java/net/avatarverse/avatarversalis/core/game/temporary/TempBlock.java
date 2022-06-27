package net.avatarverse.avatarversalis.core.game.temporary;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import net.avatarverse.avatarversalis.core.game.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.game.element.Element;
import net.avatarverse.avatarversalis.core.platform.block.Block;
import net.avatarverse.avatarversalis.core.platform.block.data.BlockData;

import net.jodah.expiringmap.ExpirationListener;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@DefaultAnnotation(NonNull.class)
@Getter
public class TempBlock implements Revertible {

	private static final ExpiringMap<Block, TempBlock> INSTANCES = ExpiringMap.builder()
			.variableExpiration()
			.expirationPolicy(ExpirationPolicy.CREATED)
			.expirationListener((ExpirationListener<Block, TempBlock>) (b, tb) -> tb.revert())
			.build();

	private final Block block;
	private AbilityInstance ability;
	private Set<Element> bendableElements;
	private BlockData newData;
	private boolean reverted = false;

	private TempBlock(Builder builder) {
		this.block = builder.block;
		this.ability = builder.ability;
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

	public TempBlock update(AbilityInstance ability) {
		this.ability = ability;
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
		private AbilityInstance ability;
		private Set<Element> bendableElements;
		private long duration;

		public Builder ability(AbilityInstance ability) {
			this.ability = ability;
			return this;
		}

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
						.update(ability)
						.update(bendableElements)
						.update(duration);
			}
			return new TempBlock(this);
		}
	}
}
