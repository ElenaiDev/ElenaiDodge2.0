package com.elenai.elenaidodge.effects;

import com.elenai.elenaidodge.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge.capability.dodges.DodgesProvider;
import com.elenai.elenaidodge.gui.DodgeStep;
import com.elenai.elenaidodge.network.NetworkHandler;
import com.elenai.elenaidodge.network.message.client.DodgeEffectsMessageToClient;
import com.elenai.elenaidodge.util.ClientStorage;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.network.PacketDistributor;

public class ClientDodgeEffects {

	/**
	 * Sends the Client Dodge Effects to the Client
	 * @param player
	 * @side Server
	 */
	public static void send(PlayerEntity player) {
		player.getCapability(DodgesProvider.DODGES_CAP).ifPresent(d -> {
			player.getCapability(AbsorptionProvider.ABSORPTION_CAP).ifPresent(a -> {
				NetworkHandler.simpleChannel.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new DodgeEffectsMessageToClient(d.getDodges(), a.getAbsorption()));
			});
		});
	}
	
	/**
	 * Runs the Client Dodge Effects
	 * @param dodges
	 * @param dodgeCost
	 * @side Client
	 */
	@SuppressWarnings("resource")
	public static void run(int dodges, int absorption) {
		PlayerEntity player = Minecraft.getInstance().player;
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
