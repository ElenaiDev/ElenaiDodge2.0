package com.elenai.elenaidodge.capability.weight;

public interface IInvincibility {

	public void increase(int invincibility);
	public void decrease(int invincibility);
	public void set(int invincibility);

	public int getInvincibility();
}
