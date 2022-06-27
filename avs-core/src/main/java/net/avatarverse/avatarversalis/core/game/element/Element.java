package net.avatarverse.avatarversalis.core.game.element;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableSet;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@DefaultAnnotation(NonNull.class)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Element {

	private static final Map<String, Element> ELEMENTS = new HashMap<>();

	@Getter private final ElementDisplay display;
	@Getter private ImmutableSet<Element> parents, children;

	public static Element register(ElementDisplay display, Element... parents) {
		String name = display.noun();
		Element element = new Element(display);

		element.parents = ImmutableSet.copyOf(parents);
		element.children = ImmutableSet.of();
		for (Element parent : parents)
			parent.children = new ImmutableSet.Builder<Element>().addAll(parent.children).add(element).build();

		ELEMENTS.put(name, element);
		return element;
	}

	public String name() {
		return display.noun();
	}

	public static @Nullable Element byName(String name) {
		return ELEMENTS.get(name);
	}

	public static @Nullable Element byNameIgnoreCase(String name) {
		return all().filter(e -> e.name().equalsIgnoreCase(name)).findAny().orElse(null);
	}

	public static Stream<String> names() {
		return ELEMENTS.keySet().stream();
	}

	public static Stream<Element> all() {
		return ELEMENTS.values().stream();
	}

	public boolean isOrInherits(Element other) {
		if (this == other) return true;
		if (parents.contains(other)) return true;
		Set<Element> visited = new HashSet<>();
		Deque<Element> queue = new ArrayDeque<>();
		parents.forEach(queue::offerLast);
		while (!queue.isEmpty()) {
			Element node = queue.poll();
			if (!visited.contains(node)) {
				if (node == other) return true;
				visited.add(node);
				for (Element parent : node.parents)
					if (!visited.contains(parent))
						queue.offerLast(parent);
			}
		}
		return false;
	}

}
