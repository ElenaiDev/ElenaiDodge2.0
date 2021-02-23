package com.elenai.elenaidodge2.network.handler;

import java.util.function.Supplier;

import com.elenai.elenaidodge2.ElenaiDodge;
import com.elenai.elenaidodge2.api.DodgeEvent;
import com.elenai.elenaidodge2.api.DodgeEvent.Direction;
import com.elenai.elenaidodge2.api.DodgeEvent.ServerDodgeEvent;
import com.elenai.elenaidodge2.capability.dodges.DodgesProvider;
import com.elenai.elenaidodge2.capability.weight.WeightProvider;
import com.elenai.elenaidodge2.integration.ToughAsNailsServer;
import com.elenai.elenaidodge2.network.NetworkHandler;
import com.elenai.elenaidodge2.network.message.server.DodgeMessageToServer;
import com.elenai.elenaidodge2.network.message.server.DodgeRegenMessageToServer;
import com.elenai.elenaidodge2.network.message.server.ThirstMessageToServer;
import com.elenai.elenaidodge2.network.message.server.WeightMessageToServer;
import com.elenai.elenaidodge2.util.Utils;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessageHandlerOnServer {

	/**
	 * Called when a message is received of the appropriate type. CALLED BY THE
	 * NETWORK THREAD, NOT THE SERVER THREAD
	 * 
	 * @param message The message
	 */
	public static void onMessageReceived(final WeightMessageToServer message,
			Supplier<NetworkEvent.Context> ctxSupplier) {
		NetworkEvent.Context ctx = ctxSupplier.get();
		LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
		ctx.setPacketHandled(true);

		if (sideReceived != LogicalSide.SERVER) {
			ElenaiDodge.LOGGER
					.warn("WeightMessageToServer received on wrong side:" + ctx.getDirection().getReceptionSide());
			return;
		}
		if (!message.isMessageValid()) {
			ElenaiDodge.LOGGER.warn("WeightMessageToServer was invalid" + message.toString());
			return;
		}

		final ServerPlayerEntity sendingPlayer = ctx.getSender();
		if (sendingPlayer == null) {
			ElenaiDodge.LOGGER.warn("ServerPlayerEntity was null when WeightMessageToServer was received");
		}

		ctx.enqueueWork(() -> processMessage(message, sendingPlayer));
	}
	
	public static void onMessageReceived(final DodgeMessageToServer message,
			Supplier<NetworkEvent.Context> ctxSupplier) {
		NetworkEvent.Context ctx = ctxSupplier.get();
		LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
		ctx.setPacketHandled(true);

		if (sideReceived != LogicalSide.SERVER) {
			ElenaiDodge.LOGGER
					.warn("DodgeMessageToServer received on wrong side:" + ctx.getDirection().getReceptionSide());
			return;
		}
		if (!message.isMessageValid()) {
			ElenaiDodge.LOGGER.warn("DodgeMessageToServer was invalid" + message.toString());
			return;
		}

		final ServerPlayerEntity sendingPlayer = ctx.getSender();
		if (sendingPlayer == null) {
			ElenaiDodge.LOGGER.warn("ServerPlayerEntity was null when DodgeMessageToServer was received");
		}

		ctx.enqueueWork(() -> processMessage(message, sendingPlayer));
	}
	
	public static void onMessageReceived(final DodgeRegenMessageToServer message,
			Supplier<NetworkEvent.Context> ctxSupplier) {
		NetworkEvent.Context ctx = ctxSupplier.get();
		LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
		ctx.setPacketHandled(true);

		if (sideReceived != LogicalSide.SERVER) {
			ElenaiDodge.LOGGER
					.warn("DodgeRegenMessageToServer received on wrong side:" + ctx.getDirection().getReceptionSide());
			return;
		}
		if (!message.isMessageValid()) {
			ElenaiDodge.LOGGER.warn("DodgeRegenMessageToServer was invalid" + message.toString());
			return;
		}

		final ServerPlayerEntity sendingPlayer = ctx.getSender();
		if (sendingPlayer == null) {
			ElenaiDodge.LOGGER.warn("ServerPlayerEntity was null when DodgeRegenMessageToServer was received");
		}

		ctx.enqueueWork(() -> processMessage(message, sendingPlayer));
	}
	
	public static void onMessageReceived(final ThirstMessageToServer message,
			Supplier<NetworkEvent.Context> ctxSupplier) {
		NetworkEvent.Context ctx = ctxSupplier.get();
		LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
		ctx.setPacketHandled(true);

		if (sideReceived != LogicalSide.SERVER) {
			ElenaiDodge.LOGGER
					.warn("ThirstMessageToServer received on wrong side:" + ctx.getDirection().getReceptionSide());
			return;
		}
		if (!message.isMessageValid()) {
			ElenaiDodge.LOGGER.warn("ThirstMessageToServer was invalid" + message.toString());
			return;
		}

		final ServerPlayerEntity sendingPlayer = ctx.getSender();
		if (sendingPlayer == null) {
			ElenaiDodge.LOGGER.warn("ServerPlayerEntity was null when ThirstMessageToServer was received");
		}

		ctx.enqueueWork(() -> processMessage(message, sendingPlayer));
	}

	static void processMessage(WeightMessageToServer message, ServerPlayerEntity sendingPlayer) {
		sendingPlayer.getCapability(WeightProvider.WEIGHT_CAP).ifPresent(w -> {
			w.set(message.getWeight());
		});
		return;
	}
	
	static void processMessage(DodgeMessageToServer message, ServerPlayerEntity sendingPlayer) {
		DodgeEvent.ServerDodgeEvent event = new ServerDodgeEvent(Direction.valueOf(message.getDirection()), Utils.calculateForce(sendingPlayer), sendingPlayer);
		if(!MinecraftForge.EVENT_BUS.post(event)) {
			Utils.handleDodge(Direction.valueOf(message.getDirection()), event, sendingPlayer);
		}
		return;
	}
	
	static void processMessage(DodgeRegenMessageToServer message, ServerPlayerEntity sendingPlayer) {
		sendingPlayer.getCapability(DodgesProvider.DODGES_CAP).ifPresent(d -> {
			if(d.getDodges() < 20) {
				d.set(message.getDodges());
				}
	});
		return;
	}
	
	static void processMessage(ThirstMessageToServer message, ServerPlayerEntity sendingPlayer) {
		if(ModList.get().isLoaded("toughasnails")) {
		ToughAsNailsServer tan = new ToughAsNailsServer(sendingPlayer);
		tan.addThirst();
		}
		return;
	}

	public static boolean isThisProtocolAcceptedByServer(String protocolVersion) {
		return NetworkHandler.MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
	}

}
