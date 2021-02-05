package com.elenai.elenaidodge.event;

import com.elenai.elenaidodge.capability.invincibility.InvincibilityProvider;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class InvincibilityEventListener {
	
	@SubscribeEvent
	public void onHit(LivingAttackEvent event) {
		if (event.getEntityLiving() instanceof PlayerEntity && (event.getSource().isProjectile()
				|| event.getSource().damageType.contains("player") || event.getSource().damageType.contains("mob"))) {
			event.getEntityLiving()
					.getCapability(InvincibilityProvider.INVINCIBILITY_CAP).ifPresent(i -> {
						if (i.getInvincibility() > 0) {
							event.setCanceled(true);
						}
					});
		}
	}

}
