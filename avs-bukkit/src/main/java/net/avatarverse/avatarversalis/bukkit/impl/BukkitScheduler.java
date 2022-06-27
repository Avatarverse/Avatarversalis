package net.avatarverse.avatarversalis.bukkit.impl;

import org.bukkit.Bukkit;

import net.avatarverse.avatarversalis.bukkit.AVSBukkit;
import net.avatarverse.avatarversalis.core.game.Scheduler;
import net.avatarverse.avatarversalis.core.platform.scheduler.Task;

public class BukkitScheduler implements Scheduler {
	@Override
	public Task run(Runnable runnable, long delay) {
		return new net.avatarverse.avatarversalis.bukkit.platform.scheduler.Task(Bukkit.getScheduler().runTaskLater(AVSBukkit.plugin, runnable, delay));
	}

	@Override
	public Task repeat(Runnable runnable, long delay, long interval) {
		return new net.avatarverse.avatarversalis.bukkit.platform.scheduler.Task(Bukkit.getScheduler().runTaskTimer(AVSBukkit.plugin, runnable, delay, interval));
	}

	@Override
	public Task async(Runnable runnable, long delay) {
		return new net.avatarverse.avatarversalis.bukkit.platform.scheduler.Task(Bukkit.getScheduler().runTaskLaterAsynchronously(AVSBukkit.plugin, runnable, delay));
	}

	@Override
	public Task repeatAsync(Runnable runnable, long delay, long interval) {
		return new net.avatarverse.avatarversalis.bukkit.platform.scheduler.Task(Bukkit.getScheduler().runTaskTimerAsynchronously(AVSBukkit.plugin, runnable, delay, interval));
	}
}
