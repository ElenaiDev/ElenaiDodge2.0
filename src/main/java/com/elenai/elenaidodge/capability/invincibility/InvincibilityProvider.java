package com.elenai.elenaidodge.capability.invincibility;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class InvincibilityProvider implements ICapabilitySerializable<NBTBase> {

	@CapabilityInject(IInvincibility.class)
	public static final Capability<IInvincibility> INVINCIBILITY_CAP = null;
	
	private IInvincibility instance = INVINCIBILITY_CAP.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == INVINCIBILITY_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == INVINCIBILITY_CAP ? INVINCIBILITY_CAP.<T> cast (this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return INVINCIBILITY_CAP.getStorage().writeNBT(INVINCIBILITY_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		INVINCIBILITY_CAP.getStorage().readNBT(INVINCIBILITY_CAP, this.instance, null, nbt);
	}

}
