package com.elenai.elenaidodge2.event;

import com.elenai.elenaidodge2.api.CheckFeatherEvent;
import com.elenai.elenaidodge2.api.DodgeEvent.ServerDodgeEvent;
import com.elenai.elenaidodge2.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge2.capability.dodges.DodgesProvider;
import com.elenai.elenaidodge2.capability.weight.WeightProvider;
import com.elenai.elenaidodge2.config.ConfigHandler;
import com.elenai.elenaidodge2.list.PotionList;
import com.elenai.elenaidodge2.util.Utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.MinecraftForge;
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
		
		CheckFeatherEvent e = new CheckFeatherEvent((ServerPlayerEntity) player);
		if(!MinecraftForge.EVENT_BUS.post(e)) {
		player.getCapability(AbsorptionProvider.ABSORPTION_CAP).ifPresent(a -> {
			player.getCapability(DodgesProvider.DODGES_CAP).ifPresent(d -> {
				if(d.getDodges() + a.getAbsorption() < ConfigHandler.cost) {
					Utils.cancelledByFeathers(player);
					event.setCanceled(true);
				}
				player.getCapability(WeightProvider.WEIGHT_CAP).ifPresent(w -> {
					if((w.getWeight() > 0) && (d.getDodges() - ConfigHandler.cost < w.getWeight() && a.getAbsorption() - ConfigHandler.cost < 0)) {
						Utils.cancelledByFeathers(player);
						event.setCanceled(true);
					}
				});
				
			});

		});}
		
	}
}
