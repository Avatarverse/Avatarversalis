package net.avatarverse.avatarversalis.bukkit.platform.scoreboard;

import java.util.Set;
import java.util.stream.Collectors;

import net.avatarverse.avatarversalis.bukkit.util.Types;
import net.avatarverse.avatarversalis.core.platform.scoreboard.DisplaySlot;
import net.avatarverse.avatarversalis.core.platform.scoreboard.Objective;
import net.avatarverse.avatarversalis.core.platform.scoreboard.Team;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@DefaultAnnotation(NonNull.class)
@RequiredArgsConstructor
@Getter
public class Scoreboard implements net.avatarverse.avatarversalis.core.platform.scoreboard.Scoreboard {

	private final org.bukkit.scoreboard.Scoreboard bukkit;

	@Override
	public Objective registerNewObjective(String name, String criteria, String displayName) {
		return new net.avatarverse.avatarversalis.bukkit.platform.scoreboard.Objective(bukkit.registerNewObjective(name, criteria, displayName));
	}

	@Override
	public void clearSlot(DisplaySlot slot) {
		bukkit.clearSlot(Types.convert(slot));
	}

	@Override
	public void resetScores(String entry) {
		bukkit.resetScores(entry);
	}

	@Override
	public Team team(String teamName) {
		return new net.avatarverse.avatarversalis.bukkit.platform.scoreboard.Team(bukkit.getTeam(teamName));
	}

	@Override
	public Team registerNewTeam(String teamName) {
		return new net.avatarverse.avatarversalis.bukkit.platform.scoreboard.Team(bukkit.registerNewTeam(teamName));
	}

	@Override
	public Set<Team> teams() {
		return bukkit.getTeams().stream().map(bkt -> (Team) new net.avatarverse.avatarversalis.bukkit.platform.scoreboard.Team(bkt)).collect(Collectors.toSet());
	}
}
