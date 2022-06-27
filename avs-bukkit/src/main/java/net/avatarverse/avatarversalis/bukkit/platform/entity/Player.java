package net.avatarverse.avatarversalis.bukkit.platform.entity;

import net.avatarverse.avatarversalis.bukkit.util.Types;
import net.avatarverse.avatarversalis.core.platform.command.CommandSender;
import net.avatarverse.avatarversalis.core.platform.GameMode;
import net.avatarverse.avatarversalis.core.platform.inventory.MainHand;
import net.avatarverse.avatarversalis.core.platform.scoreboard.Scoreboard;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
@Getter
public class Player extends LivingEntity implements net.avatarverse.avatarversalis.core.platform.entity.Player, CommandSender {

	private final org.bukkit.entity.Player bukkit;

	public Player(org.bukkit.entity.Player bukkit) {
		super(bukkit);
		this.bukkit = bukkit;
	}

	@Override
	public void sendMessage(String... message) {
		bukkit.sendMessage(message);
	}

	@Override
	public boolean hasPermission(String permission) {
		return bukkit.hasPermission(permission);
	}

	@Override
	public boolean online() {
		return bukkit.isOnline();
	}

	@Override
	public boolean sneaking() {
		return bukkit.isSneaking();
	}

	@Override
	public boolean flying() {
		return bukkit.isFlying();
	}

	@Override
	public GameMode gameMode() {
		return Types.convert(bukkit.getGameMode());
	}

	@Override
	public int heldItemSlot() {
		return bukkit.getInventory().getHeldItemSlot();
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.entity.Player heldItemSlot(int slot) {
		bukkit.getInventory().setHeldItemSlot(slot);
		return this;
	}

	@Override
	public MainHand mainHand() {
		return Types.convert(bukkit.getMainHand());
	}

	@Override
	public void scoreboard(Scoreboard scoreboard) {
		bukkit.setScoreboard(((net.avatarverse.avatarversalis.bukkit.platform.scoreboard.Scoreboard) scoreboard).bukkit());
	}
}
