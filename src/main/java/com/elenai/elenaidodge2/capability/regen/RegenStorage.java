package com.elenai.elenaidodge2.capability.regen;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class RegenStorage implements IStorage<IRegen> {

	@Override
	public NBTBase writeNBT(Capability<IRegen> capability, IRegen instance, EnumFacing side) {
		return new NBTTagInt(instance.getRegen());
	}

	@Override
	public void readNBT(Capability<IRegen> capability, IRegen instance, EnumFacing side, NBTBase nbt) {
		instance.set(((NBTPrimitive) nbt).getInt());
	}

}
