package com.elenai.elenaidodge2.event;

import com.elenai.elenaidodge2.ModConfig;
import com.elenai.elenaidodge2.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge2.capability.absorption.IAbsorption;
import com.elenai.elenaidodge2.init.PotionInit;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class PotionTickEventListener {

	@SubscribeEvent
	public void onPotionTickEvent(TickEvent.PlayerTickEvent event) {

		if (event.phase == TickEvent.Phase.END) {

			if (event.player.isPotionActive(PotionInit.ENDURANCE_EFFECT)
					&& event.player.isPotionActive(PotionInit.WEIGHT_EFFECT) && !event.player.world.isRemote) {
				event.player.removePotionEffect(PotionInit.ENDURANCE_EFFECT);
				if(ModConfig.common.misc.message) {
				event.player.sendMessage(new TextComponentString(TextFormatting.GRAY + "Your " + TextFormatting.GREEN
						+ "Endurance" + TextFormatting.GRAY + " is overwhelmed by a crushing " + TextFormatting.RED
						+ "Weight" + TextFormatting.GRAY + "!" + TextFormatting.RESET));
				}
			}

			if (event.player.isPotionActive(PotionInit.FEATHERS_EFFECT)) {
				if (!event.player.world.isRemote) {
					IAbsorption a = event.player.getCapability(AbsorptionProvider.ABSORPTION_CAP, null);
					if (a.getAbsorption() <= 0) {
						event.player.removePotionEffect(PotionInit.FEATHERS_EFFECT);
					}
				}
			}
		}
	}
}
