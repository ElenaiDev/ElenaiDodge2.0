package com.elenai.elenaidodge2.capability.absorptionbool;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class AbsorptionBoolStorage implements IStorage<IAbsorptionBool> {

	@Override
	public INBT writeNBT(Capability<IAbsorptionBool> capability, IAbsorptionBool instance, Direction side) {
		return IntNBT.valueOf(instance.hasAbsorption() ? 1 : 0);
	}

	@Override
	public void readNBT(Capability<IAbsorptionBool> capability, IAbsorptionBool instance, Direction side, INBT nbt) {
		int i = ((IntNBT) nbt).getInt(); 
		boolean b = i > 0;
		instance.set(b);
	}


}
