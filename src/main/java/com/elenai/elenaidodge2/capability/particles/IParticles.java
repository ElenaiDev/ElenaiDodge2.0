package com.elenai.elenaidodge2.capability.particles;

public interface IParticles {

	public void increase(int particles);
	public void decrease(int particles);
	public void set(int particles);

	public int getParticles();
}
