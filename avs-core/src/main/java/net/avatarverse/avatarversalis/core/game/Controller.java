package net.avatarverse.avatarversalis.core.game;

import java.lang.reflect.InvocationTargetException;
import java.util.Map.Entry;
import java.util.Set;

import net.avatarverse.avatarversalis.core.game.ability.Ability;
import net.avatarverse.avatarversalis.core.game.ability.AbilityInstance;
import net.avatarverse.avatarversalis.core.game.ability.Activation;
import net.avatarverse.avatarversalis.core.game.user.User;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
public class Controller {

	public void activate(User user, Activation activation) {
		// perform activation method on each active instance if applicable
		for (AbilityInstance instance : user.activeInstances())
			for (Entry<Class<? extends AbilityInstance>, Set<Activation>> control : instance.ability().controls().entrySet())
				if (control.getKey().equals(instance.getClass()) && control.getValue().contains(activation))
					activation.method().accept(instance);

		Ability ability = user.selectedAbility();
		if (ability == null) return;
		activate(user, ability, activation);
	}

	public void activate(User user, Ability ability, Activation activation) {
		if (!user.canBend(ability)) return;
		Game.comboManager().addStep(user, ability, activation);
		if (ability.combo() && activation == Activation.COMBO)
			instantiate(user, ability.activations().get(Activation.COMBO));
		else if (ability.activations().containsKey(activation))
			instantiate(user, ability.activations().get(activation));
	}

	private void instantiate(User user, Class<? extends AbilityInstance> clazz) {
		try {
			clazz.getDeclaredConstructor(User.class).newInstance(user);
		} catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
			e.printStackTrace(); // TODO
		}
	}

}
