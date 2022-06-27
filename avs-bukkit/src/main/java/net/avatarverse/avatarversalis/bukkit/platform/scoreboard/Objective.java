package net.avatarverse.avatarversalis.bukkit.platform.scoreboard;

import net.avatarverse.avatarversalis.bukkit.util.Types;
import net.avatarverse.avatarversalis.core.platform.scoreboard.DisplaySlot;
import net.avatarverse.avatarversalis.core.platform.scoreboard.Score;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@DefaultAnnotation(NonNull.class)
@RequiredArgsConstructor
@Getter
public class Objective implements net.avatarverse.avatarversalis.core.platform.scoreboard.Objective {

	private final org.bukkit.scoreboard.Objective bukkit;

	@Override
	public void displaySlot(DisplaySlot slot) {
		bukkit.setDisplaySlot(Types.convert(slot));
	}

	@Override
	public void unregister() {
		bukkit.unregister();
	}

	@Override
	public Score score(String entry) {
		return new net.avatarverse.avatarversalis.bukkit.platform.scoreboard.Score(bukkit.getScore(entry));
	}
}
