package com.elenai.elenaidodge.network.message;

import java.util.function.Supplier;

import com.elenai.elenaidodge.config.ConfigHandler;
import com.elenai.elenaidodge.util.ClientStorage;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CInitPlayerMessage implements IMessage<CInitPlayerMessage> {

	private int dodges;

	public CInitPlayerMessage() {

	}

	public CInitPlayerMessage(int dodges) {
		this.dodges = dodges;
		
	}

	@Override
	public void encode(CInitPlayerMessage message, PacketBuffer buffer) {
		buffer.writeInt(message.dodges);

	}

	@Override
	public CInitPlayerMessage decode(PacketBuffer buffer) {
		return new CInitPlayerMessage(buffer.readInt());
	}

	@Override
	public void handle(CInitPlayerMessage message, Supplier<Context> supplier) {
		supplier.get().enqueueWork(() -> {
			
			ClientStorage.dodges = message.dodges;
			if(ConfigHandler.tutorial) {
			ClientStorage.shownTutorial = false;
			ClientStorage.tutorialDodges = 0;
			}
			});

		supplier.get().setPacketHandled(true);

	}

}
