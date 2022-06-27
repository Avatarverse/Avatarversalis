package net.avatarverse.avatarversalis.core.platform.event;

public interface Cancellable {
	boolean cancelled();
	Cancellable cancelled(boolean cancel);
}
