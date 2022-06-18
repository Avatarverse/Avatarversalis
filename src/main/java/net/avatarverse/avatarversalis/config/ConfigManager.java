package net.avatarverse.avatarversalis.config;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import lombok.Getter;

public final class ConfigManager {

	static final Set<AbilityConfig> ABILITY_CONFIGS = new HashSet<>();

	@Getter private CommentedConfigurationNode config;

	public ConfigManager() {
		YamlConfigurationLoader loader = YamlConfigurationLoader.builder()
				.path(Path.of("config.yml"))
				.indent(2).build();

		try {
			config = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
