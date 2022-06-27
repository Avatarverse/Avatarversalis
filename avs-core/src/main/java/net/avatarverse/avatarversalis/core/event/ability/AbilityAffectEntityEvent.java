package net.avatarverse.avatarversalis.core.event.ability;

import net.avatarverse.avatarversalis.core.platform.entity.Entity;
import net.avatarverse.avatarversalis.core.platform.potion.PotionEffect;
import net.avatarverse.avatarversalis.core.platform.util.Vector;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

@DefaultAnnotation(NonNull.class)
public interface AbilityAffectEntityEvent extends AbilityEvent {
	Entity entity();
	@Nullable Vector velocity();
	Double damage();
	Integer fireTicks();
	Integer freezeTicks();
	PotionEffect[] potionEffects();
}
