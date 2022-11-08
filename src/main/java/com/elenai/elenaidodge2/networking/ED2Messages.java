package com.elenai.elenaidodge2.networking;

import com.elenai.elenaidodge2.ElenaiDodge2;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ED2Messages {
	private static SimpleChannel INSTANCE;

	private static int packetId = 0;

	private static int id() {
		return packetId++;
	}

	public static void register() {
		SimpleChannel network = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(ElenaiDodge2.MODID, "messages"))
				.networkProtocolVersion(() -> "1.0").clientAcceptedVersions(s -> true).serverAcceptedVersions(s -> true)
				.simpleChannel();
		INSTANCE = network;

        
        network.messageBuilder(ConfigSyncSTCPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
        .decoder(ConfigSyncSTCPacket::new)
        .encoder(ConfigSyncSTCPacket::toBytes)
        .consumerMainThread(ConfigSyncSTCPacket::handle)
        .add();
        
        network.messageBuilder(DodgeAnimationSTCPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
        .decoder(DodgeAnimationSTCPacket::new)
        .encoder(DodgeAnimationSTCPacket::toBytes)
        .consumerMainThread(DodgeAnimationSTCPacket::handle)
        .add();
        
        network.messageBuilder(DodgeEffectsCTSPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
        .decoder(DodgeEffectsCTSPacket::new)
        .encoder(DodgeEffectsCTSPacket::toBytes)
        .consumerMainThread(DodgeEffectsCTSPacket::handle)
        .add();
        
	}
	
	public static <MSG> void sendToServer(MSG message) {
		INSTANCE.sendToServer(message);
	}
	
	public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
		INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
	}
	
	public static <MSG> void sendToPlayersNearby(MSG message, ServerPlayer player) {
		INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> player), message);
	}
	
	public static <MSG> void sendToPlayersNearbyAndSelf(MSG message, ServerPlayer player) {
		INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), message);
	}
}
