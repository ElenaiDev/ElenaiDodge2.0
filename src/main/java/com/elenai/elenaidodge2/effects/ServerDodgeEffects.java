package com.elenai.elenaidodge2.effects;

import com.elenai.elenaidodge2.api.SpendFeatherEvent;
import com.elenai.elenaidodge2.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge2.capability.dodges.DodgesProvider;
import com.elenai.elenaidodge2.capability.invincibility.InvincibilityProvider;
import com.elenai.elenaidodge2.capability.particles.ParticlesProvider;
import com.elenai.elenaidodge2.config.ConfigHandler;
import com.elenai.elenaidodge2.network.NetworkHandler;
import com.elenai.elenaidodge2.network.message.client.ParticleMessageToClient;
import com.elenai.elenaidodge2.util.PatronRewardHandler;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.PacketDistributor;

public class ServerDodgeEffects {

	/**
	 * Runs the Server Dodge Effects
	 * 
	 * @side Server
	 */
	public static void run(ServerPlayerEntity player) {

		player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP,
				SoundCategory.PLAYERS, 0.8f, 4f);

		player.getCapability(InvincibilityProvider.INVINCIBILITY_CAP).ifPresent(i -> {
			i.set(ConfigHandler.invincibilityTicks);
		});
		
		player.getCapability(ParticlesProvider.PARTICLES_CAP).ifPresent(p -> {
			p.set(10);
		});
		
		if (!player.isCreative() && !player.isSpectator()) {
			player.getFoodStats().addExhaustion((float) ConfigHandler.exhaustion);
		}
		
		if (ConfigHandler.enableParticles && PatronRewardHandler.getTier(player) <= 0) {
		NetworkHandler.simpleChannel.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> (ServerPlayerEntity) player), 
				new ParticleMessageToClient(PatronRewardHandler.getTier(player), player.getPosX(),
						player.getPosY(), player.getPosZ()));}

		SpendFeatherEvent event = new SpendFeatherEvent(ConfigHandler.cost, player);
		if(!MinecraftForge.EVENT_BUS.post(event)) {
			if(!player.isOnGround()) {
				event.setCost(ConfigHandler.airborneCost);
			}
		player.getCapability(AbsorptionProvider.ABSORPTION_CAP).ifPresent(a -> {
			player.getCapability(DodgesProvider.DODGES_CAP).ifPresent(d -> {
				if (!player.isCreative() && !player.isSpectator()) {
					if (a.getAbsorption() <= 0) {
						d.set(d.getDodges() - event.getCost());
					} else if (a.getAbsorption() - event.getCost() >= 0) {
						a.set(a.getAbsorption() - event.getCost());
					} else {
						d.increase(a.getAbsorption() - event.getCost());
						a.set(0);
					}
				}
			});
		});
		}
		
	}
}
