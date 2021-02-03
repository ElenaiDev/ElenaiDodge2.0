package com.elenai.elenaidodge.network.message;

import java.util.function.Supplier;

import com.elenai.elenaidodge.effects.ClientDodgeEffects;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CDodgeEffectsMessage implements IMessage<CDodgeEffectsMessage> {

	private int dodges, absorption;

	public CDodgeEffectsMessage() {

	}

	public CDodgeEffectsMessage(int dodges, int absorption) {
		this.dodges = dodges;
		this.absorption = absorption;

	}

	@Override
	public void encode(CDodgeEffectsMessage message, PacketBuffer buffer) {
		buffer.writeInt(message.dodges);
		buffer.writeInt(message.absorption);

	}

	@Override
	public CDodgeEffectsMessage decode(PacketBuffer buffer) {
		return new CDodgeEffectsMessage(buffer.readInt(), buffer.readInt());
	}

	@Override
	public void handle(CDodgeEffectsMessage message, Supplier<Context> supplier) {
		supplier.get().enqueueWork(() -> {
			ClientDodgeEffects.run(message.dodges, message.absorption);
		});

		supplier.get().setPacketHandled(true);

	}

}
