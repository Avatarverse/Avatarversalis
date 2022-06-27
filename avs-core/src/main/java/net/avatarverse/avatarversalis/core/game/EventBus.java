package net.avatarverse.avatarversalis.core.game;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import net.avatarverse.avatarversalis.core.event.ability.AbilityAffectEntityEvent;
import net.avatarverse.avatarversalis.core.event.ability.AbilityConfigLoadEvent;
import net.avatarverse.avatarversalis.core.event.ability.AbilityEndEvent;
import net.avatarverse.avatarversalis.core.event.ability.AbilityKindleEvent;
import net.avatarverse.avatarversalis.core.event.ability.AbilityStartEvent;
import net.avatarverse.avatarversalis.core.event.ability.AbilityUpdateEvent;
import net.avatarverse.avatarversalis.core.event.user.UserBindChangeEvent;
import net.avatarverse.avatarversalis.core.event.user.UserBindCopyEvent;
import net.avatarverse.avatarversalis.core.event.user.UserCooldownEvent;
import net.avatarverse.avatarversalis.core.event.user.UserElementChangeEvent;
import net.avatarverse.avatarversalis.core.event.user.UserPermaremoveEvent;
import net.avatarverse.avatarversalis.core.event.user.UserPresetEvent;
import net.avatarverse.avatarversalis.core.event.world.WorldSunriseEvent;
import net.avatarverse.avatarversalis.core.event.world.WorldSunsetEvent;
import net.avatarverse.avatarversalis.core.game.ability.Ability;
import net.avatarverse.avatarversalis.core.game.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.game.element.Element;
import net.avatarverse.avatarversalis.core.game.temporary.Cooldown;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.game.user.preset.Preset;
import net.avatarverse.avatarversalis.core.listener.ability.AbilityAffectEntityListener;
import net.avatarverse.avatarversalis.core.listener.ability.AbilityConfigLoadListener;
import net.avatarverse.avatarversalis.core.listener.ability.AbilityEndListener;
import net.avatarverse.avatarversalis.core.listener.ability.AbilityKindleListener;
import net.avatarverse.avatarversalis.core.listener.ability.AbilityStartListener;
import net.avatarverse.avatarversalis.core.listener.ability.AbilityUpdateListener;
import net.avatarverse.avatarversalis.core.listener.user.UserBindChangeListener;
import net.avatarverse.avatarversalis.core.listener.user.UserBindCopyListener;
import net.avatarverse.avatarversalis.core.listener.user.UserCooldownListener;
import net.avatarverse.avatarversalis.core.listener.user.UserElementChangeListener;
import net.avatarverse.avatarversalis.core.listener.user.UserPermaremoveListener;
import net.avatarverse.avatarversalis.core.listener.user.UserPresetListener;
import net.avatarverse.avatarversalis.core.listener.world.WorldSunriseListener;
import net.avatarverse.avatarversalis.core.listener.world.WorldSunsetListener;
import net.avatarverse.avatarversalis.core.platform.World;
import net.avatarverse.avatarversalis.core.platform.entity.Entity;
import net.avatarverse.avatarversalis.core.util.Effects;

public abstract class EventBus {

	public boolean postAbilityConfigLoadEvent(User user, AbilityInstance ability) {
		AbilityConfigLoadEvent event = abilityConfigLoadEvent(user, ability);
		boolean cancelled = event.call().cancelled();
		if (!cancelled)
			Game.listeners(AbilityConfigLoadListener.class).forEach(l -> l.onAbilityConfigLoad(event));
		return cancelled;
	}

	public boolean postAbilityStartEvent(User user, AbilityInstance ability) {
		AbilityStartEvent event = abilityStartEvent(user, ability);
		boolean cancelled = event.call().cancelled();
		if (!cancelled)
			Game.listeners(AbilityStartListener.class).forEach(l -> l.onAbilityStart(event));
		return cancelled;
	}

	public boolean postAbilityUpdateEvent(User user, AbilityInstance ability) {
		AbilityUpdateEvent event = abilityUpdateEvent(user, ability);
		boolean cancelled = event.call().cancelled();
		if (!cancelled)
			Game.listeners(AbilityUpdateListener.class).forEach(l -> l.onAbilityUpdate(event));
		return cancelled;
	}

	public boolean postAbilityEndEvent(User user, AbilityInstance ability) {
		AbilityEndEvent event = abilityEndEvent(user, ability);
		boolean cancelled = event.call().cancelled();
		if (!cancelled)
			Game.listeners(AbilityEndListener.class).forEach(l -> l.onAbilityEnd(event));
		return cancelled;
	}

	public boolean postAbilityAffectEntityEvent(Effects effects, Entity entity) {
		AbilityAffectEntityEvent event = abilityAffectEntityEvent(effects, entity);
		boolean cancelled = event.call().cancelled();
		if (!cancelled)
			Game.listeners(AbilityAffectEntityListener.class).forEach(l -> l.onAbilityAffectEntity(event));
		return cancelled;
	}

	public boolean postAbilityKindleEvent(User user, AbilityInstance ability) {
		AbilityKindleEvent event = abilityKindleEvent(user, ability);
		boolean cancelled = event.call().cancelled();
		if (!cancelled)
			Game.listeners(AbilityKindleListener.class).forEach(l -> l.onAbilityKindle(event));
		return cancelled;
	}

	public boolean postUserBindEvent(User user, Ability ability, int slot) {
		return postUserBindChangeEvent(userBindEvent(user, ability, slot));
	}

	public boolean postUserUnbindEvent(User user, Ability ability, int slot) {
		return postUserBindChangeEvent(userUnbindEvent(user, ability, slot));
	}

	public boolean postUserBindChangeEvent(UserBindChangeEvent event) {
		boolean cancelled = event.call().cancelled();
		if (!cancelled)
			Game.listeners(UserBindChangeListener.class).forEach(l -> l.onUserBindChange(event));
		return cancelled;
	}

	public boolean postUserBindCopyEvent(User user, User beingCopied) {
		UserBindCopyEvent event = userBindCopyEvent(user, beingCopied);
		boolean cancelled = event.call().cancelled();
		if (!cancelled)
			Game.listeners(UserBindCopyListener.class).forEach(l -> l.onUserBindCopy(event));
		return cancelled;
	}

	public boolean postUserCooldownStartEvent(User user, Cooldown cooldown) {
		return postUserCooldownEvent(userCooldownStartEvent(user, cooldown));
	}

	public boolean postUserCooldownEndEvent(User user, Cooldown cooldown) {
		return postUserCooldownEvent(userCooldownEndEvent(user, cooldown));
	}

	public boolean postUserCooldownEvent(UserCooldownEvent event) {
		boolean cancelled = event.call().cancelled();
		if (!cancelled)
			Game.listeners(UserCooldownListener.class).forEach(l -> l.onUserCooldown(event));
		return cancelled;
	}

	public boolean postUserElementChooseEvent(User user, Element element) {
		return postUserElementChangeEvent(userElementChooseEvent(user, element));
	}

	public boolean postUserElementAddEvent(User user, Element... elements) {
		return postUserElementAddEvent(user, Arrays.stream(elements).collect(Collectors.toSet()));
	}

	public boolean postUserElementAddEvent(User user, Set<Element> elements) {
		return postUserElementChangeEvent(userElementAddEvent(user, elements));
	}

	public boolean postUserElementRemoveEvent(User user, Element... elements) {
		return postUserElementRemoveEvent(user, Arrays.stream(elements).collect(Collectors.toSet()));
	}

	public boolean postUserElementRemoveEvent(User user, Set<Element> elements) {
		return postUserElementChangeEvent(userElementRemoveEvent(user, elements));
	}

	public boolean postUserElementChangeEvent(UserElementChangeEvent event) {
		boolean cancelled = event.call().cancelled();
		if (!cancelled)
			Game.listeners(UserElementChangeListener.class).forEach(l -> l.onUserElementChange(event));
		return cancelled;
	}

	public boolean postUserPermaremoveEvent(User user) {
		UserPermaremoveEvent event = userPermaremoveEvent(user);
		boolean cancelled = event.call().cancelled();
		if (!cancelled)
			Game.listeners(UserPermaremoveListener.class).forEach(l -> l.onUserPermaremove(event));
		return cancelled;
	}

	public boolean postUserPresetCreateEvent(User user, Preset preset) {
		return postUserPresetEvent(userPresetCreateEvent(user, preset));
	}

	public boolean postUserPresetDeleteEvent(User user, Preset preset) {
		return postUserPresetEvent(userPresetDeleteEvent(user, preset));
	}

	public boolean postUserPresetBindEvent(User user, Preset preset) {
		return postUserPresetEvent(userPresetBindEvent(user, preset));
	}

	public boolean postUserPresetEvent(UserPresetEvent event) {
		boolean cancelled = event.call().cancelled();
		if (!cancelled)
			Game.listeners(UserPresetListener.class).forEach(l -> l.onUserPreset(event));
		return cancelled;
	}

	public void postWorldSunriseEvent(World world) {
		WorldSunriseEvent event = worldSunriseEvent(world);
		event.call();
		Game.listeners(WorldSunriseListener.class).forEach(l -> l.onWorldSunrise(event));
	}

	public void postWorldSunsetEvent(World world) {
		WorldSunsetEvent event = worldSunsetEvent(world);
		event.call();
		Game.listeners(WorldSunsetListener.class).forEach(l -> l.onWorldSunset(event));
	}

	protected abstract AbilityConfigLoadEvent abilityConfigLoadEvent(User user, AbilityInstance ability);
	protected abstract AbilityStartEvent abilityStartEvent(User user, AbilityInstance ability);
	protected abstract AbilityUpdateEvent abilityUpdateEvent(User user, AbilityInstance ability);
	protected abstract AbilityEndEvent abilityEndEvent(User user, AbilityInstance ability);
	protected abstract AbilityAffectEntityEvent abilityAffectEntityEvent(Effects effects, Entity entity);
	protected abstract AbilityKindleEvent abilityKindleEvent(User user, AbilityInstance ability);
	protected abstract UserBindChangeEvent userBindEvent(User user, Ability ability, int slot);
	protected abstract UserBindChangeEvent userUnbindEvent(User user, Ability ability, int slot);
	protected abstract UserBindCopyEvent userBindCopyEvent(User user, User beingCopied);
	protected abstract UserCooldownEvent userCooldownStartEvent(User user, Cooldown cooldown);
	protected abstract UserCooldownEvent userCooldownEndEvent(User user, Cooldown cooldown);
	protected abstract UserElementChangeEvent userElementChooseEvent(User user, Element element);
	protected abstract UserElementChangeEvent userElementAddEvent(User user, Set<Element> elements);
	protected abstract UserElementChangeEvent userElementRemoveEvent(User user, Set<Element> elements);
	protected abstract UserPermaremoveEvent userPermaremoveEvent(User user);
	protected abstract UserPresetEvent userPresetCreateEvent(User user, Preset preset);
	protected abstract UserPresetEvent userPresetDeleteEvent(User user, Preset preset);
	protected abstract UserPresetEvent userPresetBindEvent(User user, Preset preset);
	protected abstract WorldSunriseEvent worldSunriseEvent(World world);
	protected abstract WorldSunsetEvent worldSunsetEvent(World world);
}
