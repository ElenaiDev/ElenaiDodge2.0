package com.elenai.elenaidodge2.capability.absorption;

public interface IAbsorption {

	public void increase(int absorption);
	public void decrease(int absorption);
	public void set(int absorption);

	public int getAbsorption();
}
