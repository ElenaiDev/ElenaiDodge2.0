package com.elenai.elenaidodge.capability;

import com.elenai.elenaidodge.ElenaiDodge;
import com.elenai.elenaidodge.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge.capability.absorptionbool.AbsorptionBoolProvider;
import com.elenai.elenaidodge.capability.dodges.DodgesProvider;
import com.elenai.elenaidodge.capability.invincibility.WeightProvider;
import com.elenai.elenaidodge.capability.joined.JoinedProvider;
import com.elenai.elenaidodge.capability.particles.ParticlesProvider;
import com.elenai.elenaidodge.capability.weight.InvincibilityProvider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityHandler {

	public static final ResourceLocation DODGES_CAP = new ResourceLocation(ElenaiDodge.MODID, "dodges");
	public static final ResourceLocation JOINED_CAP = new ResourceLocation(ElenaiDodge.MODID, "joined");
	public static final ResourceLocation WEIGHT_CAP = new ResourceLocation(ElenaiDodge.MODID, "weight");
	public static final ResourceLocation ABSORPTION_CAP = new ResourceLocation(ElenaiDodge.MODID, "absorption");
	public static final ResourceLocation ABSORPTION_BOOL_CAP = new ResourceLocation(ElenaiDodge.MODID, "absorption_bool");
	public static final ResourceLocation INVINCIBILITY_CAP = new ResourceLocation(ElenaiDodge.MODID, "invincibility");
	public static final ResourceLocation PARTICLES_CAP = new ResourceLocation(ElenaiDodge.MODID, "particles");

	@SubscribeEvent
	public void onEntityConstructing(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof PlayerEntity) {

			event.addCapability(DODGES_CAP, new DodgesProvider());
			event.addCapability(JOINED_CAP, new JoinedProvider());
			event.addCapability(WEIGHT_CAP, new WeightProvider());
			event.addCapability(ABSORPTION_CAP, new AbsorptionProvider());
			event.addCapability(ABSORPTION_BOOL_CAP, new AbsorptionBoolProvider());
			event.addCapability(INVINCIBILITY_CAP, new InvincibilityProvider());
			event.addCapability(PARTICLES_CAP, new ParticlesProvider());

		}
	}

	@SubscribeEvent
	public void onPlayerCloned(PlayerEvent.Clone event) {
		
		event.getOriginal().getCapability(JoinedProvider.JOINED_CAP).ifPresent(oldJ ->{
            event.getPlayer().getCapability(JoinedProvider.JOINED_CAP).ifPresent(newJ ->{
                newJ.set(oldJ.getJoined());
            });
        });
	}
}
