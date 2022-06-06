package net.avatarverse.avatarversalis.core.temporary;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.Nullable;

import net.avatarverse.avatarversalis.core.user.User;

import net.jodah.expiringmap.ExpirationListener;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class Cooldown implements Revertible {

	private static final Map<UUID, ExpiringMap<String, Cooldown>> COOLDOWNS = new HashMap<>();

	private final UUID uuid;
	private final String ability;
	private final Runnable endTask;
	@Setter(AccessLevel.NONE) private boolean ended = false;

	private Cooldown(User user, String ability, long duration, @Nullable Runnable endTask) {
		this.uuid = user.uuid();
		this.ability = ability;
		this.endTask = endTask;

		userMap(uuid).put(ability, this, duration, TimeUnit.MILLISECONDS);
	}

	public boolean remove() {
		revert();
		return COOLDOWNS.get(uuid).remove(ability) != null;
	}

	@Override
	public void revert() {
		ended = true;
		if (endTask != null) {
			endTask.run();
		}
	}

	private ExpiringMap<String, Cooldown> userMap(UUID uuid) {
		return COOLDOWNS.putIfAbsent(uuid, ExpiringMap.builder()
				.variableExpiration()
				.expirationPolicy(ExpirationPolicy.CREATED)
				.expirationListener((ExpirationListener<String, Cooldown>) (s, cooldown) -> cooldown.revert())
				.build());
	}

	public static Cooldown of(User user, String ability, long duration, @Nullable Runnable endTask) {
		return new Cooldown(user, ability, duration, endTask);
	}

	public static @Nullable Cooldown find(User user, String ability) {
		ExpiringMap<String, Cooldown> userMap = COOLDOWNS.get(user.uuid());
		return userMap != null ? userMap.get(ability) : null;
	}
}
