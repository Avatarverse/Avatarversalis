package net.avatarverse.avatarversalis.bukkit.platform.scoreboard;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@DefaultAnnotation(NonNull.class)
@RequiredArgsConstructor
@Getter
public class Score implements net.avatarverse.avatarversalis.core.platform.scoreboard.Score {

	private final org.bukkit.scoreboard.Score bukkit;

	@Override
	public net.avatarverse.avatarversalis.core.platform.scoreboard.Score score(int score) {
		bukkit.setScore(score);
		return this;
	}
}
