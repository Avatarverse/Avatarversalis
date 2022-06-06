package net.avatarverse.avatarversalis.core.board;

import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * Provides graphical functionality through Minecraft scoreboards for bending
 */

public class BendingBoard {

    public static class BoardSlot {

        private Scoreboard board;
        private Objective obj;
        private Team team;
        private int slot;

        public BoardSlot() {}

    }

}
