package com.elenai.elenaidodge.event;

import com.elenai.elenaidodge.api.DodgeEvent.ServerDodgeEvent;
import com.elenai.elenaidodge.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge.capability.dodges.DodgesProvider;
import com.elenai.elenaidodge.capability.weight.WeightProvider;
import com.elenai.elenaidodge.config.ConfigHandler;
import com.elenai.elenaidodge.list.PotionList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ServerDodgeEventListener {
	
	@SubscribeEvent
	public void onServerDodge(ServerDodgeEvent event) {
		
		PlayerEntity player = event.getPlayer();
		
		// Condition Checks
		if (!player.isOnGround() && !ConfigHandler.enableWhilstAirborne) {
			event.setCanceled(true);
		}
		
		if(player.getFoodStats().getFoodLevel() <= ConfigHandler.hunger) {
			event.setCanceled(true);
		}
		
		if (player.isSneaking() && !ConfigHandler.enableWhilstSneaking) {
			event.setCanceled(true);
		}
		
		if(player.isActiveItemStackBlocking() && !ConfigHandler.enableWhilstBlocking) {
			event.setCanceled(true);
		}
		
		if(player.getRidingEntity() != null) {
			event.setCanceled(true);
		}
		
		for(String i : ConfigHandler.potions) {
			player.getActivePotionEffects().forEach(p -> {
				if(p.getPotion().getRegistryName().equals(new ResourceLocation(i))) {
					event.setCanceled(true);
				}
			});
		}

		// Alterations
		if(player.isPotionActive(Effects.SLOWNESS)) {
			event.setForce(event.getForce()/(player.getActivePotionEffect(Effects.SLOWNESS).getAmplifier()+2));
			if(player.getActivePotionEffect(Effects.SLOWNESS).getAmplifier()>5) { event.setCanceled(true);}
		}
	
		if(player.getEntityWorld().getDimensionKey().equals(DimensionType.THE_NETHER) && ConfigHandler.nether) {
			event.setForce(event.getForce()*1.25);
		}
		
		if(player.getEntityWorld().getDimensionKey().equals(DimensionType.THE_END) && ConfigHandler.end) {
			event.setForce(event.getForce()/1.25);
		}
		
		if(player.isPotionActive(PotionList.FORCEFUL_EFFECT)) {
			event.setForce((event.getForce() + (player.getActivePotionEffect(PotionList.FORCEFUL_EFFECT).getAmplifier()+0.3)/2));
		}

		if(player.isPotionActive(PotionList.FEEBLE_EFFECT)) {
			event.setForce((event.getForce()/(player.getActivePotionEffect(PotionList.FEEBLE_EFFECT).getAmplifier()+2)));
		}

		// Put this after all force alterations!
		if (event.getForce() <= 0) {
			event.setCanceled(true);
		}
		player.getCapability(AbsorptionProvider.ABSORPTION_CAP).ifPresent(a -> {
			player.getCapability(DodgesProvider.DODGES_CAP).ifPresent(d -> {
				if(d.getDodges() + a.getAbsorption() < ConfigHandler.cost) {
					event.setCanceled(true);
				}
				player.getCapability(WeightProvider.WEIGHT_CAP).ifPresent(w -> {
					if((w.getWeight() > 0) && (d.getDodges() - ConfigHandler.cost < w.getWeight() && a.getAbsorption() - ConfigHandler.cost < 0)) {
						event.setCanceled(true);
					}
				});
				
			});

		});
		
	}
}
