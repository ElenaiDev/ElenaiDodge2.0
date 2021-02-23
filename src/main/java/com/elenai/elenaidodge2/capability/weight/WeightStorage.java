package com.elenai.elenaidodge2.capability.weight;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class WeightStorage implements IStorage<IWeight> {

	@Override
	public INBT writeNBT(Capability<IWeight> capability, IWeight instance, Direction side) {
		return IntNBT.valueOf(instance.getWeight());
	}

	@Override
	public void readNBT(Capability<IWeight> capability, IWeight instance, Direction side, INBT nbt) {
		instance.set(((IntNBT) nbt).getInt());
	}

}
