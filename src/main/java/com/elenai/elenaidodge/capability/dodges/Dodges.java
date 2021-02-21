package com.elenai.elenaidodge.capability.dodges;

public class Dodges implements IDodges {

	private int dodges = 0;

	@Override
	public void increase(int dodges) {
		this.dodges += dodges;
	}

	@Override
	public void decrease(int dodges) {
		this.dodges -= dodges;
	}

	@Override
	public void set(int dodges) {
		this.dodges = dodges;
	}

	@Override
	public int getDodges() {
		return this.dodges;
	}

}
