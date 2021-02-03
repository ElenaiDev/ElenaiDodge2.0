package com.elenai.elenaidodge.capability.particles;

public class Particles implements IParticles {

	private int particles = 0;

	@Override
	public void increase(int particles) {
		this.particles += particles;
	}

	@Override
	public void decrease(int particles) {
		this.particles -= particles;
	}

	@Override
	public void set(int particles) {
		this.particles = particles;
	}

	@Override
	public int getParticles() {
		return this.particles;
	}

}
