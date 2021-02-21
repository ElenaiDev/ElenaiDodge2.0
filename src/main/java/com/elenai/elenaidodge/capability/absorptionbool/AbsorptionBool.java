package com.elenai.elenaidodge.capability.absorptionbool;

public class AbsorptionBool implements IAbsorptionBool {

	private boolean absorption = false;

	@Override
	public void set(boolean absorption) {
		this.absorption = absorption;
	}

	@Override
	public boolean hasAbsorption() {
		return this.absorption;
	}



}
