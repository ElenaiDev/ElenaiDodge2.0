package com.elenai.elenaidodge2.capability.dodges;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class DodgesStorage implements IStorage<IDodges> {

	@Override
	public INBT writeNBT(Capability<IDodges> capability, IDodges instance, Direction side) {
		return IntNBT.valueOf(instance.getDodges());
	}

	@Override
	public void readNBT(Capability<IDodges> capability, IDodges instance, Direction side, INBT nbt) {
		instance.set(((IntNBT) nbt).getInt());
	}

}
