package com.elenai.elenaidodge.network;

import static net.minecraftforge.fml.network.NetworkDirection.PLAY_TO_CLIENT;
import static net.minecraftforge.fml.network.NetworkDirection.PLAY_TO_SERVER;

import java.util.Optional;

import com.elenai.elenaidodge.ElenaiDodge;
import com.elenai.elenaidodge.network.handler.MessageHandlerOnClient;
import com.elenai.elenaidodge.network.handler.MessageHandlerOnServer;
import com.elenai.elenaidodge.network.message.client.AbsorptionMessageToClient;
import com.elenai.elenaidodge.network.message.client.ConfigMessageToClient;
import com.elenai.elenaidodge.network.message.client.DodgeEffectsMessageToClient;
import com.elenai.elenaidodge.network.message.client.DodgeMessageToClient;
import com.elenai.elenaidodge.network.message.client.InitPlayerMessageToClient;
import com.elenai.elenaidodge.network.message.client.ParticleMessageToClient;
import com.elenai.elenaidodge.network.message.client.PatronMessageToClient;
import com.elenai.elenaidodge.network.message.client.VelocityMessageToClient;
import com.elenai.elenaidodge.network.message.client.WeightMessageToClient;
import com.elenai.elenaidodge.network.message.server.DodgeMessageToServer;
import com.elenai.elenaidodge.network.message.server.DodgeRegenMessageToServer;
import com.elenai.elenaidodge.network.message.server.ThirstMessageToServer;
import com.elenai.elenaidodge.network.message.server.WeightMessageToServer;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkHandler {

	  public static SimpleChannel simpleChannel;    // used to transmit your network messages

	  public static byte nextId = 0;

	  public static final String MESSAGE_PROTOCOL_VERSION = "1.0";  // a version number for the protocol you're using.  Can be used to maintain backward
	                                                                // compatibility.  But to be honest you'll probably never need it for anything useful...

	  public static final ResourceLocation simpleChannelRL = new ResourceLocation(ElenaiDodge.MODID, "channel");

	
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
	}
	
}
