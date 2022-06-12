package net.avatarverse.avatarversalis.core;

import net.avatarverse.avatarversalis.core.ability.AbilityManager;
import net.avatarverse.avatarversalis.core.ability.ComboManager;
import net.avatarverse.avatarversalis.core.board.BendingBoardManager;
import net.avatarverse.avatarversalis.core.temporary.CooldownManager;
import net.avatarverse.avatarversalis.util.Listeners;
import net.avatarverse.avatarversalis.util.Scheduler;

import lombok.Data;

@Data
public class Game {

	private final AbilityManager abilityManager;
	private final ComboManager comboManager;
	private final CooldownManager cooldownManager;
	private final BendingBoardManager boardManager;
	private final Controller controller;

	public Game() {
		abilityManager = new AbilityManager();
		comboManager = new ComboManager();
		cooldownManager = new CooldownManager();
		boardManager = new BendingBoardManager();

		controller = new Controller();
		Listeners.register(controller);

		Scheduler.repeat(this::update, 1);
	}

	private void update() {

	}

}
