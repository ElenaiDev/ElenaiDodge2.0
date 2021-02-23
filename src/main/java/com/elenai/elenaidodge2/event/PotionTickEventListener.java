package com.elenai.elenaidodge2.event;

import com.elenai.elenaidodge2.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge2.config.ConfigHandler;
import com.elenai.elenaidodge2.list.PotionList;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PotionTickEventListener {

	@SubscribeEvent
	public void onPotionTickEvent(TickEvent.PlayerTickEvent event) {

		if (event.phase == TickEvent.Phase.END) {

			if (event.player.isPotionActive(PotionList.ENDURANCE_EFFECT)
					&& event.player.isPotionActive(PotionList.WEIGHT_EFFECT) && !event.player.world.isRemote) {
				event.player.removePotionEffect(PotionList.ENDURANCE_EFFECT);
				if(ConfigHandler.message) {
				event.player.sendStatusMessage(new StringTextComponent(TextFormatting.GRAY + "Your " + TextFormatting.GREEN
						+ "Endurance" + TextFormatting.GRAY + " is overwhelmed by a crushing " + TextFormatting.RED
						+ "Weight" + TextFormatting.GRAY + "!" + TextFormatting.RESET), true);
				}
			}

			if (event.player.isPotionActive(PotionList.FEATHERS_EFFECT)) {
				if (!event.player.world.isRemote) {
					event.player.getCapability(AbsorptionProvider.ABSORPTION_CAP).ifPresent(a -> {
						if (a.getAbsorption() <= 0) {
							event.player.removePotionEffect(PotionList.FEATHERS_EFFECT);
						}
					});
				}
			}
		}
	}
}
