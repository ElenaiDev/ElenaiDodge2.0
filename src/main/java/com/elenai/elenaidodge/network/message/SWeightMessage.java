package com.elenai.elenaidodge.network.message;

import java.util.function.Supplier;

import com.elenai.elenaidodge.capability.invincibility.WeightProvider;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class SWeightMessage implements IMessage<SWeightMessage> {

	private int weight;

	public SWeightMessage() {

	}

	public SWeightMessage(int weight) {
		this.weight = weight;
	}

	@Override
	public void encode(SWeightMessage message, PacketBuffer buffer) {
		buffer.writeInt(message.weight);

	}

	@Override
	public SWeightMessage decode(PacketBuffer buffer) {
		return new SWeightMessage(buffer.readInt());
	}

	@Override
	public void handle(SWeightMessage message, Supplier<Context> supplier) {
		supplier.get().enqueueWork(() -> {
			ServerPlayerEntity player =supplier.get().getSender();
			player.getCapability(WeightProvider.WEIGHT_CAP).ifPresent(w -> {
				w.set(message.weight);
			});
		});

		supplier.get().setPacketHandled(true);

	}

}
