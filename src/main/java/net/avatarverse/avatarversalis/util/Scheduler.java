package net.avatarverse.avatarversalis.util;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import net.avatarverse.avatarversalis.Avatarversalis;

import edu.umd.cs.findbugs.annotations.ReturnValuesAreNonnullByDefault;

@ParametersAreNonnullByDefault
@ReturnValuesAreNonnullByDefault
public final class Scheduler {

	public static BukkitTask run(Runnable runnable, long delay) {
		return Bukkit.getScheduler().runTaskLater(Avatarversalis.plugin(), runnable, delay);
	}

	public static BukkitTask repeat(Runnable runnable, long interval) {
		return Bukkit.getScheduler().runTaskTimer(Avatarversalis.plugin(), runnable, 0, interval);
	}

}
