package com.elenai.elenaidodge.capability.absorptionbool;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class AbsorptionBoolStorage implements IStorage<IAbsorptionBool> {

	@Override
	public NBTBase writeNBT(Capability<IAbsorptionBool> capability, IAbsorptionBool instance, EnumFacing side) {
		return new NBTTagInt(instance.hasAbsorption() ? 1 : 0);
	}

	@Override
	public void readNBT(Capability<IAbsorptionBool> capability, IAbsorptionBool instance, EnumFacing side, NBTBase nbt) {
		int i = ((NBTPrimitive) nbt).getInt(); 
		boolean b = i > 0;
		instance.set(b);
	}

}
