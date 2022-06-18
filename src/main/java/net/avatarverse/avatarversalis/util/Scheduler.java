package net.avatarverse.avatarversalis.util;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import net.avatarverse.avatarversalis.Avatarversalis;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * A utility class for quickly running and scheduling synchronous and asynchronous {@link BukkitTask}s.
 */
@DefaultAnnotation(NonNull.class)
public final class Scheduler {

	/**
	 * Run a task synchronously with a delay of 0 ticks.
	 * @param runnable task to run
	 * @return the generated {@link BukkitTask}
	 */
	public static BukkitTask run(Runnable runnable) {
		return run(runnable, 0);
	}

	/**
	 * Run a task synchronously with a custom delay.
	 * @param runnable task to run
	 * @param delay delay in ticks
	 * @return the generated {@link BukkitTask}
	 */
	public static BukkitTask run(Runnable runnable, long delay) {
		return Bukkit.getScheduler().runTaskLater(Avatarversalis.plugin(), runnable, delay);
	}

	/**
	 * Repeat a task synchronously with a delay of 0 ticks and an interval of 1.
	 * @param runnable task to run
	 * @return the generated {@link BukkitTask}
	 */
	public static BukkitTask repeat(Runnable runnable) {
		return repeat(runnable, 0, 1);
	}

	/**
	 * Repeat a task synchronously with a delay of 0 ticks and a custom interval.
	 * @param runnable task to run
	 * @param interval repeat interval in ticks
	 * @return the generated {@link BukkitTask}
	 */
	public static BukkitTask repeat(Runnable runnable, long interval) {
		return repeat(runnable, interval, 1);
	}

	/**
	 * Repeat a task synchronously with a custom delay and interval.
	 * @param runnable task to run
	 * @param delay delay in ticks
	 * @param interval repeat interval in ticks
	 * @return the generated {@link BukkitTask}
	 */
	public static BukkitTask repeat(Runnable runnable, long delay, long interval) {
		return Bukkit.getScheduler().runTaskTimer(Avatarversalis.plugin(), runnable, delay, interval);
	}

	/**
	 * Run a task asynchronously with a delay of 0 ticks.
	 * @param runnable task to run
	 * @return the generated {@link BukkitTask}
	 */
	public static BukkitTask async(Runnable runnable) {
		return async(runnable, 0);
	}

	/**
	 * Run a task asynchronously with a custom delay.
	 * @param runnable task to run
	 * @param delay delay in ticks
	 * @return the generated {@link BukkitTask}
	 */
	public static BukkitTask async(Runnable runnable, long delay) {
		return Bukkit.getScheduler().runTaskLaterAsynchronously(Avatarversalis.plugin(), runnable, delay);
	}

	/**
	 * Repeat a task asynchronously with a delay of 0 ticks and interval of 1 tick.
	 * @param runnable task to run
	 * @return the generated {@link BukkitTask}
	 */
	public static BukkitTask repeatAsync(Runnable runnable) {
		return repeatAsync(runnable, 0, 1);
	}

	/**
	 * Repeat a task asynchronously with a delay of 0 ticks and a custom interval.
	 * @param runnable task to run
	 * @param interval repeat interval in ticks
	 * @return the generated {@link BukkitTask}
	 */
	public static BukkitTask repeatAsync(Runnable runnable, long interval) {
		return repeatAsync(runnable, 0, interval);
	}

	/**
	 * Repeat a task asynchronously with a custom delay and interval.
	 * @param runnable task to run
	 * @param delay delay in ticks
	 * @param interval repeat interval in ticks
	 * @return the generated {@link BukkitTask}
	 */
	public static BukkitTask repeatAsync(Runnable runnable, long delay, long interval) {
		return Bukkit.getScheduler().runTaskTimerAsynchronously(Avatarversalis.plugin(), runnable, delay, interval);
	}

}
