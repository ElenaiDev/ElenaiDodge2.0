package com.elenai.elenaidodge.capability.particles;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ParticlesStorage implements IStorage<IParticles> {

	@Override
	public NBTBase writeNBT(Capability<IParticles> capability, IParticles instance, EnumFacing side) {
		return new NBTTagInt(instance.getParticles());
	}

	@Override
	public void readNBT(Capability<IParticles> capability, IParticles instance, EnumFacing side, NBTBase nbt) {
		instance.set(((NBTPrimitive) nbt).getInt());
	}

}
