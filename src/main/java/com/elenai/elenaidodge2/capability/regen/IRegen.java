package com.elenai.elenaidodge2.capability.regen;

public interface IRegen {

	public void increase(int regen);
	public void decrease(int regen);
	public void set(int regen);

	public int getRegen();
}
