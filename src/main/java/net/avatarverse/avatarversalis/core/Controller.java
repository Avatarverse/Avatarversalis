package net.avatarverse.avatarversalis.core;

import java.lang.reflect.InvocationTargetException;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.ability.Activation;
import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.event.WorldSunriseEvent;
import net.avatarverse.avatarversalis.event.WorldSunsetEvent;

public class Controller implements Listener {

	public void activate(User user, Activation activation) {
		Ability ability = user.selectedAbility();
		if (ability == null || !user.canBend(ability))
			return;
		if (!ability.activations().containsKey(activation)) {
			Set<Class<? extends AbilityInstance>> relevantClasses = ability.controls().entrySet().stream()
					.filter(e -> e.getValue() == activation)
					.map(Entry::getKey)
					.collect(Collectors.toSet());
			user.activeAbilities().stream()
					.filter(ai -> relevantClasses.contains(ai.getClass()))
					.forEach(activation.method());
			return;
		}
		try {
			ability.activations().get(activation).getDeclaredConstructor(User.class).newInstance(user);
		} catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@EventHandler
	public void onLeftClick(PlayerInteractEvent event) {
		if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) return;
		Optional.ofNullable(User.byEntity(event.getPlayer())).ifPresent(u ->
				activate(u, Activation.ATTACK)
		);
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		Optional.ofNullable(User.byEntity(event.getPlayer())).ifPresent(u ->
				activate(u, Activation.INTERACT)
		);
	}

	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent event) {
		Optional.ofNullable(User.byEntity(event.getPlayer())).ifPresent(u ->
				activate(u, Activation.INTERACT_ENTITY)
		);
	}

	@EventHandler
	public void onInteractBlock(PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		Optional.ofNullable(User.byEntity(event.getPlayer())).ifPresent(u ->
				activate(u, Activation.INTERACT_BLOCK)
		);
	}

	@EventHandler
	public void onSneak(PlayerToggleSneakEvent event) {
		Optional.ofNullable(User.byEntity(event.getPlayer())).ifPresent(u ->
				activate(u, event.isSneaking() ? Activation.SNEAK : Activation.SNEAK_RELEASE)
		);
	}

	@EventHandler
	public void onFall(EntityDamageEvent event) {
		if (event.getCause() != DamageCause.FALL) return;
		Optional.ofNullable(User.byEntity(event.getEntity())).ifPresent(u ->
				activate(u, Activation.FALL)
		);
	}

	@EventHandler
	public void onSunrise(WorldSunriseEvent event) {
		User.USERS.values().stream().filter(u -> event.world() == u.location().getWorld())
				.forEach(u -> activate(u, Activation.SUNRISE));
	}

	@EventHandler
	public void onSunset(WorldSunsetEvent event) {
		User.USERS.values().stream().filter(u -> event.world() == u.location().getWorld())
				.forEach(u -> activate(u, Activation.SUNSET));
	}

}
