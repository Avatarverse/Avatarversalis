package net.avatarverse.avatarversalis.core.element.fire;

import net.avatarverse.avatarversalis.Avatarversalis;

public final class DayFactor extends FireFactor {
	DayFactor() {
		super(DayFactor.class, Avatarversalis.configManager().config().node("properties", "fire", "day-factor"));


//		Optional.ofNullable(
//				parseExpression(root.node("damage").getString("x1.25"))
//		).ifPresent(p ->
//				this.damage = new AttributeModifier(Attribute.DAMAGE, p.key(), p.value())
//		);
//
//		Optional.ofNullable(
//				parseExpression(root.node("range").getString("x1.25"))
//		).ifPresent(p ->
//				this.range = new AttributeModifier(Attribute.RANGE, p.key(), p.value())
//		);
//
//		Optional.ofNullable(
//				parseExpression(root.node("cooldown").getString("x0.8"))
//		).ifPresent(p ->
//				this.cooldown = new AttributeModifier(Attribute.COOLDOWN, p.key(), p.value())
//		);
//
//		Optional.ofNullable(
//				parseExpression(root.node("duration").getString("x1.2"))
//		).ifPresent(p ->
//				this.duration = new AttributeModifier(Attribute.DURATION, p.key(), p.value())
//		);
//
//		Optional.ofNullable(
//				parseExpression(root.node("speed").getString("x1.25"))
//		).ifPresent(p ->
//				this.speed = new AttributeModifier(Attribute.SPEED, p.key(), p.value())
//		);
//
//		Optional.ofNullable(
//				parseExpression(root.node("charge-time").getString("x0.8"))
//		).ifPresent(p ->
//				this.chargeTime = new AttributeModifier(Attribute.CHARGE_TIME, p.key(), p.value())
//		);
//
//		Optional.ofNullable(
//				parseExpression(root.node("radius").getString("x1.25"))
//		).ifPresent(p ->
//				this.radius = new AttributeModifier(Attribute.RADIUS, p.key(), p.value())
//		);
//
//		Optional.ofNullable(
//				parseExpression(root.node("height").getString("x1.5"))
//		).ifPresent(p ->
//				this.height = new AttributeModifier(Attribute.HEIGHT, p.key(), p.value())
//		);
//
//		Optional.ofNullable(
//				parseExpression(root.node("amount").getString("+1"))
//		).ifPresent(p ->
//				this.amount = new AttributeModifier(Attribute.AMOUNT, p.key(), p.value())
//		);
//
//		Optional.ofNullable(
//				parseExpression(root.node("fire-ticks").getString("x1.5"))
//		).ifPresent(p ->
//				this.fireTicks = new AttributeModifier(Attribute.FIRE_TICKS, p.key(), p.value())
//		);
//
//		for (Field field : getClass().getDeclaredFields()) {
//			Object object = null;
//			try {
//				object = field.get(this);
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			}
//			if (object != null) {
//				modifiers.add((AttributeModifier) object);
//			}
//		}
	}

}
