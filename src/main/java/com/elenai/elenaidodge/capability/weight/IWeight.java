package com.elenai.elenaidodge.capability.weight;

public interface IWeight {

	public void increase(int weight);
	public void decrease(int weight);
	public void set(int weight);

	public int getWeight();
}
