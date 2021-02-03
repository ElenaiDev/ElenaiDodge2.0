package com.elenai.elenaidodge.capability.absorption;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class AbsorptionStorage implements IStorage<IAbsorption> {

	@Override
	public INBT writeNBT(Capability<IAbsorption> capability, IAbsorption instance, Direction side) {
		return IntNBT.valueOf(instance.getAbsorption());
	}

	@Override
	public void readNBT(Capability<IAbsorption> capability, IAbsorption instance, Direction side, INBT nbt) {
		instance.set(((IntNBT) nbt).getInt());
	}

}
