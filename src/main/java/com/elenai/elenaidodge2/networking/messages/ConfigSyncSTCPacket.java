package com.elenai.elenaidodge2.networking.messages;

import java.util.function.Supplier;

import com.elenai.elenaidodge2.client.ED2ClientStorage;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class ConfigSyncSTCPacket {
	private final boolean airborne;
	private final int cooldown;
	private final int cost;
	private final double power;
	private final double verticality;
	private final int airborneCost;
	
	public ConfigSyncSTCPacket(boolean airborne, int cooldown, int cost, double power, double verticality, int airborneCost) {
	this.airborne = airborne;
	this.cooldown = cooldown;
	this.cost = cost;
	this.power = power;
	this.verticality = verticality;
	this.airborneCost = airborneCost;
	}

	public ConfigSyncSTCPacket(FriendlyByteBuf buf) {
		this.airborne = buf.readBoolean();
		this.cooldown = buf.readInt();
		this.cost = buf.readInt();
		this.power = buf.readDouble();
		this.verticality = buf.readDouble();
		this.airborneCost = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeBoolean(airborne);
		buf.writeInt(cooldown);
		buf.writeInt(cost);
		buf.writeDouble(power);
		buf.writeDouble(verticality);
		buf.writeInt(airborneCost);
	}

	public boolean handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			ED2ClientStorage.setAirborne(airborne);
			ED2ClientStorage.setCooldown(cooldown);
			ED2ClientStorage.setCost(cost);
			ED2ClientStorage.setPower(power);
			ED2ClientStorage.setVerticality(verticality);
			ED2ClientStorage.setAirborneCost(airborneCost);
		});
		return true;
	}
}
