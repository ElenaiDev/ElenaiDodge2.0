package com.elenai.elenaidodge2.event;

import com.elenai.elenaidodge2.capability.invincibility.InvincibilityProvider;
import com.elenai.elenaidodge2.capability.particles.ParticlesProvider;
import com.elenai.elenaidodge2.network.NetworkHandler;
import com.elenai.elenaidodge2.network.message.client.ParticleMessageToClient;
import com.elenai.elenaidodge2.util.PatronRewardHandler;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.PacketDistributor;

public class TickEventListener {

	@SubscribeEvent
	public void onServerPlayerTick(TickEvent.PlayerTickEvent event) {

		// SERVER
		if (event.phase == TickEvent.Phase.END && !event.player.world.isRemote) {
			event.player.getCapability(InvincibilityProvider.INVINCIBILITY_CAP).ifPresent(i -> {
				if (i.getInvincibility() > 0) {
					i.set(i.getInvincibility() - 1);
				}
			});
			
			event.player.getCapability(ParticlesProvider.PARTICLES_CAP).ifPresent(p -> {
				if (p.getParticles() > 0) {
					p.set(p.getParticles() - 1);
					NetworkHandler.simpleChannel.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> (ServerPlayerEntity) event.player), 
							new ParticleMessageToClient(PatronRewardHandler.getTier(event.player), event.player.getPosX(),
									event.player.getPosY(), event.player.getPosZ()));
				}
			});
		}
	}
}