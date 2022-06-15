package net.avatarverse.avatarversalis.core;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.spongepowered.configurate.serialize.SerializationException;

import net.avatarverse.avatarversalis.Avatarversalis;
import net.avatarverse.avatarversalis.core.ability.AbilityManager;
import net.avatarverse.avatarversalis.core.ability.ComboManager;
import net.avatarverse.avatarversalis.core.board.BendingBoardManager;
import net.avatarverse.avatarversalis.core.temporary.CooldownManager;
import net.avatarverse.avatarversalis.util.Listeners;
import net.avatarverse.avatarversalis.util.Scheduler;

import lombok.Getter;

@Getter
public class Game {

	private final AbilityManager abilityManager;
	private final ComboManager comboManager;
	private final CooldownManager cooldownManager;
	private final BendingBoardManager boardManager;
	private final Controller controller;

	private final Set<World> disabledWorlds;

	public Game() {
		abilityManager = new AbilityManager();
		comboManager = new ComboManager();
		cooldownManager = new CooldownManager();
		boardManager = new BendingBoardManager();

		controller = new Controller();
		Listeners.register(controller);

		List<String> worlds = null;
		try {
			worlds = Avatarversalis.configManager().config().node("properties", "disabled-worlds").getList(String.class);
		} catch (SerializationException e) {
			// TODO Lang error
		}
		disabledWorlds = worlds != null ? worlds.stream().map(Bukkit::getWorld).filter(Objects::nonNull).collect(Collectors.toSet()) : Collections.emptySet();

		Scheduler.repeat(this::update);
	}

	private void update() {

	}

}
