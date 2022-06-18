package net.avatarverse.avatarversalis.core.board;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.spongepowered.configurate.ConfigurationNode;

import net.avatarverse.avatarversalis.Avatarversalis;
import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.core.user.AvatarPlayer;
import net.avatarverse.avatarversalis.util.text.Colors;
import net.avatarverse.avatarversalis.util.text.Lang;

import net.md_5.bungee.api.ChatColor;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

/**
 * Manager for every instance of {@link BendingBoard}
 */
@DefaultAnnotation(NonNull.class)
public final class BendingBoardManager {

	private final Map<UUID, BendingBoard> playerBoards;
	private final Set<UUID> disabledPlayers;
	private final Map<String, ChatColor> trackedCooldowns;
	@Getter private final boolean enabled;

	public BendingBoardManager() {
		playerBoards = new ConcurrentHashMap<>();
		disabledPlayers = Collections.synchronizedSet(new HashSet<>());
		trackedCooldowns = new ConcurrentHashMap<>();

		ConfigurationNode boardSettings = Avatarversalis.config().node("properties", "scoreboard");

		enabled = boardSettings.node("enabled").getBoolean(true);
		for (ConfigurationNode node : boardSettings.node("extra-cooldowns").childrenList()) {
			Object key = node.key();
			if (key == null) continue;

			String name = key.toString();
			String secondString = node.getString();
			if (secondString == null) continue;

			Ability ability = Ability.byName(secondString);
			ChatColor color = Colors.hexToChatColor(secondString);
			if (ability != null) color = ability.element().display().color();

			trackedCooldowns.put(name, color);
		}

		// TODO load disabled players from db
	}

	public Optional<BendingBoard> board(AvatarPlayer player) {
		if (!enabled || !player.valid())
			return Optional.empty();
		return Optional.of(playerBoards.computeIfAbsent(player.uuid(), u -> new BendingBoard(player)));
	}

	public boolean enabled(AvatarPlayer player) {
	    return !disabledPlayers.contains(player.uuid());
    }

	public void enable(AvatarPlayer player) {
		board(player).ifPresent(BendingBoard::show);
		disabledPlayers.remove(player.uuid());
	}

	public void disable(AvatarPlayer player) {
		board(player).ifPresent(BendingBoard::hide);
		disabledPlayers.add(player.uuid());
	}

	public void addCooldownToTrack(String cooldownName, ChatColor color) {
		trackedCooldowns.put(cooldownName, color);
	}

	private static Scoreboard main() {
		return Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
	}

	private static Scoreboard newBoard() {
		return Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
	}

	public final class BendingBoard {

		private static final String SEPARATOR = Avatarversalis.config().node("properties", "scoreboard", "misc-separator").getString(" -------------- ");
		private static final Map<Integer, String> UNIQUE_IDENTIFIERS = new ConcurrentHashMap<>();
		static {
			for (int slot = 0; slot < 16; slot++) {
				generateUniqueIdentifier(slot);
			}
		}

		private final Scoreboard board;
		private final Objective objective;
		private final Map<String, Team> misc = new ConcurrentHashMap<>(); // section of scoreboard for additional cooldowns, like combos
		private final AvatarPlayer player;
		private int currentSlot;

		private BendingBoard(AvatarPlayer player) {
			this.player = player;
			this.board = newBoard();
			this.objective = board.registerNewObjective("BendingBoard", "dummy", Lang.BOARD_TITLE.asString());
			this.currentSlot = player.currentSlot() + 1;
			objective.setDisplaySlot(DisplaySlot.SIDEBAR);
			show();
		}

		public void hide() {
			player.player().setScoreboard(main());
		}

		public void show() {
			player.player().setScoreboard(board);
			update();
		}

		public void destroy() {
			board.clearSlot(DisplaySlot.SIDEBAR);
			board.getTeams().forEach(Team::unregister);
			objective.unregister();
			hide();
			playerBoards.remove(player.uuid());
		}

		public void switchSlot(int newSlot) {
			newSlot++;
			team(currentSlot).setPrefix(" ");
			team(newSlot).setPrefix(">");
			currentSlot = newSlot;
		}

		public void update() {
			player.binds().keySet().forEach(this::update);
		}

		public void update(int slot) {
			slot++;
			Ability ability = player.binds().get(slot);
			String suffix = ability != null ? (player.isOnCooldown(ability) ? Lang.strikethrough(ability.displayName()) : ability.displayName()) : Lang.BOARD_EMPTY.asString(slot);
			team(slot).setSuffix(suffix);
		}

		public void updateMisc(String cooldownName, boolean add) {
			Team team = misc.computeIfAbsent(cooldownName, c -> createTeam(10 + misc.size()));
			if (add) {
				if (misc.containsKey(cooldownName)) return;
				if (misc.isEmpty())
					objective.getScore(SEPARATOR).setScore(-10);
				Ability ability = Ability.byName(cooldownName);
				ChatColor color = ability != null ? ability.element().display().color() : trackedCooldowns.getOrDefault(cooldownName, ChatColor.WHITE);
				team.setPrefix("  " + color + Lang.strikethrough(cooldownName));
			} else if (misc.containsKey(cooldownName)) {
				team.getEntries().forEach(board::resetScores);
				team.unregister();
				misc.remove(cooldownName);
				if (misc.isEmpty())
					board.resetScores(SEPARATOR);
			}
		}

		private Team team(int slot) {
			Team team = board.getTeam("slot" + slot);
			return team != null ? team : createTeam(slot);
		}

		private Team createTeam(int slot) {
			Team team = board.registerNewTeam("slot" + slot);
			String entry = UNIQUE_IDENTIFIERS.getOrDefault(slot, generateUniqueIdentifier(slot));
			team.addEntry(entry);
			objective.getScore(entry).setScore(-slot);
			return team;
		}

		private static String generateUniqueIdentifier(int slot) {
			String identifier = org.bukkit.ChatColor.values()[slot % 10] + "" + org.bukkit.ChatColor.values()[slot % 16];
			UNIQUE_IDENTIFIERS.put(slot, identifier);
			return identifier;
		}
	}

}
