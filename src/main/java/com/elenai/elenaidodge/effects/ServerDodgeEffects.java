package com.elenai.elenaidodge.effects;

import com.elenai.elenaidodge.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge.capability.dodges.DodgesProvider;
import com.elenai.elenaidodge.capability.invincibility.InvincibilityProvider;
import com.elenai.elenaidodge.capability.particles.ParticlesProvider;
import com.elenai.elenaidodge.config.ConfigHandler;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;

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

		player.getCapability(AbsorptionProvider.ABSORPTION_CAP).ifPresent(a -> {
			player.getCapability(DodgesProvider.DODGES_CAP).ifPresent(d -> {
				if (!player.isCreative() && !player.isSpectator()) {
					if (a.getAbsorption() <= 0) {
						d.set(d.getDodges() - ConfigHandler.cost);
					} else if (a.getAbsorption() - ConfigHandler.cost >= 0) {
						a.set(a.getAbsorption() - ConfigHandler.cost);
					} else {
						d.increase(a.getAbsorption() - ConfigHandler.cost);
						a.set(0);
					}
				}
			});
		});
		
	}
}
