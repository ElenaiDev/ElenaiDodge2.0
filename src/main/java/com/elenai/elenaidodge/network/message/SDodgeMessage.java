package com.elenai.elenaidodge.network.message;

import java.util.function.Supplier;

import com.elenai.elenaidodge.util.DodgeEvent;
import com.elenai.elenaidodge.util.DodgeEvent.Direction;
import com.elenai.elenaidodge.util.DodgeEvent.ServerDodgeEvent;
import com.elenai.elenaidodge.util.Utils;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class SDodgeMessage implements IMessage<SDodgeMessage> {

	private String dir;

	public SDodgeMessage() {

	}

	public SDodgeMessage(String dir) {
		this.dir = dir;
		
	}

	@Override
	public void encode(SDodgeMessage message, PacketBuffer buffer) {
		buffer.writeString(message.dir);

	}

	@Override
	public SDodgeMessage decode(PacketBuffer buffer) {
		return new SDodgeMessage(buffer.readString(999999));
	}

	@Override
	public void handle(SDodgeMessage message, Supplier<Context> supplier) {
		supplier.get().enqueueWork(() -> {
			ServerPlayerEntity player = supplier.get().getSender();

			DodgeEvent event = new ServerDodgeEvent(Direction.valueOf(message.dir), Utils.calculateForce(player), player);
			if(!MinecraftForge.EVENT_BUS.post(event)) {
				Utils.handleDodge(Direction.valueOf(message.dir), event, player);
			}
		});

		supplier.get().setPacketHandled(true);

	}

}
