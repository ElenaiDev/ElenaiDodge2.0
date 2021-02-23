package com.elenai.elenaidodge2.capability.weight;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class WeightProvider implements ICapabilitySerializable<INBT> {

	@CapabilityInject(IWeight.class)
	public static final Capability<IWeight> WEIGHT_CAP = null;
	
	private LazyOptional<IWeight> instance = LazyOptional.of(WEIGHT_CAP::getDefaultInstance);

	    @Nonnull
	    @Override
	    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
	        return cap == WEIGHT_CAP ? instance.cast() : LazyOptional.empty();
	    }

	    @Override
	    public INBT serializeNBT() {
	        return WEIGHT_CAP.getStorage().writeNBT(WEIGHT_CAP, instance.orElseThrow( () -> new NullPointerException("Lazy Optional was empty")), null);
	    }

	    @Override
	    public void deserializeNBT(INBT nbt) {
	    	WEIGHT_CAP.getStorage().readNBT(WEIGHT_CAP, instance.orElseThrow( () -> new NullPointerException("Lazy Optional was empty")), null, nbt);
	    }

}
