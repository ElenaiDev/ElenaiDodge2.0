package com.elenai.elenaidodge.capability.invincibility;

public class Invincibility implements IInvincibility {

	private int invincibility = 0;

	@Override
	public void increase(int invincibility) {
		this.invincibility += invincibility;
	}

	@Override
	public void decrease(int invincibility) {
		this.invincibility -= invincibility;
	}

	@Override
	public void set(int invincibility) {
		this.invincibility = invincibility;
	}

	@Override
	public int getInvincibility() {
		return this.invincibility;
	}

}
