package net.avatarverse.avatarversalis.core.platform.scoreboard;

public interface Objective {
	void displaySlot(DisplaySlot slot);
	void unregister();
	Score score(String entry);
}
