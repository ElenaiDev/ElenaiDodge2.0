package com.elenai.elenaidodge.capability.absorptionbool;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class AbsorptionBoolProvider implements ICapabilitySerializable<INBT> {

	@CapabilityInject(IAbsorptionBool.class)
	public static final Capability<IAbsorptionBool> ABSORPTION_BOOL_CAP = null;
	
	private LazyOptional<IAbsorptionBool> instance = LazyOptional.of(ABSORPTION_BOOL_CAP::getDefaultInstance);

	    @Nonnull
	    @Override
	    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
	        return cap == ABSORPTION_BOOL_CAP ? instance.cast() : LazyOptional.empty();
	    }

	    @Override
	    public INBT serializeNBT() {
	        return ABSORPTION_BOOL_CAP.getStorage().writeNBT(ABSORPTION_BOOL_CAP, instance.orElseThrow( () -> new NullPointerException("Lazy Optional was empty")), null);
	    }

	    @Override
	    public void deserializeNBT(INBT nbt) {
	    	ABSORPTION_BOOL_CAP.getStorage().readNBT(ABSORPTION_BOOL_CAP, instance.orElseThrow( () -> new NullPointerException("Lazy Optional was empty")), null, nbt);
	    }
	
	

}
