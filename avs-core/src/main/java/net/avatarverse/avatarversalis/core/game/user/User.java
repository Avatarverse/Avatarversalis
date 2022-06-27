package net.avatarverse.avatarversalis.core.game.user;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.avatarverse.avatarversalis.core.game.Game;
import net.avatarverse.avatarversalis.core.game.ability.Ability;
import net.avatarverse.avatarversalis.core.game.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.game.ability.AbilityManager;
import net.avatarverse.avatarversalis.core.game.attribute.AttributeModifier;
import net.avatarverse.avatarversalis.core.game.element.Element;
import net.avatarverse.avatarversalis.core.game.temporary.Cooldown;
import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.util.Vector;
import net.avatarverse.avatarversalis.core.platform.World;
import net.avatarverse.avatarversalis.core.platform.block.Block;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.Getter;
import lombok.Setter;

@DefaultAnnotation(NonNull.class)
public abstract class User {

	public static final Map<UUID, User> USERS = new HashMap<>();
	public static final Map<Block, BlockUser> BLOCK_USERS = new HashMap<>();

	@Getter protected final UUID uuid;
	@Getter @Setter protected boolean toggled;
	@Getter @Setter protected boolean invincible;
	@Getter protected boolean permaremoved;
	protected final Map<Element, Boolean> elements = new ConcurrentHashMap<>(); // <Element, Toggled?>
	protected final Map<Integer, Ability> binds = new ConcurrentHashMap<>();
	protected final Map<Element, Set<AttributeModifier>> elementModifiers = new ConcurrentHashMap<>();
	protected final Map<Ability, Set<AttributeModifier>> abilityModifiers = new ConcurrentHashMap<>();

	public User(UUID uuid) {
		this.uuid = uuid;
		for (int i = 0; i < 9; i++)
			binds.put(i, null);
		USERS.put(uuid, this);
	}

	public static @Nullable User byId(UUID uuid) {
		return USERS.get(uuid);
	}

	public static @Nullable User byName(String name) {
		return all().filter(u -> u.name().equalsIgnoreCase(name)).findAny().orElse(null);
	}

	public static Stream<User> all() {
		return USERS.values().stream();
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

	public @Nullable <T extends AbilityInstance> T activeInstance(Class<T> clazz) {
		return AbilityManager.INSTANCES_BY_USER.get(this).stream().filter(a -> a.getClass().equals(clazz)).map(clazz::cast).findAny().orElse(null);
	}

	public Map<Element, Boolean> elementsMap() {
		return new ConcurrentHashMap<>(elements);
	}

	public Set<Element> elements() {
		return new HashSet<>(elements.keySet());
	}

	public boolean hasElement(Element element) {
		return elements.containsKey(element);
	}

	public void addElement(Element... elements) {
		if (!Game.eventBus().postUserElementAddEvent(this, elements)) return;
		Arrays.stream(elements).forEach(e -> this.elements.put(e, true));
	}

	public void removeElement(Element... elements) {
		if (!Game.eventBus().postUserElementRemoveEvent(this, elements)) return;
		Arrays.stream(elements).forEach(this.elements::remove);
	}

	public void clearElements() {
		if (!Game.eventBus().postUserElementRemoveEvent(this, elements.keySet())) return;
		elements.clear();
	}

	public void toggle() {
		toggled = !toggled;
	}

	public void toggleElement(Element element) {
		toggleElement(element, !elements.get(element));
	}

	public void toggleElement(Element element, boolean toggled) {
		elements.replace(element, toggled);
	}

	public boolean isElementToggled(Element element) {
		return elements.getOrDefault(element, false);
	}

	public Map<Integer, Ability> binds() {
		return new ConcurrentHashMap<>(binds);
	}

	public void bind(Ability ability, int slot) {
		if (!Game.eventBus().postUserBindEvent(this, ability, slot)) return;
		binds.put(slot, ability);
	}

	public void clearBinds() {
		binds.keySet().forEach(this::unbind);
	}

	public void unbind(int slot) {
		Ability ability = binds.get(slot);
		if (ability == null || !Game.eventBus().postUserUnbindEvent(this, ability, slot)) return;
		binds.put(slot, null);
	}

	public boolean copyBinds(User other) {
		if (!Game.eventBus().postUserBindCopyEvent(this, other)) return false;
		other.binds.forEach((slot, ability) -> {
			if (canBend(ability))
				bind(ability, slot);
		});
		return true;
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

	public void addCooldown(Ability ability, long cooldown, @Nullable Runnable endTask) {
		addCooldown(ability.name(), cooldown, endTask);
	}

	public void addCooldown(String ability, long cooldown) {
		addCooldown(ability, cooldown, null);
	}

	public void addCooldown(String ability, long cooldown, @Nullable Runnable endTask) {
		Cooldown.of(this, ability, cooldown, endTask);
	}

	public boolean removeCooldown(Ability ability) {
		return removeCooldown(ability.name());
	}

	public boolean removeCooldown(String ability) {
		Cooldown cooldown = cooldown(ability);
		if (cooldown == null || !Game.eventBus().postUserCooldownEndEvent(this, cooldown))
			return false;
		return cooldown.remove();
	}

	public Map<Element, Set<AttributeModifier>> elementModifiers() {
		return new ConcurrentHashMap<>(elementModifiers);
	}

	public Set<AttributeModifier> modifiers(Element element) {
		return new HashSet<>(elementModifiers.getOrDefault(element, Collections.emptySet()));
	}

	public Map<Ability, Set<AttributeModifier>> abilityModifiers() {
		return new ConcurrentHashMap<>(abilityModifiers);
	}

	public Set<AttributeModifier> modifiers(Ability ability) {
		return new HashSet<>(abilityModifiers.getOrDefault(ability, Collections.emptySet()));
	}

	public void addModifier(Element element, AttributeModifier modifier) {
		elementModifiers.computeIfAbsent(element, set -> new HashSet<>()).add(modifier);
	}

	public void addModifier(Ability ability, AttributeModifier modifier) {
		abilityModifiers.computeIfAbsent(ability, set -> new HashSet<>()).add(modifier);
	}

	public void clearModifiers() {
		elementModifiers.forEach((e, set) -> set.clear());
		abilityModifiers.forEach((a, set) -> set.clear());
	}

	public void permaremove() {
		if (!Game.eventBus().postUserPermaremoveEvent(this)) return;
		permaremoved = true;
		clearElements();
	}

	public void unPermaremove() {
		permaremoved = false;
	}

	public abstract String name();
	public abstract boolean canBend(Ability ability);
	public abstract int currentSlot();
	public abstract void currentSlot(int slot);
	public abstract @Nullable Ability selectedAbility();
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
