package net.avatarverse.avatarversalis.util;

import java.util.function.Consumer;
import java.util.function.Predicate;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@DefaultAnnotation(NonNull.class)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Wrapper<T> {

	@Getter private final @Nullable T value;

	public static <T> Wrapper<T> of(@Nullable T value) {
		return new Wrapper<>(value);
	}

	public boolean empty() {
		return value == null;
	}

	public boolean present() {
		return value != null;
	}

	public boolean ifPresent(Consumer<? super T> action) {
		if (present())
			action.accept(value);
		return present();
	}

	public boolean check(Predicate<T> predicate) {
		return present() && predicate.test(value);
	}

	public boolean equals(Object obj) {
		if (obj == this) return true;
		return obj instanceof Wrapper wrapper && ((empty() && wrapper.empty()) || (value != null && value.equals(wrapper.value)));
	}

}
