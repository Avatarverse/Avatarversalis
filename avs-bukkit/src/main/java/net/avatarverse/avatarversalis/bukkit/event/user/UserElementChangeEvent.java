package net.avatarverse.avatarversalis.bukkit.event.user;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import net.avatarverse.avatarversalis.core.game.element.Element;
import net.avatarverse.avatarversalis.core.game.user.User;
import net.avatarverse.avatarversalis.core.platform.event.Cancellable;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
public class UserElementChangeEvent extends UserEvent implements net.avatarverse.avatarversalis.core.event.user.UserElementChangeEvent {

	@Getter private final Set<Element> elements;
	@Getter private final Action action;

	private boolean cancelled = false;

	public UserElementChangeEvent(User user, Action action, Element... elements) {
		this(user, action, Arrays.stream(elements).collect(Collectors.toSet()));
	}

	public UserElementChangeEvent(User user, Action action, Set<Element> elements) {
		super(user);
		this.elements = elements;
		this.action = action;
	}

	public UserElementChangeEvent call() {
		super.call();
		return this;
	}

	@Override
	public boolean cancelled() {
		return cancelled;
	}

	@Override
	public Cancellable cancelled(boolean cancel) {
		this.cancelled = cancel;
		return this;
	}
}
