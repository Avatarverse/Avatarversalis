package net.avatarverse.avatarversalis.core.platform.command;

public interface CommandSender {
	void sendMessage(String... message);
	String name();
	boolean hasPermission(String permission);
}
