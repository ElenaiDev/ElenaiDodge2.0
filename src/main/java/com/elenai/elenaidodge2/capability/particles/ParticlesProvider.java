package com.elenai.elenaidodge2.capability.particles;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ParticlesProvider implements ICapabilitySerializable<INBT> {

	@CapabilityInject(IParticles.class)
	public static final Capability<IParticles> PARTICLES_CAP = null;
	
	private LazyOptional<IParticles> instance = LazyOptional.of(PARTICLES_CAP::getDefaultInstance);

	    @Nonnull
	    @Override
	    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
	        return cap == PARTICLES_CAP ? instance.cast() : LazyOptional.empty();
	    }

	    @Override
	    public INBT serializeNBT() {
	        return PARTICLES_CAP.getStorage().writeNBT(PARTICLES_CAP, instance.orElseThrow( () -> new NullPointerException("Lazy Optional was empty")), null);
	    }

	    @Override
	    public void deserializeNBT(INBT nbt) {
	    	PARTICLES_CAP.getStorage().readNBT(PARTICLES_CAP, instance.orElseThrow( () -> new NullPointerException("Lazy Optional was empty")), null, nbt);
	    }

}
