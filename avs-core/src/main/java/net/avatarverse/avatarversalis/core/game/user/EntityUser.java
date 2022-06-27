package net.avatarverse.avatarversalis.core.game.user;

import net.avatarverse.avatarversalis.core.game.ability.Ability;
import net.avatarverse.avatarversalis.core.platform.entity.Entity;
import net.avatarverse.avatarversalis.core.platform.Location;
import net.avatarverse.avatarversalis.core.platform.util.Vector;
import net.avatarverse.avatarversalis.core.platform.World;
import net.avatarverse.avatarversalis.core.platform.block.Block;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
public class EntityUser extends User {

	@Getter private final Entity entity;
	private int currentSlot;

	public EntityUser(Entity entity) {
		super(entity.uuid());
		this.entity = entity;
	}

	public static @Nullable EntityUser of(Entity entity) {
		User user = byId(entity.uuid());
		return user != null ? user.as(EntityUser.class) : null;
	}

	@Override
	public String name() {
		return entity.name();
	}

	@Override
	public boolean canBend(Ability ability) {
		return false;
	}

	@Override
	public int currentSlot() {
		return currentSlot;
	}

	@Override
	public void currentSlot(int slot) {
		this.currentSlot = slot;
	}

	@Override
	public @Nullable Ability selectedAbility() {
		return null;
	}

	@Override
	public Location location() {
		return entity.location();
	}

	@Override
	public Location eyeLocation() {
		return location();
	}

	@Override
	public Location handLocation() {
		return location();
	}

	@Override
	public Block locBlock() {
		return location().block();
	}

	@Override
	public Block headBlock() {
		return eyeLocation().block();
	}

	@Override
	public Vector direction() {
		return location().direction();
	}

	@Override
	public Vector velocity() {
		return entity.velocity();
	}

	@Override
	public float yaw() {
		return location().yaw();
	}

	@Override
	public float pitch() {
		return location().pitch();
	}

	@Override
	public World world() {
		return entity.world();
	}

	@Override
	public boolean dead() {
		return entity.dead();
	}

	@Override
	public boolean valid() {
		return entity.valid();
	}

	@Override
	public boolean spectator() {
		return false;
	}

	@Override
	public boolean sneaking() {
		return true;
	}

	@Override
	public boolean flying() {
		return false;
	}

	@Override
	public boolean onGround() {
		return entity.onGround();
	}
}
