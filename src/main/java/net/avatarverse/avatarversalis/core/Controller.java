package net.avatarverse.avatarversalis.core;

import java.lang.reflect.InvocationTargetException;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import net.avatarverse.avatarversalis.Avatarversalis;
import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.ability.Activation;
import net.avatarverse.avatarversalis.core.user.EntityUser;
import net.avatarverse.avatarversalis.core.user.User;
import net.avatarverse.avatarversalis.event.world.WorldSunriseEvent;
import net.avatarverse.avatarversalis.event.world.WorldSunsetEvent;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
public class Controller implements Listener {

	public void activate(User user, Activation activation) {
		// perform activation method on each active instance if applicable
		for (AbilityInstance instance : user.activeInstances())
			for (Entry<Class<? extends AbilityInstance>, Set<Activation>> control : instance.ability().controls().entrySet())
				if (control.getKey().equals(instance.getClass()) && control.getValue().contains(activation))
					activation.method().accept(instance);

		Ability ability = user.selectedAbility();
		if (ability == null) return;
		activate(user, ability, activation);
	}

	public void activate(User user, Ability ability, Activation activation) {
		if (!user.canBend(ability)) return;
		Avatarversalis.game().comboManager().addStep(user, ability, activation);
		if (ability.combo() && activation == Activation.COMBO)
			instantiate(user, ability.activations().get(Activation.COMBO));
		else if (ability.activations().containsKey(activation))
			instantiate(user, ability.activations().get(activation));
	}

	private void instantiate(User user, Class<? extends AbilityInstance> clazz) {
		try {
			clazz.getDeclaredConstructor(User.class).newInstance(user);
		} catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
			e.printStackTrace(); // TODO
		}
	}

	@EventHandler
	public void onLeftClick(PlayerInteractEvent event) {
		if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) return;
		Optional.ofNullable(EntityUser.of(event.getPlayer())).ifPresent(u ->
				activate(u, Activation.ATTACK)
		);
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		Optional.ofNullable(EntityUser.of(event.getPlayer())).ifPresent(u ->
				activate(u, Activation.INTERACT)
		);
	}

	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent event) {
		Optional.ofNullable(EntityUser.of(event.getPlayer())).ifPresent(u ->
				activate(u, Activation.INTERACT_ENTITY)
		);
	}

	@EventHandler
	public void onInteractBlock(PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		Optional.ofNullable(EntityUser.of(event.getPlayer())).ifPresent(u ->
				activate(u, Activation.INTERACT_BLOCK)
		);
	}

	@EventHandler
	public void onSneak(PlayerToggleSneakEvent event) {
		Optional.ofNullable(EntityUser.of(event.getPlayer())).ifPresent(u ->
				activate(u, event.isSneaking() ? Activation.SNEAK : Activation.SNEAK_RELEASE)
		);
	}

	@EventHandler
	public void onFall(EntityDamageEvent event) {
		if (event.getCause() != DamageCause.FALL) return;
		Optional.ofNullable(EntityUser.of(event.getEntity())).ifPresent(u ->
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
