package com.elenai.elenaidodge.capability.absorption;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class AbsorptionProvider implements ICapabilitySerializable<NBTBase> {

	@CapabilityInject(IAbsorption.class)
	public static final Capability<IAbsorption> ABSORPTION_CAP = null;
	
	private IAbsorption instance = ABSORPTION_CAP.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == ABSORPTION_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == ABSORPTION_CAP ? ABSORPTION_CAP.<T> cast (this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return ABSORPTION_CAP.getStorage().writeNBT(ABSORPTION_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		ABSORPTION_CAP.getStorage().readNBT(ABSORPTION_CAP, this.instance, null, nbt);
	}

}
