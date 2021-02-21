package com.elenai.elenaidodge.event;

import com.elenai.elenaidodge.ModConfig;
import com.elenai.elenaidodge.gui.DodgeStep;
import com.elenai.elenaidodge.integration.ToughAsNailsClient;
import com.elenai.elenaidodge.network.PacketHandler;
import com.elenai.elenaidodge.network.message.SDodgeRegenMessage;
import com.elenai.elenaidodge.network.message.SThirstMessage;
import com.elenai.elenaidodge.util.ClientStorage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ClientTickEventListener {

	public static int regen = ClientStorage.regenSpeed;

	public static int pauseLen = 7;
	public static int animationLen = 4;

	public static int animation = animationLen;
	public static int pause = pauseLen;
	public static int flashes = 2;
	
	@SubscribeEvent
	public void onPlayerClientTick(TickEvent.ClientTickEvent event) {

		EntityPlayerSP player = Minecraft.getMinecraft().player;
		if(player != null) {
		// CLIENT
				if (event.phase == TickEvent.Phase.END && player.world.isRemote && !Minecraft.getMinecraft().isGamePaused()) {

					// Tutorial
					if (!ClientStorage.shownTutorial && ModConfig.client.hud.tutorial) {
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
						
						if (regen > 0 && Loader.isModLoaded("toughasnails")) {
							if (ToughAsNailsClient.highThirst()) {
								regen--;
							}
						} else
						
							if (regen > 0) {
							regen--;
						} 
						else if (regen <= 0) {
							
							if(Loader.isModLoaded("toughasnails")) {
								PacketHandler.instance.sendToServer(new SThirstMessage());
								}
							
							ClientStorage.dodges++;
							PacketHandler.instance.sendToServer(new SDodgeRegenMessage(ClientStorage.dodges));
							flashes = 0;
							regen = ClientStorage.regenSpeed;
						}
					}
				}
		}
	}

}
