package com.elenai.elenaidodge.capability.weight;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class WeightProvider implements ICapabilitySerializable<NBTBase> {

	@CapabilityInject(IWeight.class)
	public static final Capability<IWeight> WEIGHT_CAP = null;
	
	private IWeight instance = WEIGHT_CAP.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == WEIGHT_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == WEIGHT_CAP ? WEIGHT_CAP.<T> cast (this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return WEIGHT_CAP.getStorage().writeNBT(WEIGHT_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		WEIGHT_CAP.getStorage().readNBT(WEIGHT_CAP, this.instance, null, nbt);
	}

}
