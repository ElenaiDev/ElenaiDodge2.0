package com.elenai.elenaidodge.capability.dodges;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class DodgesProvider implements ICapabilitySerializable<NBTBase> {

	@CapabilityInject(IDodges.class)
	public static final Capability<IDodges> DODGES_CAP = null;
	
	private IDodges instance = DODGES_CAP.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == DODGES_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == DODGES_CAP ? DODGES_CAP.<T> cast (this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return DODGES_CAP.getStorage().writeNBT(DODGES_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		DODGES_CAP.getStorage().readNBT(DODGES_CAP, this.instance, null, nbt);
	}

}
