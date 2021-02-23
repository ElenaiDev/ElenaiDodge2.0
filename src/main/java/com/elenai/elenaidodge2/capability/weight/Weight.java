package com.elenai.elenaidodge2.capability.weight;

public class Weight implements IWeight {

	private int weight = 0;

	@Override
	public void increase(int weight) {
		this.weight += weight;
	}

	@Override
	public void decrease(int weight) {
		this.weight -= weight;
	}

	@Override
	public void set(int weight) {
		this.weight = weight;
	}

	@Override
	public int getWeight() {
		return this.weight;
	}

}
