package com.elenai.elenaidodge2.event;

import com.elenai.elenaidodge2.config.ConfigHandler;
import com.elenai.elenaidodge2.gui.DodgeStep;
import com.elenai.elenaidodge2.integration.ToughAsNailsClient;
import com.elenai.elenaidodge2.network.NetworkHandler;
import com.elenai.elenaidodge2.network.message.server.DodgeRegenMessageToServer;
import com.elenai.elenaidodge2.network.message.server.ThirstMessageToServer;
import com.elenai.elenaidodge2.util.ClientStorage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

public class ClientTickEventListener {
	
	public static int regen = ClientStorage.regenSpeed;

	public static int pauseLen = 7;
	public static int animationLen = 4;

	public static int animation = animationLen;
	public static int pause = pauseLen;
	public static int flashes = 2;
	
	@SuppressWarnings("resource")
	@SubscribeEvent
	public void onPlayerTickClient(TickEvent.ClientTickEvent event) {
		
		ClientPlayerEntity player = Minecraft.getInstance().player;
		if(player != null) {

		// CLIENT
		if (event.phase == TickEvent.Phase.END && player.world.isRemote && !Minecraft.getInstance().isGamePaused()) {

			// Tutorial
			if (!ClientStorage.shownTutorial && ConfigHandler.tutorial) {
				DodgeStep.show();
				ClientStorage.shownTutorial = true;
			}

			if (ClientStorage.tutorialDodges == 1) {
				DodgeStep.hide();
			}

			// COOLDOWN
			if (ClientStorage.cooldown > 0) {
				ClientStorage.cooldown--;
			}

			// ANIMATION
			if (flashes < 2 && pause <= 0) {
				pause = pauseLen;
				animation = animationLen;
				ClientStorage.healing = true;
				flashes++;
			}

			if (pause > 0) {
				pause--;
			}

			if (animation > 0) {
				animation--;
			}

			if (ClientStorage.healing && animation == 0) {
				ClientStorage.healing = false;
			}

			
			// REGENERATION LOGIC
			if (ClientStorage.dodges < 20) {
				
				if (regen > 0 && ModList.get().isLoaded("toughasnails")) {
					if (ToughAsNailsClient.highThirst()) {
						regen--;
					}
				} else
				if (regen > 0) {
					regen--;
				}
				else if (regen <= 0) {
					
					if(ModList.get().isLoaded("toughasnails")) {
					NetworkHandler.simpleChannel.sendToServer(new ThirstMessageToServer());
					}
						
						ClientStorage.dodges++;
						NetworkHandler.simpleChannel.sendToServer(new DodgeRegenMessageToServer(ClientStorage.dodges));
						flashes = 0;
						regen = ClientStorage.regenSpeed;

				}
				
			}
		}
	}
	}

}
