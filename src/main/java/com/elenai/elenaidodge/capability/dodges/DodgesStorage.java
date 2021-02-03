package com.elenai.elenaidodge.capability.dodges;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class DodgesStorage implements IStorage<IDodges> {

	@Override
	public NBTBase writeNBT(Capability<IDodges> capability, IDodges instance, EnumFacing side) {
		return new NBTTagInt(instance.getDodges());
	}

	@Override
	public void readNBT(Capability<IDodges> capability, IDodges instance, EnumFacing side, NBTBase nbt) {
		instance.set(((NBTPrimitive) nbt).getInt());
	}

}
