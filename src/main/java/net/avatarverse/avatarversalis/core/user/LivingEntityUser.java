package net.avatarverse.avatarversalis.core.user;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

import net.avatarverse.avatarversalis.core.ability.Ability;
import net.avatarverse.avatarversalis.core.element.Element;
import net.avatarverse.avatarversalis.core.temporary.Cooldown;

import lombok.Getter;

public class LivingEntityUser extends EntityUser {

	@Getter private final LivingEntity entity;
	/**
	 * < Element, Toggled >
	 */
	@Getter private final Map<Element, Boolean> elements;

	public LivingEntityUser(LivingEntity entity) {
		super(entity);
		this.entity = entity;
		this.elements = new HashMap<>();
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

	@Override
	public @Nullable Cooldown cooldown(Ability ability) {
		return null;
	}

	@Override
	public void addCooldown(Ability ability, Cooldown cooldown) {

	}

	@Override
	public void removeCooldown(Ability ability) {

	}

	@Override
	public boolean canBend(Ability ability) {
		return false;
	}

	public Location eyeLocation() {
		return entity.getEyeLocation();
	}

}
