package net.avatarverse.avatarversalis.bukkit.platform.scheduler;

import org.bukkit.scheduler.BukkitTask;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@DefaultAnnotation(NonNull.class)
@RequiredArgsConstructor
@Getter
public class Task implements net.avatarverse.avatarversalis.core.platform.scheduler.Task {

	private final BukkitTask bukkit;

	@Override
	public int id() {
		return bukkit.getTaskId();
	}

	@Override
	public void cancel() {
		bukkit.cancel();
	}

	@Override
	public boolean cancelled() {
		return bukkit.isCancelled();
	}

	@Override
	public boolean sync() {
		return bukkit.isSync();
	}
}
