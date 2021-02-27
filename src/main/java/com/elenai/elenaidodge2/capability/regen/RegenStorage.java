package com.elenai.elenaidodge2.capability.regen;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class RegenStorage implements IStorage<IRegen> {

	@Override
	public INBT writeNBT(Capability<IRegen> capability, IRegen instance, Direction side) {
		return IntNBT.valueOf(instance.getRegen());
	}

	@Override
	public void readNBT(Capability<IRegen> capability, IRegen instance, Direction side, INBT nbt) {
		instance.set(((IntNBT) nbt).getInt());
	}

}
