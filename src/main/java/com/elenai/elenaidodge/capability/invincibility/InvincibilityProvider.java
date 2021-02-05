package com.elenai.elenaidodge.capability.invincibility;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class InvincibilityProvider implements ICapabilitySerializable<INBT> {

	@CapabilityInject(IInvincibility.class)
	public static final Capability<IInvincibility> INVINCIBILITY_CAP = null;
	
	private LazyOptional<IInvincibility> instance = LazyOptional.of(INVINCIBILITY_CAP::getDefaultInstance);

	    @Nonnull
	    @Override
	    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
	        return cap == INVINCIBILITY_CAP ? instance.cast() : LazyOptional.empty();
	    }

	    @Override
	    public INBT serializeNBT() {
	        return INVINCIBILITY_CAP.getStorage().writeNBT(INVINCIBILITY_CAP, instance.orElseThrow( () -> new NullPointerException("Lazy Optional was empty")), null);
	    }

	    @Override
	    public void deserializeNBT(INBT nbt) {
	    	INVINCIBILITY_CAP.getStorage().readNBT(INVINCIBILITY_CAP, instance.orElseThrow( () -> new NullPointerException("Lazy Optional was empty")), null, nbt);
	    }

}
