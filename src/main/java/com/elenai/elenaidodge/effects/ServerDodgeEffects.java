package com.elenai.elenaidodge.effects;

import com.elenai.elenaidodge.ModConfig;
import com.elenai.elenaidodge.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge.capability.absorption.IAbsorption;
import com.elenai.elenaidodge.capability.dodges.DodgesProvider;
import com.elenai.elenaidodge.capability.dodges.IDodges;
import com.elenai.elenaidodge.capability.invincibility.IInvincibility;
import com.elenai.elenaidodge.capability.invincibility.InvincibilityProvider;
import com.elenai.elenaidodge.capability.particles.IParticles;
import com.elenai.elenaidodge.capability.particles.ParticlesProvider;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;

public class ServerDodgeEffects {

	/**
	 * Runs the Server Dodge Effects
	 * 
	 * @side Server
	 */
	public static void run(EntityPlayerMP player) {

		player.getEntityWorld().playSound(player, player.getPosition(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP,
				SoundCategory.PLAYERS, 0.8f, 4f);

		IInvincibility i = player.getCapability(InvincibilityProvider.INVINCIBILITY_CAP, null);
		i.set(ModConfig.common.balance.invincibilityTicks);
		
		IParticles p = player.getCapability(ParticlesProvider.PARTICLES_CAP, null);
		p.set(10);
		
		if (!player.isCreative() && !player.isSpectator()) {
			player.getFoodStats().addExhaustion((float) ModConfig.common.balance.exhaustion);
		}

		IAbsorption a = player.getCapability(AbsorptionProvider.ABSORPTION_CAP, null);
		IDodges d = player.getCapability(DodgesProvider.DODGES_CAP, null);
		if (!player.isCreative() && !player.isSpectator()) {
			if (a.getAbsorption() <= 0) {
				d.set(d.getDodges() - ModConfig.common.feathers.cost);
			} else if (a.getAbsorption() - ModConfig.common.feathers.cost >= 0) {
				a.set(a.getAbsorption() - ModConfig.common.feathers.cost);
			} else {
				d.increase(a.getAbsorption() - ModConfig.common.feathers.cost);
				a.set(0);
			}
		}
	}
}
