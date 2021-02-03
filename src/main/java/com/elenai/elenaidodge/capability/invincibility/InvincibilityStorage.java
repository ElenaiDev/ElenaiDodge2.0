package com.elenai.elenaidodge.capability.invincibility;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class InvincibilityStorage implements IStorage<IInvincibility> {

	@Override
	public NBTBase writeNBT(Capability<IInvincibility> capability, IInvincibility instance, EnumFacing side) {
		return new NBTTagInt(instance.getInvincibility());
	}

	@Override
	public void readNBT(Capability<IInvincibility> capability, IInvincibility instance, EnumFacing side, NBTBase nbt) {
		instance.set(((NBTPrimitive) nbt).getInt());
	}

}
