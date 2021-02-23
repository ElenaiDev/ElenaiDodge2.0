package com.elenai.elenaidodge2.capability.absorption;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class AbsorptionProvider implements ICapabilitySerializable<INBT> {

	@CapabilityInject(IAbsorption.class)
	public static final Capability<IAbsorption> ABSORPTION_CAP = null;
	
	private LazyOptional<IAbsorption> instance = LazyOptional.of(ABSORPTION_CAP::getDefaultInstance);

	    @Nonnull
	    @Override
	    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
	        return cap == ABSORPTION_CAP ? instance.cast() : LazyOptional.empty();
	    }

	    @Override
	    public INBT serializeNBT() {
	        return ABSORPTION_CAP.getStorage().writeNBT(ABSORPTION_CAP, instance.orElseThrow( () -> new NullPointerException("Lazy Optional was empty")), null);
	    }

	    @Override
	    public void deserializeNBT(INBT nbt) {
	    	ABSORPTION_CAP.getStorage().readNBT(ABSORPTION_CAP, instance.orElseThrow( () -> new NullPointerException("Lazy Optional was empty")), null, nbt);
	    }

}
