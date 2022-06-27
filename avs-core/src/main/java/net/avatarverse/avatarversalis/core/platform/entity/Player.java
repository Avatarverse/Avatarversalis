package net.avatarverse.avatarversalis.core.platform.entity;

import net.avatarverse.avatarversalis.core.platform.command.CommandSender;
import net.avatarverse.avatarversalis.core.platform.GameMode;
import net.avatarverse.avatarversalis.core.platform.inventory.MainHand;
import net.avatarverse.avatarversalis.core.platform.scoreboard.Scoreboard;

public interface Player extends LivingEntity, CommandSender {
	boolean online();
	boolean sneaking();
	boolean flying();
	boolean onGround();
	GameMode gameMode();
	int heldItemSlot();
	Player heldItemSlot(int slot);
	MainHand mainHand();
	void scoreboard(Scoreboard scoreboard);
}
