package net.avatarverse.avatarversalis.util;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class Pair<K, V> {

	private final K key;
	private final V value;

	public static <K, V> Pair<K, V> of(K key, V value) {
		return new Pair<>(key, value);
	}
}
