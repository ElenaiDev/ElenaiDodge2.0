package com.elenai.elenaidodge2.capability.regen;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class RegenProvider implements ICapabilitySerializable<NBTBase> {

	@CapabilityInject(IRegen.class)
	public static final Capability<IRegen> REGEN_CAP = null;
	
	private IRegen instance = REGEN_CAP.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == REGEN_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == REGEN_CAP ? REGEN_CAP.<T> cast (this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return REGEN_CAP.getStorage().writeNBT(REGEN_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		REGEN_CAP.getStorage().readNBT(REGEN_CAP, this.instance, null, nbt);
	}

}
