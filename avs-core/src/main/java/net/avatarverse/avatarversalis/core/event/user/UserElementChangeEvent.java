package net.avatarverse.avatarversalis.core.event.user;

import java.util.Set;

import net.avatarverse.avatarversalis.core.game.element.Element;
import net.avatarverse.avatarversalis.core.platform.event.Cancellable;

public interface UserElementChangeEvent extends UserEvent, Cancellable {
	Set<Element> elements();
	Action action();
	UserElementChangeEvent call();

	enum Action {
		CHOOSE, ADD, REMOVE
	}
}
