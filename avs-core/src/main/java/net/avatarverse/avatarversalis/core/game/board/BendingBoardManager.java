package net.avatarverse.avatarversalis.core.game.board;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.spongepowered.configurate.ConfigurationNode;

import net.avatarverse.avatarversalis.core.game.Game;
import net.avatarverse.avatarversalis.core.game.ability.Ability;
import net.avatarverse.avatarversalis.core.game.user.AvatarPlayer;
import net.avatarverse.avatarversalis.core.platform.ChatColor;
import net.avatarverse.avatarversalis.core.platform.scoreboard.DisplaySlot;
import net.avatarverse.avatarversalis.core.platform.scoreboard.Objective;
import net.avatarverse.avatarversalis.core.platform.scoreboard.Scoreboard;
import net.avatarverse.avatarversalis.core.platform.scoreboard.Team;
import net.avatarverse.avatarversalis.core.util.text.Colors;
import net.avatarverse.avatarversalis.core.util.text.Lang;

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

		ConfigurationNode boardSettings = Game.config().node("properties", "scoreboard");

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
		return Objects.requireNonNull(Game.plugin().mainScoreboard());
	}

	private static Scoreboard newBoard() {
		return Objects.requireNonNull(Game.plugin().newScoreboard());
	}

	public final class BendingBoard {

		private static final String SEPARATOR = Game.config().node("properties", "scoreboard", "misc-separator").getString(" -------------- ");
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
			objective.displaySlot(DisplaySlot.SIDEBAR);
			show();
		}

		public void hide() {
			player.player().scoreboard(main());
		}

		public void show() {
			player.player().scoreboard(board);
			update();
		}

		public void destroy() {
			board.clearSlot(DisplaySlot.SIDEBAR);
			board.teams().forEach(Team::unregister);
			objective.unregister();
			hide();
			playerBoards.remove(player.uuid());
		}

		public void switchSlot(int newSlot) {
			newSlot++;
			team(currentSlot).prefix(" ");
			team(newSlot).prefix(">");
			currentSlot = newSlot;
		}

		public void update() {
			player.binds().keySet().forEach(this::update);
		}

		public void update(int slot) {
			slot++;
			Ability ability = player.binds().get(slot);
			String suffix = ability != null ? (player.isOnCooldown(ability) ? Lang.strikethrough(ability.displayName()) : ability.displayName()) : Lang.BOARD_EMPTY.asString(slot);
			team(slot).suffix(suffix);
		}

		public void updateMisc(String cooldownName, boolean add) {
			Team team = misc.computeIfAbsent(cooldownName, c -> createTeam(10 + misc.size()));
			if (add) {
				if (misc.containsKey(cooldownName)) return;
				if (misc.isEmpty())
					objective.score(SEPARATOR).score(-10);
				Ability ability = Ability.byName(cooldownName);
				ChatColor color = ability != null ? ability.element().display().color() : trackedCooldowns.getOrDefault(cooldownName, ChatColor.WHITE);
				team.prefix("  " + color + Lang.strikethrough(cooldownName));
			} else if (misc.containsKey(cooldownName)) {
				team.entries().forEach(board::resetScores);
				team.unregister();
				misc.remove(cooldownName);
				if (misc.isEmpty())
					board.resetScores(SEPARATOR);
			}
		}

		private Team team(int slot) {
			Team team = board.team("slot" + slot);
			return team != null ? team : createTeam(slot);
		}

		private Team createTeam(int slot) {
			Team team = board.registerNewTeam("slot" + slot);
			String entry = UNIQUE_IDENTIFIERS.getOrDefault(slot, generateUniqueIdentifier(slot));
			team.addEntry(entry);
			objective.score(entry).score(-slot);
			return team;
		}

		private static String generateUniqueIdentifier(int slot) {
			String identifier = ChatColor.values()[slot % 10] + "" + ChatColor.values()[slot % 16];
			UNIQUE_IDENTIFIERS.put(slot, identifier);
			return identifier;
		}
	}

}
