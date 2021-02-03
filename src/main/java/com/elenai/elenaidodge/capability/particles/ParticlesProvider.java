package com.elenai.elenaidodge.capability.particles;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ParticlesProvider implements ICapabilitySerializable<NBTBase> {

	@CapabilityInject(IParticles.class)
	public static final Capability<IParticles> PARTICLES_CAP = null;
	
	private IParticles instance = PARTICLES_CAP.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == PARTICLES_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == PARTICLES_CAP ? PARTICLES_CAP.<T> cast (this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return PARTICLES_CAP.getStorage().writeNBT(PARTICLES_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		PARTICLES_CAP.getStorage().readNBT(PARTICLES_CAP, this.instance, null, nbt);
	}

}
