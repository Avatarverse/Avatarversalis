package net.avatarverse.avatarversalis.core.platform.scoreboard;

import java.util.Set;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
public interface Scoreboard {
	Objective registerNewObjective(String name, String criteria, String displayName);
	void clearSlot(DisplaySlot slot);
	void resetScores(String entry);
	Team team(String teamName);
	Team registerNewTeam(String teamName);
	Set<Team> teams();
}
