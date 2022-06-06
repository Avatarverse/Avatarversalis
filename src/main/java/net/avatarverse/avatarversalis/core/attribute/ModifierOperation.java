package net.avatarverse.avatarversalis.core.attribute;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ModifierOperation {

	ADD((n, m) -> {
		if (n instanceof Double) return n.doubleValue() + m.doubleValue();
		if (n instanceof Float) return n.floatValue() + m.floatValue();
		if (n instanceof Long) return n.longValue() + m.longValue();
		if (n instanceof Integer) return n.intValue() + m.intValue();
		return n;
	}), MULTIPLY((n, m) -> {
		if (n instanceof Double) return n.doubleValue() * m.doubleValue();
		if (n instanceof Float) return n.floatValue() * m.floatValue();
		if (n instanceof Long) return n.longValue() * m.longValue();
		if (n instanceof Integer) return n.intValue() * m.intValue();
		return n;
	});

	private final Operation operation;

	public Number modify(Number number, Number modifier) {
		return operation.performOperation(number, modifier);
	}

	@FunctionalInterface
	private interface Operation {
		Number performOperation(Number number, Number modifier);
	}
}
