package net.avatarverse.avatarversalis.bukkit.impl;

import java.util.Set;

import net.avatarverse.avatarversalis.bukkit.event.ability.AbilityAffectEntityEvent;
import net.avatarverse.avatarversalis.bukkit.event.ability.AbilityConfigLoadEvent;
import net.avatarverse.avatarversalis.bukkit.event.ability.AbilityEndEvent;
import net.avatarverse.avatarversalis.bukkit.event.ability.AbilityKindleEvent;
import net.avatarverse.avatarversalis.bukkit.event.ability.AbilityStartEvent;
import net.avatarverse.avatarversalis.bukkit.event.ability.AbilityUpdateEvent;
import net.avatarverse.avatarversalis.bukkit.event.user.UserBindChangeEvent;
import net.avatarverse.avatarversalis.bukkit.event.user.UserBindCopyEvent;
import net.avatarverse.avatarversalis.bukkit.event.user.UserCooldownEvent;
import net.avatarverse.avatarversalis.bukkit.event.user.UserElementChangeEvent;
import net.avatarverse.avatarversalis.bukkit.event.user.UserPermaremoveEvent;
import net.avatarverse.avatarversalis.bukkit.event.user.UserPresetEvent;
import net.avatarverse.avatarversalis.bukkit.event.world.WorldSunriseEvent;
import net.avatarverse.avatarversalis.bukkit.event.world.WorldSunsetEvent;
import net.avatarverse.avatarversalis.core.game.EventBus;
import net.avatarverse.avatarversalis.core.game.ability.Ability;
import net.avatarverse.avatarversalis.core.game.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.game.element.Element;
import net.avatarverse.avatarversalis.core.game.temporary.Cooldown;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.game.user.preset.Preset;
import net.avatarverse.avatarversalis.core.platform.World;
import net.avatarverse.avatarversalis.core.platform.entity.Entity;
import net.avatarverse.avatarversalis.core.util.Effects;

public class BukkitEventBus extends EventBus {

	@Override
	public AbilityConfigLoadEvent abilityConfigLoadEvent(User user, AbilityInstance ability) {
		return new AbilityConfigLoadEvent(user, ability);
	}

	@Override
	public AbilityStartEvent abilityStartEvent(User user, AbilityInstance ability) {
		return new AbilityStartEvent(user, ability);
	}

	@Override
	public AbilityUpdateEvent abilityUpdateEvent(User user, AbilityInstance ability) {
		return new AbilityUpdateEvent(user, ability);
	}

	@Override
	public AbilityEndEvent abilityEndEvent(User user, AbilityInstance ability) {
		return new AbilityEndEvent(user, ability);
	}

	@Override
	public AbilityAffectEntityEvent abilityAffectEntityEvent(Effects effects, Entity entity) {
		return new AbilityAffectEntityEvent(effects, entity);
	}

	@Override
	public AbilityKindleEvent abilityKindleEvent(User user, AbilityInstance ability) {
		return new AbilityKindleEvent(user, ability);
	}

	@Override
	public UserBindChangeEvent userBindEvent(User user, Ability ability, int slot) {
		return new UserBindChangeEvent(user, ability, slot, net.avatarverse.avatarversalis.core.event.user.UserBindChangeEvent.Result.BIND);
	}

	@Override
	public UserBindChangeEvent userUnbindEvent(User user, Ability ability, int slot) {
		return new UserBindChangeEvent(user, ability, slot, net.avatarverse.avatarversalis.core.event.user.UserBindChangeEvent.Result.UNBIND);
	}

	@Override
	public UserBindCopyEvent userBindCopyEvent(User user, User beingCopied) {
		return new UserBindCopyEvent(user, beingCopied);
	}

	@Override
	public UserCooldownEvent userCooldownStartEvent(User user, Cooldown cooldown) {
		return new UserCooldownEvent(user, cooldown, net.avatarverse.avatarversalis.core.event.user.UserCooldownEvent.Result.START);
	}

	@Override
	public UserCooldownEvent userCooldownEndEvent(User user, Cooldown cooldown) {
		return new UserCooldownEvent(user, cooldown, net.avatarverse.avatarversalis.core.event.user.UserCooldownEvent.Result.END);
	}

	@Override
	public UserElementChangeEvent userElementChooseEvent(User user, Element element) {
		return new UserElementChangeEvent(user, net.avatarverse.avatarversalis.core.event.user.UserElementChangeEvent.Action.CHOOSE, element);
	}

	public UserElementChangeEvent userElementAddEvent(User user, Element... elements) {
		return new UserElementChangeEvent(user, net.avatarverse.avatarversalis.core.event.user.UserElementChangeEvent.Action.ADD, elements);
	}

	@Override
	public UserElementChangeEvent userElementAddEvent(User user, Set<Element> elements) {
		return new UserElementChangeEvent(user, net.avatarverse.avatarversalis.core.event.user.UserElementChangeEvent.Action.ADD, elements);
	}

	public UserElementChangeEvent userElementRemoveEvent(User user, Element... elements) {
		return new UserElementChangeEvent(user, net.avatarverse.avatarversalis.core.event.user.UserElementChangeEvent.Action.ADD, elements);
	}

	@Override
	public UserElementChangeEvent userElementRemoveEvent(User user, Set<Element> elements) {
		return new UserElementChangeEvent(user, net.avatarverse.avatarversalis.core.event.user.UserElementChangeEvent.Action.REMOVE, elements);
	}

	@Override
	public UserPermaremoveEvent userPermaremoveEvent(User user) {
		return new UserPermaremoveEvent(user);
	}

	@Override
	public UserPresetEvent userPresetCreateEvent(User user, Preset preset) {
		return new UserPresetEvent(user, preset, net.avatarverse.avatarversalis.core.event.user.UserPresetEvent.Action.CREATE);
	}

	@Override
	public UserPresetEvent userPresetDeleteEvent(User user, Preset preset) {
		return new UserPresetEvent(user, preset, net.avatarverse.avatarversalis.core.event.user.UserPresetEvent.Action.DELETE);
	}

	@Override
	public UserPresetEvent userPresetBindEvent(User user, Preset preset) {
		return new UserPresetEvent(user, preset, net.avatarverse.avatarversalis.core.event.user.UserPresetEvent.Action.BIND);
	}

	@Override
	public WorldSunriseEvent worldSunriseEvent(World world) {
		return new WorldSunriseEvent(world);
	}

	@Override
	public WorldSunsetEvent worldSunsetEvent(World world) {
		return new WorldSunsetEvent(world);
	}
}
