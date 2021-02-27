package com.elenai.elenaidodge2.capability.regen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class RegenProvider implements ICapabilitySerializable<INBT> {

	@CapabilityInject(IRegen.class)
	public static final Capability<IRegen> REGEN_CAP = null;
	
	private LazyOptional<IRegen> instance = LazyOptional.of(REGEN_CAP::getDefaultInstance);

	    @Nonnull
	    @Override
	    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
	        return cap == REGEN_CAP ? instance.cast() : LazyOptional.empty();
	    }

	    @Override
	    public INBT serializeNBT() {
	        return REGEN_CAP.getStorage().writeNBT(REGEN_CAP, instance.orElseThrow( () -> new NullPointerException("Lazy Optional was empty")), null);
	    }

	    @Override
	    public void deserializeNBT(INBT nbt) {
	    	REGEN_CAP.getStorage().readNBT(REGEN_CAP, instance.orElseThrow( () -> new NullPointerException("Lazy Optional was empty")), null, nbt);
	    }

}
