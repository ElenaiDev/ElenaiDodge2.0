package com.elenai.elenaidodge.capability.joined;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class JoinedStorage implements IStorage<IJoined> {

	@Override
	public NBTBase writeNBT(Capability<IJoined> capability, IJoined instance, EnumFacing side) {
		return new NBTTagInt(instance.hasJoined() ? 1 : 0);
	}

	@Override
	public void readNBT(Capability<IJoined> capability, IJoined instance, EnumFacing side, NBTBase nbt) {
		int i = ((NBTPrimitive) nbt).getInt(); 
		boolean b = i > 0;
		instance.set(b);
	}

}
