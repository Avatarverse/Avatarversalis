package net.avatarverse.avatarversalis.core.game;

import net.avatarverse.avatarversalis.core.platform.scheduler.Task;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * A utility class for quickly running and scheduling synchronous and asynchronous tasks.
 */
@DefaultAnnotation(NonNull.class)
public interface Scheduler {

	/**
	 * Run a task synchronously with a delay of 0 ticks.
	 * @param runnable task to run
	 * @return the generated {@link Task}
	 */
	default Task run(Runnable runnable) {
		return run(runnable, 0);
	}

	/**
	 * Run a task synchronously with a custom delay.
	 * @param runnable task to run
	 * @param delay delay in ticks
	 * @return the generated {@link Task}
	 */
	Task run(Runnable runnable, long delay);

	/**
	 * Repeat a task synchronously with a delay of 0 ticks and an interval of 1.
	 * @param runnable task to run
	 * @return the generated {@link Task}
	 */
	default Task repeat(Runnable runnable) {
		return repeat(runnable, 0, 1);
	}

	/**
	 * Repeat a task synchronously with a delay of 0 ticks and a custom interval.
	 * @param runnable task to run
	 * @param interval repeat interval in ticks
	 * @return the generated {@link Task}
	 */
	default Task repeat(Runnable runnable, long interval) {
		return repeat(runnable, interval, 1);
	}

	/**
	 * Repeat a task synchronously with a custom delay and interval.
	 * @param runnable task to run
	 * @param delay delay in ticks
	 * @param interval repeat interval in ticks
	 * @return the generated {@link Task}
	 */
	Task repeat(Runnable runnable, long delay, long interval);

	/**
	 * Run a task asynchronously with a delay of 0 ticks.
	 * @param runnable task to run
	 * @return the generated {@link Task}
	 */
	default Task async(Runnable runnable) {
		return async(runnable, 0);
	}

	/**
	 * Run a task asynchronously with a custom delay.
	 * @param runnable task to run
	 * @param delay delay in ticks
	 * @return the generated {@link Task}
	 */
	Task async(Runnable runnable, long delay);

	/**
	 * Repeat a task asynchronously with a delay of 0 ticks and interval of 1 tick.
	 * @param runnable task to run
	 * @return the generated {@link Task}
	 */
	default Task repeatAsync(Runnable runnable) {
		return repeatAsync(runnable, 0, 1);
	}

	/**
	 * Repeat a task asynchronously with a delay of 0 ticks and a custom interval.
	 * @param runnable task to run
	 * @param interval repeat interval in ticks
	 * @return the generated {@link Task}
	 */
	default Task repeatAsync(Runnable runnable, long interval) {
		return repeatAsync(runnable, 0, interval);
	}

	/**
	 * Repeat a task asynchronously with a custom delay and interval.
	 * @param runnable task to run
	 * @param delay delay in ticks
	 * @param interval repeat interval in ticks
	 * @return the generated {@link Task}
	 */
	Task repeatAsync(Runnable runnable, long delay, long interval);

}
