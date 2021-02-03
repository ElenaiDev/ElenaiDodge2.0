package com.elenai.elenaidodge.network.message;

import java.util.function.Supplier;

import com.elenai.elenaidodge.event.ArmorTickEventListener;
import com.elenai.elenaidodge.util.ClientStorage;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CUpdateConfigMessage implements IMessage<CUpdateConfigMessage> {

	private int regenRate, dodges, absorption;
	private String weights;
	private boolean half;

	public CUpdateConfigMessage() {

	}

	public CUpdateConfigMessage(int regenRate, int dodges, String weights, boolean half, int absorption) {
		this.regenRate = regenRate;
		this.dodges = dodges;
		this.weights = weights;
		this.half = half;
		this.absorption = absorption;


	}

	@Override
	public void encode(CUpdateConfigMessage message, PacketBuffer buffer) {
		buffer.writeInt(message.regenRate);
		buffer.writeInt(message.dodges);
		buffer.writeString(message.weights);
		buffer.writeBoolean(message.half);
		buffer.writeInt(message.absorption);

	}

	@Override
	public CUpdateConfigMessage decode(PacketBuffer buffer) {
		return new CUpdateConfigMessage(buffer.readInt(), buffer.readInt(), buffer.readString(999999), buffer.readBoolean(), buffer.readInt());
	}

	@Override
	public void handle(CUpdateConfigMessage message, Supplier<Context> supplier) {
		supplier.get().enqueueWork(() -> {
			ClientStorage.regenSpeed = message.regenRate;

			if (message.dodges != 9999) {
				ClientStorage.dodges = message.dodges;
				ClientStorage.absorption = message.absorption;
			}
			
			ClientStorage.weightValues = message.weights;
			ClientStorage.halfFeathers = message.half;
			
			
			// Forces Armor Refresh
			ArmorTickEventListener.previousArmor.clear();
		});

		supplier.get().setPacketHandled(true);

	}

}
