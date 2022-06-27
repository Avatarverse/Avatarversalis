package net.avatarverse.avatarversalis.bukkit.platform;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@DefaultAnnotation(NonNull.class)
@RequiredArgsConstructor
@Getter
public class CommandSender implements net.avatarverse.avatarversalis.core.platform.command.CommandSender {

	private final org.bukkit.command.CommandSender bukkit;

	@Override
	public void sendMessage(String... message) {
		bukkit.sendMessage(message);
	}

	@Override
	public String name() {
		return bukkit.getName();
	}

	@Override
	public boolean hasPermission(String permission) {
		return bukkit().hasPermission(permission);
	}
}
