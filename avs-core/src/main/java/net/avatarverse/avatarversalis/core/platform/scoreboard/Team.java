package net.avatarverse.avatarversalis.core.platform.scoreboard;

import java.util.Set;

public interface Team {
	Team prefix(String prefix);
	Team suffix(String suffix);
	Team addEntry(String entry);
	Set<String> entries();
	void unregister();
}
