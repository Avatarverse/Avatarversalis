package net.avatarverse.avatarversalis.event.user;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.event.Cancellable;

import net.avatarverse.avatarversalis.core.element.Element;
import net.avatarverse.avatarversalis.core.user.User;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
public class UserElementChangeEvent extends UserEvent implements Cancellable {

	@Getter private final Set<Element> elements;
	@Getter private final Result result;

	private boolean cancelled = false;

	public UserElementChangeEvent(User user, Result result, Element... elements) {
		this(user, result, Arrays.stream(elements).collect(Collectors.toSet()));
	}

	public UserElementChangeEvent(User user, Result result, Set<Element> elements) {
		super(user);
		this.elements = new HashSet<>(elements);
		this.result = result;
	}

	public UserElementChangeEvent call() {
		super.call();
		return this;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}

	public enum Result {
		CHOOSE, REMOVE, ADD, PERMAREMOVE
	}
}
