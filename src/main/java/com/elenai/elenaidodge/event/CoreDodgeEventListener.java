package com.elenai.elenaidodge.event;

import com.elenai.elenaidodge.network.NetworkHandler;
import com.elenai.elenaidodge.network.message.server.DodgeMessageToServer;
import com.elenai.elenaidodge.util.ClientStorage;
import com.elenai.elenaidodge.util.DodgeEvent.RequestDodgeEvent;

import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CoreDodgeEventListener {
	
	/*
	 * The CORE Dodge event listener for the Client.
	 * 1) This class notifies the Server that a Dodge event has been performed.
	 * 2) This class notifies the Server of the player's feathers.
	 * 3) This class sets the hardcoded client cooldown to 8.
	 * Essentially, this is the core of the mod, no smokes and mirrors.
	 */

	@SubscribeEvent
	public void onClientDodge(RequestDodgeEvent event) {
		NetworkHandler.simpleChannel.sendToServer(new DodgeMessageToServer(event.getDirection().toString()));
		ClientStorage.cooldown = 8;
	}
}
