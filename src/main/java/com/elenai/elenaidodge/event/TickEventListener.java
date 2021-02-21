package com.elenai.elenaidodge.event;

import com.elenai.elenaidodge.capability.invincibility.IInvincibility;
import com.elenai.elenaidodge.capability.invincibility.InvincibilityProvider;
import com.elenai.elenaidodge.capability.particles.IParticles;
import com.elenai.elenaidodge.capability.particles.ParticlesProvider;
import com.elenai.elenaidodge.network.PacketHandler;
import com.elenai.elenaidodge.network.message.CParticleMessage;
import com.elenai.elenaidodge.util.PatronRewardHandler;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class TickEventListener {

	@SubscribeEvent
	public void onClientPlayerTick(TickEvent.PlayerTickEvent event) {

		// SERVER
		if (event.phase == TickEvent.Phase.END && !event.player.world.isRemote) {
			IInvincibility i = event.player.getCapability(InvincibilityProvider.INVINCIBILITY_CAP, null);
			if (i.getInvincibility() > 0) {
				i.set(i.getInvincibility() - 1);
			}
			IParticles p = event.player.getCapability(ParticlesProvider.PARTICLES_CAP, null);
			if (p.getParticles() > 0) {
				p.set(p.getParticles() - 1);
				PacketHandler.instance.sendTo(new CParticleMessage(PatronRewardHandler.getTier(event.player),
						event.player.posX, event.player.posY, event.player.posZ), (EntityPlayerMP) event.player);
				PacketHandler.instance.sendToAllTracking(new CParticleMessage(PatronRewardHandler.getTier(event.player),
						event.player.posX, event.player.posY, event.player.posZ), (EntityPlayerMP) event.player);
			}
		}
	}
}