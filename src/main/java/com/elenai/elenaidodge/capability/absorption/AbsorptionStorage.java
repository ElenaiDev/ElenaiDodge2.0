package com.elenai.elenaidodge.capability.absorption;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class AbsorptionStorage implements IStorage<IAbsorption> {

	@Override
	public NBTBase writeNBT(Capability<IAbsorption> capability, IAbsorption instance, EnumFacing side) {
		return new NBTTagInt(instance.getAbsorption());
	}

	@Override
	public void readNBT(Capability<IAbsorption> capability, IAbsorption instance, EnumFacing side, NBTBase nbt) {
		instance.set(((NBTPrimitive) nbt).getInt());
	}

}
