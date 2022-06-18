package net.avatarverse.avatarversalis.core.ability;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.event.user.UserBindChangeEvent;
import net.avatarverse.avatarversalis.event.user.UserBindChangeEvent.Result;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

@DefaultAnnotation(NonNull.class)
public class MultiAbilityManager {

	public static final Map<User, MultiAbilityInfo> MULTI_ABILITIES = new ConcurrentHashMap<>();

	public static @Nullable MultiAbilityInfo info(User user) {
		return MULTI_ABILITIES.get(user);
	}

	public static void bind(User user, Ability multiAbility) {
		if (!multiAbility.multiAbility()) return;
		if (new UserBindChangeEvent(user, multiAbility, 0, Result.BIND).call().isCancelled()) return;

		MultiAbilityInfo info = new MultiAbilityInfo(user, multiAbility, user.currentSlot(), user.binds());

		user.clearBinds();
		for (int i = 0; i < multiAbility.subAbilities().size(); i++)
			user.bind(multiAbility.subAbilities().get(i), i);
		user.currentSlot(0);

		MULTI_ABILITIES.put(user, info);
	}

	public static void unbind(User user) {
		MultiAbilityInfo info = info(user);
		if (info == null) return;

		user.clearBinds();
		for (Entry<Integer, Ability> bind : info.prevBinds.entrySet())
			user.bind(bind.getValue(), bind.getKey());
		user.currentSlot(info.prevSlot);

		MULTI_ABILITIES.remove(user);
	}

	public static boolean contains(User user) {
		return info(user) != null;
	}

	public record MultiAbilityInfo(User user, Ability ability, int prevSlot, Map<Integer, Ability> prevBinds) {}

}
