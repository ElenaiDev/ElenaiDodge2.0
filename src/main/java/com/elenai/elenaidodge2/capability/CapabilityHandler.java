package com.elenai.elenaidodge2.capability;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge2.capability.absorptionbool.AbsorptionBoolProvider;
import com.elenai.elenaidodge2.capability.dodges.DodgesProvider;
import com.elenai.elenaidodge2.capability.invincibility.InvincibilityProvider;
import com.elenai.elenaidodge2.capability.joined.JoinedProvider;
import com.elenai.elenaidodge2.capability.particles.ParticlesProvider;
import com.elenai.elenaidodge2.capability.weight.WeightProvider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityHandler {

	public static final ResourceLocation DODGES_CAP = new ResourceLocation(ElenaiDodge2.MODID, "dodges");
	public static final ResourceLocation JOINED_CAP = new ResourceLocation(ElenaiDodge2.MODID, "joined");
	public static final ResourceLocation WEIGHT_CAP = new ResourceLocation(ElenaiDodge2.MODID, "weight");
	public static final ResourceLocation ABSORPTION_CAP = new ResourceLocation(ElenaiDodge2.MODID, "absorption");
	public static final ResourceLocation ABSORPTION_BOOL_CAP = new ResourceLocation(ElenaiDodge2.MODID, "absorption_bool");
	public static final ResourceLocation INVINCIBILITY_CAP = new ResourceLocation(ElenaiDodge2.MODID, "invincibility");
	public static final ResourceLocation PARTICLES_CAP = new ResourceLocation(ElenaiDodge2.MODID, "particles");

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
