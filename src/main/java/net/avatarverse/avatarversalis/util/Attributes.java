package net.avatarverse.avatarversalis.util;

import javax.annotation.ParametersAreNonnullByDefault;

import org.jetbrains.annotations.Nullable;

import net.avatarverse.avatarversalis.core.attribute.ModifierOperation;

import edu.umd.cs.findbugs.annotations.ReturnValuesAreNonnullByDefault;

@ParametersAreNonnullByDefault
@ReturnValuesAreNonnullByDefault
public final class Attributes {

	public static @Nullable Pair<ModifierOperation, Double> parseExpression(String expression) {
		if (expression.length() < 2) return null;

		String number = expression.substring(1);
		ModifierOperation operation;
		double value;

		switch(expression.charAt(0)) {
		case '-':
			number = "-" + number;
		case '+':
			value = Double.parseDouble(number);
			operation = ModifierOperation.ADD;
			break;
		case 'x':
		case '*':
			value = Double.parseDouble(number);
			operation = ModifierOperation.MULTIPLY;
			break;
		case '/':
			value = 1 / Double.parseDouble(number);
			operation = ModifierOperation.MULTIPLY;
			break;
		default:
			return null;
		}

		if ((operation == ModifierOperation.ADD && value == 0) || (operation == ModifierOperation.MULTIPLY && value == 1))
			return null;

		return Pair.of(operation, value);
	}

}
