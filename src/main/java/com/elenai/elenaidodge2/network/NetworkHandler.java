package com.elenai.elenaidodge2.network;

import static net.minecraftforge.fml.network.NetworkDirection.PLAY_TO_CLIENT;
import static net.minecraftforge.fml.network.NetworkDirection.PLAY_TO_SERVER;

import java.util.Optional;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.network.handler.MessageHandlerOnClient;
import com.elenai.elenaidodge2.network.handler.MessageHandlerOnServer;
import com.elenai.elenaidodge2.network.message.client.AbsorptionMessageToClient;
import com.elenai.elenaidodge2.network.message.client.CancelledFeathersMessageToClient;
import com.elenai.elenaidodge2.network.message.client.ConfigMessageToClient;
import com.elenai.elenaidodge2.network.message.client.DodgeEffectsMessageToClient;
import com.elenai.elenaidodge2.network.message.client.DodgeMessageToClient;
import com.elenai.elenaidodge2.network.message.client.InitPlayerMessageToClient;
import com.elenai.elenaidodge2.network.message.client.ParticleMessageToClient;
import com.elenai.elenaidodge2.network.message.client.PatronMessageToClient;
import com.elenai.elenaidodge2.network.message.client.RegenModifierMessageToClient;
import com.elenai.elenaidodge2.network.message.client.VelocityMessageToClient;
import com.elenai.elenaidodge2.network.message.client.WeightMessageToClient;
import com.elenai.elenaidodge2.network.message.server.DodgeMessageToServer;
import com.elenai.elenaidodge2.network.message.server.DodgeRegenMessageToServer;
import com.elenai.elenaidodge2.network.message.server.ThirstMessageToServer;
import com.elenai.elenaidodge2.network.message.server.WeightMessageToServer;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkHandler {

	  public static SimpleChannel simpleChannel;    

	  public static byte nextId = 0;

	  public static final String MESSAGE_PROTOCOL_VERSION = "1.0"; 
	                                                               

	  public static final ResourceLocation simpleChannelRL = new ResourceLocation(ElenaiDodge2.MODID, "channel");

	
	public static void init() {
		simpleChannel = NetworkRegistry.newSimpleChannel(simpleChannelRL, () -> MESSAGE_PROTOCOL_VERSION,
				MessageHandlerOnClient::isThisProtocolAcceptedByClient,
		            MessageHandlerOnServer::isThisProtocolAcceptedByServer);

		 // SERVER
		    simpleChannel.registerMessage(nextId++, WeightMessageToServer.class,
		    		WeightMessageToServer::encode, WeightMessageToServer::decode,
		            MessageHandlerOnServer::onMessageReceived,
		            Optional.of(PLAY_TO_SERVER));
		    simpleChannel.registerMessage(nextId++, DodgeMessageToServer.class,
		    		DodgeMessageToServer::encode, DodgeMessageToServer::decode,
		    		MessageHandlerOnServer::onMessageReceived,
		            Optional.of(PLAY_TO_SERVER));
		    simpleChannel.registerMessage(nextId++, DodgeRegenMessageToServer.class,
		    		DodgeRegenMessageToServer::encode, DodgeRegenMessageToServer::decode,
		    		MessageHandlerOnServer::onMessageReceived,
		            Optional.of(PLAY_TO_SERVER));
		    simpleChannel.registerMessage(nextId++, ThirstMessageToServer.class,
		    		ThirstMessageToServer::encode, ThirstMessageToServer::decode,
		    		MessageHandlerOnServer::onMessageReceived,
		            Optional.of(PLAY_TO_SERVER));

		    // CLIENT	
		    simpleChannel.registerMessage(nextId++, WeightMessageToClient.class,
		    		WeightMessageToClient::encode, WeightMessageToClient::decode,
		            MessageHandlerOnClient::onMessageReceived,
		            Optional.of(PLAY_TO_CLIENT));
		    simpleChannel.registerMessage(nextId++, CancelledFeathersMessageToClient.class,
		    		CancelledFeathersMessageToClient::encode, CancelledFeathersMessageToClient::decode,
		            MessageHandlerOnClient::onMessageReceived,
		            Optional.of(PLAY_TO_CLIENT));
		    simpleChannel.registerMessage(nextId++, DodgeEffectsMessageToClient.class,
		    		DodgeEffectsMessageToClient::encode, DodgeEffectsMessageToClient::decode,
		            MessageHandlerOnClient::onMessageReceived,
		            Optional.of(PLAY_TO_CLIENT));
		    simpleChannel.registerMessage(nextId++, InitPlayerMessageToClient.class,
		    		InitPlayerMessageToClient::encode, InitPlayerMessageToClient::decode,
		            MessageHandlerOnClient::onMessageReceived,
		            Optional.of(PLAY_TO_CLIENT));
		    simpleChannel.registerMessage(nextId++, ParticleMessageToClient.class,
		    		ParticleMessageToClient::encode, ParticleMessageToClient::decode,
		            MessageHandlerOnClient::onMessageReceived,
		            Optional.of(PLAY_TO_CLIENT));
		    simpleChannel.registerMessage(nextId++, AbsorptionMessageToClient.class,
		    		AbsorptionMessageToClient::encode, AbsorptionMessageToClient::decode,
		            MessageHandlerOnClient::onMessageReceived,
		            Optional.of(PLAY_TO_CLIENT));
		    simpleChannel.registerMessage(nextId++, DodgeMessageToClient.class,
		    		DodgeMessageToClient::encode, DodgeMessageToClient::decode,
		            MessageHandlerOnClient::onMessageReceived,
		            Optional.of(PLAY_TO_CLIENT));
		    simpleChannel.registerMessage(nextId++, VelocityMessageToClient.class,
		    		VelocityMessageToClient::encode, VelocityMessageToClient::decode,
		            MessageHandlerOnClient::onMessageReceived,
		            Optional.of(PLAY_TO_CLIENT));
		    simpleChannel.registerMessage(nextId++, ConfigMessageToClient.class,
		    		ConfigMessageToClient::encode, ConfigMessageToClient::decode,
		            MessageHandlerOnClient::onMessageReceived,
		            Optional.of(PLAY_TO_CLIENT));
		    simpleChannel.registerMessage(nextId++, PatronMessageToClient.class,
		    		PatronMessageToClient::encode, PatronMessageToClient::decode,
		            MessageHandlerOnClient::onMessageReceived,
		            Optional.of(PLAY_TO_CLIENT));
		    simpleChannel.registerMessage(nextId++, RegenModifierMessageToClient.class,
		    		RegenModifierMessageToClient::encode, RegenModifierMessageToClient::decode,
		            MessageHandlerOnClient::onMessageReceived,
		            Optional.of(PLAY_TO_CLIENT));
	}
	
}
