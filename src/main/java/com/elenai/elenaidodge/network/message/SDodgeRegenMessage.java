package com.elenai.elenaidodge.network.message;

import java.util.function.Supplier;

import com.elenai.elenaidodge.capability.dodges.DodgesProvider;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class SDodgeRegenMessage implements IMessage<SDodgeRegenMessage> {

	private int dodges;

	public SDodgeRegenMessage() {

	}

	public SDodgeRegenMessage(int dodges) {
		this.dodges = dodges;
	}

	@Override
	public void encode(SDodgeRegenMessage message, PacketBuffer buffer) {
		buffer.writeInt(message.dodges);

	}

	@Override
	public SDodgeRegenMessage decode(PacketBuffer buffer) {
		return new SDodgeRegenMessage(buffer.readInt());
	}

	@Override
	public void handle(SDodgeRegenMessage message, Supplier<Context> supplier) {
		supplier.get().enqueueWork(() -> {
			ServerPlayerEntity player =supplier.get().getSender();
			player.getCapability(DodgesProvider.DODGES_CAP).ifPresent(d -> {
				if(d.getDodges() < 20) {
					d.set(message.dodges);
					}
			});
		});

		supplier.get().setPacketHandled(true);

	}

}
