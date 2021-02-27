package com.elenai.elenaidodge2.effects;

import com.elenai.elenaidodge2.ModConfig;
import com.elenai.elenaidodge2.api.SpendFeatherEvent;
import com.elenai.elenaidodge2.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge2.capability.absorption.IAbsorption;
import com.elenai.elenaidodge2.capability.dodges.DodgesProvider;
import com.elenai.elenaidodge2.capability.dodges.IDodges;
import com.elenai.elenaidodge2.capability.invincibility.IInvincibility;
import com.elenai.elenaidodge2.capability.invincibility.InvincibilityProvider;
import com.elenai.elenaidodge2.capability.particles.IParticles;
import com.elenai.elenaidodge2.capability.particles.ParticlesProvider;
import com.elenai.elenaidodge2.network.PacketHandler;
import com.elenai.elenaidodge2.network.message.CParticleMessage;
import com.elenai.elenaidodge2.util.PatronRewardHandler;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.MinecraftForge;

public class ServerDodgeEffects {

	/**
	 * Runs the Server Dodge Effects
	 * 
	 * @side Server
	 */
	public static void run(EntityPlayerMP player) {

		player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP,
				SoundCategory.PLAYERS, 0.8f, 4f);

		IInvincibility i = player.getCapability(InvincibilityProvider.INVINCIBILITY_CAP, null);
		i.set(ModConfig.common.balance.invincibilityTicks);
		
		IParticles p = player.getCapability(ParticlesProvider.PARTICLES_CAP, null);
		p.set(10);
		
		if (!player.isCreative() && !player.isSpectator()) {
			player.getFoodStats().addExhaustion((float) ModConfig.common.balance.exhaustion);
		}
		
		if (ModConfig.common.misc.particles && PatronRewardHandler.getTier(player) <= 0) {
		PacketHandler.instance.sendTo(new CParticleMessage(PatronRewardHandler.getTier(player),
				player.posX, player.posY, player.posZ), (EntityPlayerMP) player);
		PacketHandler.instance.sendToAllTracking(new CParticleMessage(PatronRewardHandler.getTier(player),
				player.posX, player.posY, player.posZ), (EntityPlayerMP) player);
		}

		SpendFeatherEvent event = new SpendFeatherEvent(ModConfig.common.feathers.cost, player);
		if(!MinecraftForge.EVENT_BUS.post(event)) {
		IAbsorption a = player.getCapability(AbsorptionProvider.ABSORPTION_CAP, null);
		IDodges d = player.getCapability(DodgesProvider.DODGES_CAP, null);
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
		}
	}
}
