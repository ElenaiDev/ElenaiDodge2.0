package com.elenai.elenaidodge.capability.absorptionbool;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class AbsorptionBoolProvider implements ICapabilitySerializable<NBTBase> {

	@CapabilityInject(IAbsorptionBool.class)
	public static final Capability<IAbsorptionBool> ABSORPTION_BOOL_CAP = null;
	
	private IAbsorptionBool instance = ABSORPTION_BOOL_CAP.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == ABSORPTION_BOOL_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == ABSORPTION_BOOL_CAP ? ABSORPTION_BOOL_CAP.<T> cast (this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return ABSORPTION_BOOL_CAP.getStorage().writeNBT(ABSORPTION_BOOL_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		ABSORPTION_BOOL_CAP.getStorage().readNBT(ABSORPTION_BOOL_CAP, this.instance, null, nbt);
	}

}
