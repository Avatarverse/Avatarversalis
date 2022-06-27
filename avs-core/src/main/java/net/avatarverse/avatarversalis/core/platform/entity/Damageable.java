package net.avatarverse.avatarversalis.core.platform.entity;

public interface Damageable {
	void damage(double amount);
	void damage(double amount, Entity source);
	double health();
	double maxHealth();
	void resetMaxHealth();
	Damageable health(double health);
	Damageable maxHealth(double maxHealth);
}
