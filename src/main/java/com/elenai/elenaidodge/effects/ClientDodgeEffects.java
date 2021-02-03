package com.elenai.elenaidodge.effects;

import com.elenai.elenaidodge.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge.capability.absorption.IAbsorption;
import com.elenai.elenaidodge.capability.dodges.DodgesProvider;
import com.elenai.elenaidodge.capability.dodges.IDodges;
import com.elenai.elenaidodge.gui.DodgeStep;
import com.elenai.elenaidodge.network.PacketHandler;
import com.elenai.elenaidodge.network.message.CDodgeEffectsMessage;
import com.elenai.elenaidodge.util.ClientStorage;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;

public class ClientDodgeEffects {

	/**
	 * Sends the Client Dodge Effects to the Client
	 * @param player
	 * @side Server
	 */
	public static void send(EntityPlayer player) {
		IDodges d = player.getCapability(DodgesProvider.DODGES_CAP, null);
		IAbsorption a = player.getCapability(AbsorptionProvider.ABSORPTION_CAP, null);
		PacketHandler.instance.sendTo(new CDodgeEffectsMessage(d.getDodges(), a.getAbsorption()), (EntityPlayerMP) player);
	}
	
	/**
	 * Runs the Client Dodge Effects
	 * @param dodges
	 * @param dodgeCost
	 * @side Client
	 */
	public static void run(int dodges, int absorption) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		ClientStorage.absorption = absorption;
		ClientStorage.dodges = dodges;
		player.getEntityWorld().playSound(player, player.getPosition(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP,
				SoundCategory.PLAYERS, 0.8f, 4f);
		if(ClientStorage.tutorialDodges < 1) {
		ClientStorage.tutorialDodges+=0.25;
		DodgeStep.moveToast.setProgress((float)ClientStorage.tutorialDodges);
		}
	}
	
}
