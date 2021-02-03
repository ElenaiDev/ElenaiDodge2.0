package com.elenai.elenaidodge.network.message;

import java.util.function.Supplier;

import com.elenai.elenaidodge.event.ArmorTickEventListener;
import com.elenai.elenaidodge.util.ClientStorage;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CUpdateStatsMessage implements IMessage<CUpdateStatsMessage> {

	private int dodges, absorption, weight;

	public CUpdateStatsMessage() {

	}

	public CUpdateStatsMessage(int dodges, int absorption, int weight) {
		this.dodges = dodges;
		this.absorption = absorption;
		this.weight = weight;
	}

	@Override
	public void encode(CUpdateStatsMessage message, PacketBuffer buffer) {
		buffer.writeInt(message.dodges);
		buffer.writeInt(message.absorption);
		buffer.writeInt(message.weight);

	}

	@Override
	public CUpdateStatsMessage decode(PacketBuffer buffer) {
		return new CUpdateStatsMessage(buffer.readInt(), buffer.readInt(), buffer.readInt());
	}

	@Override
	public void handle(CUpdateStatsMessage message, Supplier<Context> supplier) {
		supplier.get().enqueueWork(() -> {
			ClientStorage.absorption = message.absorption;
			ClientStorage.dodges = message.dodges;
			if(message.weight != 0) {	
				ClientStorage.weight = message.weight;
				} else {
					// Forces Armor Refresh
					ArmorTickEventListener.previousArmor.clear();
				}
		});

		supplier.get().setPacketHandled(true);

	}

}
