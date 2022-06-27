package net.avatarverse.avatarversalis.bukkit.impl;

import java.util.Optional;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import net.avatarverse.avatarversalis.bukkit.event.world.WorldSunriseEvent;
import net.avatarverse.avatarversalis.bukkit.event.world.WorldSunsetEvent;
import net.avatarverse.avatarversalis.bukkit.platform.entity.Entity;
import net.avatarverse.avatarversalis.bukkit.platform.entity.Player;
import net.avatarverse.avatarversalis.core.game.Controller;
import net.avatarverse.avatarversalis.core.game.ability.Activation;
import net.avatarverse.avatarversalis.core.game.user.EntityUser;
import net.avatarverse.avatarversalis.core.game.user.User;

public class BukkitController extends Controller implements Listener {

	@EventHandler
	public void onLeftClick(PlayerInteractEvent event) {
		if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) return;
		Optional.ofNullable(EntityUser.of(new Player(event.getPlayer()))).ifPresent(u ->
				activate(u, Activation.ATTACK)
		);
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		Optional.ofNullable(EntityUser.of(new Player(event.getPlayer()))).ifPresent(u ->
				activate(u, Activation.INTERACT)
		);
	}

	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent event) {
		Optional.ofNullable(EntityUser.of(new Player(event.getPlayer()))).ifPresent(u ->
				activate(u, Activation.INTERACT_ENTITY)
		);
	}

	@EventHandler
	public void onInteractBlock(PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		Optional.ofNullable(EntityUser.of(new Player(event.getPlayer()))).ifPresent(u ->
				activate(u, Activation.INTERACT_BLOCK)
		);
	}

	@EventHandler
	public void onSneak(PlayerToggleSneakEvent event) {
		Optional.ofNullable(EntityUser.of(new Player(event.getPlayer()))).ifPresent(u ->
				activate(u, event.isSneaking() ? Activation.SNEAK : Activation.SNEAK_RELEASE)
		);
	}

	@EventHandler
	public void onFall(EntityDamageEvent event) {
		if (event.getCause() != DamageCause.FALL) return;
		Optional.ofNullable(EntityUser.of(new Entity(event.getEntity()))).ifPresent(u ->
				activate(u, Activation.FALL)
		);
	}

	@EventHandler
	public void onSunrise(WorldSunriseEvent event) {
		User.USERS.values().stream().filter(u -> event.world() == u.location().world())
				.forEach(u -> activate(u, Activation.SUNRISE));
	}

	@EventHandler
	public void onSunset(WorldSunsetEvent event) {
		User.USERS.values().stream().filter(u -> event.world() == u.location().world())
				.forEach(u -> activate(u, Activation.SUNSET));
	}

}
