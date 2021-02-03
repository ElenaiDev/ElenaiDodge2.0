package com.elenai.elenaidodge.network.message;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CVelocityMessage implements IMessage<CVelocityMessage> {

	private double motionX, motionY, motionZ;


	public CVelocityMessage() {
	}

	public CVelocityMessage(double motionX, double motionY, double motionZ) {
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;

	}

	@Override
	public void encode(CVelocityMessage message, PacketBuffer buffer) {
		buffer.writeDouble(message.motionX);
		buffer.writeDouble(message.motionY);
		buffer.writeDouble(message.motionZ);

	}

	@Override
	public CVelocityMessage decode(PacketBuffer buffer) {
		return new CVelocityMessage(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
	}

	@SuppressWarnings("resource")
	@Override
	public void handle(CVelocityMessage message, Supplier<Context> supplier) {
		supplier.get().enqueueWork(() -> {
			Minecraft.getInstance().player.setVelocity(message.motionX, message.motionY, message.motionZ);

		});

		supplier.get().setPacketHandled(true);

	}

}
