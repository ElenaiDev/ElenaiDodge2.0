package com.elenai.elenaidodge.event;

import com.elenai.elenaidodge.capability.invincibility.IInvincibility;
import com.elenai.elenaidodge.capability.invincibility.InvincibilityProvider;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InvincibilityEventListener {
	
	@SubscribeEvent
	public void onHit(LivingAttackEvent event) {
		if (event.getEntityLiving() instanceof EntityPlayer && (event.getSource().isProjectile()
				|| event.getSource().damageType.contains("player") || event.getSource().damageType.contains("mob"))) {
			IInvincibility i = event.getEntityLiving()
					.getCapability(InvincibilityProvider.INVINCIBILITY_CAP, null);
			if (i.getInvincibility() > 0) {
				event.setCanceled(true);
			}
		}
	}

}
