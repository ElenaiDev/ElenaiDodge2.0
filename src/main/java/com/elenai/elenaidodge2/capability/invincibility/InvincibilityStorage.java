package com.elenai.elenaidodge2.capability.invincibility;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class InvincibilityStorage implements IStorage<IInvincibility> {

	@Override
	public INBT writeNBT(Capability<IInvincibility> capability, IInvincibility instance, Direction side) {
		return IntNBT.valueOf(instance.getInvincibility());
	}

	@Override
	public void readNBT(Capability<IInvincibility> capability, IInvincibility instance, Direction side, INBT nbt) {
		instance.set(((IntNBT) nbt).getInt());
	}

}
