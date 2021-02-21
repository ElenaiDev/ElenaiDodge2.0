package com.elenai.elenaidodge.capability.weight;

public class Weight implements IWeight {

	private int weight = 0;

	@Override
	public void set(int weight) {
		this.weight = weight;
	}

	@Override
	public int getWeight() {
		return this.weight;
	}

}
