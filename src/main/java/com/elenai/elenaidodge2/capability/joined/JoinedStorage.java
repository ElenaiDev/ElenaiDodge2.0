package com.elenai.elenaidodge2.capability.joined;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class JoinedStorage implements IStorage<IJoined> {

	@Override
	public INBT writeNBT(Capability<IJoined> capability, IJoined instance, Direction side) {
		return IntNBT.valueOf(instance.getJoined() ? 1 : 0);
	}

	@Override
	public void readNBT(Capability<IJoined> capability, IJoined instance, Direction side, INBT nbt) {
		int i = ((IntNBT) nbt).getInt(); 
		boolean b = i > 0;
		instance.set(b);
	}


}
