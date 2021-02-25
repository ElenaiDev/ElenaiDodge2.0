package com.elenai.elenaidodge2.event;

import com.elenai.elenaidodge2.ModConfig;
import com.elenai.elenaidodge2.api.DodgeEvent.ServerDodgeEvent;
import com.elenai.elenaidodge2.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge2.capability.absorption.IAbsorption;
import com.elenai.elenaidodge2.capability.dodges.DodgesProvider;
import com.elenai.elenaidodge2.capability.dodges.IDodges;
import com.elenai.elenaidodge2.capability.weight.IWeight;
import com.elenai.elenaidodge2.capability.weight.WeightProvider;
import com.elenai.elenaidodge2.init.PotionInit;
import com.elenai.elenaidodge2.util.Utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ServerDodgeEventListener {
	
	@SubscribeEvent
	public void onServerDodge(ServerDodgeEvent event) {
		
		EntityPlayer player = event.getPlayer();
		
		// Condition Checks
		if (!player.onGround && !ModConfig.common.balance.enableWhilstAirborne) {
			event.setCanceled(true);
		}
		
		if(player.getFoodStats().getFoodLevel() <= ModConfig.common.balance.hunger) {
			event.setCanceled(true);
		}
		
		if (player.isSneaking() && !ModConfig.common.balance.enableWhilstSneaking) {
			event.setCanceled(true);
		}
		
		if(player.isActiveItemStackBlocking() && !ModConfig.common.balance.enableWhilstBlocking) {
			event.setCanceled(true);
		}
		
		IAbsorption a = player.getCapability(AbsorptionProvider.ABSORPTION_CAP, null);
		IDodges d = player.getCapability(DodgesProvider.DODGES_CAP, null);
		if(d.getDodges() + a.getAbsorption() < ModConfig.common.feathers.cost) {
			Utils.cancelledByFeathers(player);
			event.setCanceled(true);
		}
		
		if(player.isRiding()) {
			event.setCanceled(true);
		}
		
		for(String i : ModConfig.common.balance.potions) {
			player.getActivePotionEffects().forEach(p -> {
				if(p.getPotion().getRegistryName().equals(new ResourceLocation(i))) {
					event.setCanceled(true);
				}
			});
		}

		// Alterations
		if(player.isPotionActive(MobEffects.SLOWNESS)) {
			event.setForce(event.getForce()/(player.getActivePotionEffect(MobEffects.SLOWNESS).getAmplifier()+2));
			if(player.getActivePotionEffect(MobEffects.SLOWNESS).getAmplifier()>5) { event.setCanceled(true);}
		}
	
		if(player.dimension == -1 && ModConfig.common.misc.nether) {
			event.setForce(event.getForce()*1.25);
		}
		
		if(player.dimension == 1 && ModConfig.common.misc.end) {
			event.setForce(event.getForce()/1.25);
		}
		
		if(player.isPotionActive(PotionInit.FORCEFUL_EFFECT)) {
			event.setForce((event.getForce() + (player.getActivePotionEffect(PotionInit.FORCEFUL_EFFECT).getAmplifier()+0.3)/2));
		}

		if(player.isPotionActive(PotionInit.FEEBLE_EFFECT)) {
			event.setForce((event.getForce()/(player.getActivePotionEffect(PotionInit.FEEBLE_EFFECT).getAmplifier()+2)));
		}

		// Put this after all force alterations!
		if (event.getForce() <= 0) {
			event.setCanceled(true);
		}
		
		IWeight w = player.getCapability(WeightProvider.WEIGHT_CAP, null);
		if((w.getWeight() > 0) && (d.getDodges() - ModConfig.common.feathers.cost < w.getWeight() && a.getAbsorption() - ModConfig.common.feathers.cost < 0)) {
			Utils.cancelledByFeathers(player);
			event.setCanceled(true);
		}
	}
}
