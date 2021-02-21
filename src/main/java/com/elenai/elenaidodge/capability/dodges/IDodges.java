package com.elenai.elenaidodge.capability.dodges;

public interface IDodges {

	public void increase(int dodges);
	public void decrease(int dodges);
	public void set(int dodges);

	public int getDodges();
}
