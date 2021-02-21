package com.elenai.elenaidodge.capability.particles;

public interface IParticles {

	public void increase(int particles);
	public void decrease(int particles);
	public void set(int invincibility);

	public int getParticles();
}
