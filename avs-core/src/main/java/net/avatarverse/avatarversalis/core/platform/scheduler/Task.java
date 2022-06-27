package net.avatarverse.avatarversalis.core.platform.scheduler;

public interface Task {
	int id();
	void cancel();
	boolean cancelled();
	boolean sync();
}
