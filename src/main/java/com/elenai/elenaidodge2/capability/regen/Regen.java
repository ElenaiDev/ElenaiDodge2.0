package com.elenai.elenaidodge2.capability.regen;

public class Regen implements IRegen {

	private int regen = 0;

	@Override
	public void increase(int regen) {
		this.regen += regen;
	}

	@Override
	public void decrease(int regen) {
		this.regen -= regen;
	}

	@Override
	public void set(int regen) {
		this.regen = regen;
	}

	@Override
	public int getRegen() {
		return this.regen;
	}

}
