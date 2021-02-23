package com.elenai.elenaidodge2.capability;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge2.capability.absorptionbool.AbsorptionBoolProvider;
import com.elenai.elenaidodge2.capability.dodges.DodgesProvider;
import com.elenai.elenaidodge2.capability.invincibility.InvincibilityProvider;
import com.elenai.elenaidodge2.capability.joined.IJoined;
import com.elenai.elenaidodge2.capability.joined.JoinedProvider;
import com.elenai.elenaidodge2.capability.particles.ParticlesProvider;
import com.elenai.elenaidodge2.capability.weight.WeightProvider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
		if (event.getObject() instanceof EntityPlayer) {
			if (!event.getObject().hasCapability(DodgesProvider.DODGES_CAP, null)) {
				event.addCapability(DODGES_CAP, new DodgesProvider());
			}
			if (!event.getObject().hasCapability(JoinedProvider.JOINED_CAP, null)) {
				event.addCapability(JOINED_CAP, new JoinedProvider());
			}
			if (!event.getObject().hasCapability(WeightProvider.WEIGHT_CAP, null)) {
				event.addCapability(WEIGHT_CAP, new WeightProvider());
			}
			if (!event.getObject().hasCapability(AbsorptionProvider.ABSORPTION_CAP, null)) {
				event.addCapability(ABSORPTION_CAP, new AbsorptionProvider());
			}
			if (!event.getObject().hasCapability(AbsorptionBoolProvider.ABSORPTION_BOOL_CAP, null)) {
				event.addCapability(ABSORPTION_BOOL_CAP, new AbsorptionBoolProvider());
			}
			if (!event.getObject().hasCapability(InvincibilityProvider.INVINCIBILITY_CAP, null)) {
				event.addCapability(INVINCIBILITY_CAP, new InvincibilityProvider());
			}
			if (!event.getObject().hasCapability(ParticlesProvider.PARTICLES_CAP, null)) {
				event.addCapability(PARTICLES_CAP, new ParticlesProvider());
			}
		}
	}

	@SubscribeEvent
	public void onPlayerCloned(PlayerEvent.Clone event) {
		EntityPlayer player = event.getEntityPlayer();

		IJoined joined = player.getCapability(JoinedProvider.JOINED_CAP, null);
		IJoined oldJoined = event.getOriginal().getCapability(JoinedProvider.JOINED_CAP, null);
		joined.set(oldJoined.hasJoined());


	}

}
