package com.elenai.elenaidodge.capability.particles;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ParticlesStorage implements IStorage<IParticles> {

	@Override
	public INBT writeNBT(Capability<IParticles> capability, IParticles instance, Direction side) {
		return IntNBT.valueOf(instance.getParticles());
	}

	@Override
	public void readNBT(Capability<IParticles> capability, IParticles instance, Direction side, INBT nbt) {
		instance.set(((IntNBT) nbt).getInt());
	}

}
