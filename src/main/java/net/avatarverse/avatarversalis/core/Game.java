package net.avatarverse.avatarversalis.core;

import net.avatarverse.avatarversalis.core.ability.AbilityManager;
import net.avatarverse.avatarversalis.core.ability.combo.ComboManager;
import net.avatarverse.avatarversalis.core.board.BendingBoardManager;
import net.avatarverse.avatarversalis.core.temporary.CooldownManager;
import net.avatarverse.avatarversalis.util.Scheduler;

public class Game {

	private final AbilityManager abilityManager;
	private final ComboManager comboManager;
	private final CooldownManager cooldownManager;
	private final BendingBoardManager boardManager;

	public Game() {
		abilityManager = new AbilityManager();
		comboManager = new ComboManager();
		cooldownManager = new CooldownManager();
		boardManager = new BendingBoardManager();

		Scheduler.repeat(this::update, 1);
	}

	private void update() {

	}

}
