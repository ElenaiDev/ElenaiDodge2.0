package com.elenai.elenaidodge.capability.joined;

public class Joined implements IJoined {

	private boolean joined = false;

	@Override
	public void join() {
		this.joined = true;
	}

	@Override
	public boolean hasJoined() {
		return this.joined;
	}

	@Override
	public void set(boolean bool) {
		this.joined = bool;
	}



}
