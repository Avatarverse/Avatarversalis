package net.avatarverse.avatarversalis.core.user;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.core.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.ability.AbilityManager;
import net.avatarverse.avatarversalis.core.element.Element;
import net.avatarverse.avatarversalis.core.temporary.Cooldown;

import lombok.Getter;
import lombok.Setter;

public abstract class User {

	public static final Map<UUID, User> USERS = new HashMap<>();
	public static final Map<Block, BlockUser> BLOCK_USERS = new HashMap<>();

	@Getter protected final UUID uuid;
	@Getter @Setter private boolean toggled;
	@Getter private final Map<Element, Boolean> elements; // <Element, Toggled?>

	public User(UUID uuid) {
		this.uuid = uuid;
		this.elements = new HashMap<>();
		USERS.put(uuid, this);
	}

	public static @Nullable User byId(UUID uuid) {
		return USERS.get(uuid);
	}

	public static @Nullable User byEntity(Entity entity) {
		return USERS.get(entity.getUniqueId());
	}

	public @Nullable <T extends User> T as(Class<T> clazz) {
		return clazz.isInstance(this) ? clazz.cast(this) : null;
	}

	public Set<AbilityInstance> activeInstances() {
		return new HashSet<>(AbilityManager.INSTANCES_BY_USER.get(this));
	}

	public <T extends AbilityInstance> Set<T> activeInstances(Class<T> clazz) {
		return AbilityManager.INSTANCES_BY_USER.get(this).stream().filter(a -> a.getClass().equals(clazz)).map(clazz::cast).collect(Collectors.toSet());
	}

	public <T extends AbilityInstance> T activeInstance(Class<T> clazz) {
		return AbilityManager.INSTANCES_BY_USER.get(this).stream().filter(a -> a.getClass().equals(clazz)).map(clazz::cast).findAny().orElse(null);
	}

	public boolean hasElement(Element element) {
		return elements.containsKey(element);
	}

	public void addElement(Element... elements) {
		Arrays.stream(elements).forEach(e -> this.elements.put(e, true));
	}

	public void removeElement(Element... elements) {
		Arrays.stream(elements).forEach(this.elements::remove);
	}

	public void toggleElement(Element element, boolean toggled) {
		elements.replace(element, toggled);
	}

	public boolean isOnCooldown(Ability ability) {
		return cooldown(ability) != null;
	}

	public @Nullable Cooldown cooldown(Ability ability) {
		return cooldown(ability.name());
	}

	public @Nullable Cooldown cooldown(String ability) {
		return Cooldown.find(this, ability);
	}

	public void addCooldown(Ability ability, long cooldown) {
		addCooldown(ability.name(), cooldown, null);
	}

	public void addCooldown(Ability ability, long cooldown, Runnable endTask) {
		addCooldown(ability.name(), cooldown, endTask);
	}

	public void addCooldown(String ability, long cooldown) {
		addCooldown(ability, cooldown, null);
	}

	public void addCooldown(String ability, long cooldown, Runnable endTask) {
		Cooldown.of(this, ability, cooldown, endTask);
	}

	public boolean removeCooldown(Ability ability) {
		return removeCooldown(ability.name());
	}

	public boolean removeCooldown(String ability) {
		Cooldown cooldown = cooldown(ability);
		return cooldown != null && cooldown.remove();
	}

	public abstract boolean canBend(Ability ability);
	public abstract int currentSlot();
	public abstract Ability selectedAbility();
	public abstract Location location();
	public abstract Location eyeLocation();
	public abstract Location handLocation();
	public abstract Block locBlock();
	public abstract Block headBlock();
	public abstract Vector direction();
	public abstract Vector velocity();
	public abstract float yaw();
	public abstract float pitch();
	public abstract World world();
	public abstract boolean dead();
	public abstract boolean valid();
	public abstract boolean spectator();
	public abstract boolean sneaking();
	public abstract boolean flying();
	public abstract boolean onGround();

}
