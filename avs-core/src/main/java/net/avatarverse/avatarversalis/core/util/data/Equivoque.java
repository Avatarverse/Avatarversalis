package net.avatarverse.avatarversalis.core.util.data;

import java.util.function.Function;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.Getter;

@DefaultAnnotation(NonNull.class)
public class Equivoque<P, Q> {

	@Getter private final String string;
	private final Pair<String, Function<String, P>> p;
	private final Pair<String, Function<String, Q>> q;

	public Equivoque(String pPrefix, Function<String, P> pFunction, String qPrefix, Function<String, Q> qFunction, String string) {
		this.string = string;
		p = Pair.of(pPrefix, pFunction);
		q = Pair.of(qPrefix, qFunction);
	}

	public @Nullable P p() {
		String toMap = string;
		if (p.key().contains("="))
			toMap = string.substring(p.key().length());
		return p.value().apply(toMap);
	}

	public @Nullable Q q() {
		String toMap = string;
		if (q.key().contains("="))
			toMap = string.substring(q.key().length());
		return q.value().apply(toMap);
	}

}
