package com.elenai.elenaidodge.capability.absorption;

public class Absorption implements IAbsorption {

	private int absorption = 0;

	@Override
	public void increase(int absorption) {
		this.absorption += absorption;
	}

	@Override
	public void decrease(int absorption) {
		this.absorption -= absorption;
	}

	@Override
	public void set(int absorption) {
		this.absorption = absorption;
	}

	@Override
	public int getAbsorption() {
		return this.absorption;
	}

}
