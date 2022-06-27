package net.avatarverse.avatarversalis.bukkit.platform.scoreboard;

import java.util.Set;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@DefaultAnnotation(NonNull.class)
@RequiredArgsConstructor
@Getter
public class Team implements net.avatarverse.avatarversalis.core.platform.scoreboard.Team {

	private final org.bukkit.scoreboard.Team bukkit;

	@Override
	public net.avatarverse.avatarversalis.core.platform.scoreboard.Team prefix(String prefix) {
		bukkit.setPrefix(prefix);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.scoreboard.Team suffix(String suffix) {
		bukkit.setSuffix(suffix);
		return this;
	}

	@Override
	public net.avatarverse.avatarversalis.core.platform.scoreboard.Team addEntry(String entry) {
		bukkit.addEntry(entry);
		return this;
	}

	@Override
	public Set<String> entries() {
		return bukkit.getEntries();
	}

	@Override
	public void unregister() {
		bukkit.unregister();
	}
}
