package net.avatarverse.avatarversalis.core;

import java.util.UUID;
import java.util.logging.Logger;

import net.avatarverse.avatarversalis.core.config.ConfigManager;
import net.avatarverse.avatarversalis.core.game.Controller;
import net.avatarverse.avatarversalis.core.game.EventBus;
import net.avatarverse.avatarversalis.core.game.Game;
import net.avatarverse.avatarversalis.core.game.Scheduler;
import net.avatarverse.avatarversalis.core.platform.World;
import net.avatarverse.avatarversalis.core.platform.scoreboard.Scoreboard;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

@DefaultAnnotation(NonNull.class)
public interface Avatarversalis {

	Logger log();
	String version();
	ConfigManager configManager();
	Game game();
	Controller controller();
	EventBus eventBus();
	Scheduler scheduler();

	Scoreboard mainScoreboard();
	Scoreboard newScoreboard();
	@Nullable World world(UUID uid);
	@Nullable World world(String name);
	void reloadConfig();

}
