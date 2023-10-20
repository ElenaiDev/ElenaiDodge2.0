package com.elenai.elenaidodge2.event;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.capability.InvincibleCapability;
import com.elenai.elenaidodge2.capability.PlayerInvincibilityProvider;
import com.elenai.elenaidodge2.config.ED2CommonConfig;
import com.elenai.elenaidodge2.networking.ED2Messages;
import com.elenai.elenaidodge2.networking.messages.ConfigSyncSTCPacket;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ElenaiDodge2.MODID)
public class CommonEvents {

	@SubscribeEvent
	public static void playerTickEvent(PlayerTickEvent event) {
		if (event.side == LogicalSide.SERVER && event.phase == TickEvent.Phase.START) {
			event.player.getCapability(PlayerInvincibilityProvider.PLAYER_INVINCIBILITY).ifPresent(i -> {
				if (i.getInvincibility() > 0) {
					i.subInvincibility(1);
				}
			});
		}
	}

	@SubscribeEvent
	public static void onPlayerHit(LivingHurtEvent event) {
		if (event.getEntity() instanceof ServerPlayer player) {
			player.getCapability(PlayerInvincibilityProvider.PLAYER_INVINCIBILITY).ifPresent(i -> {
				if (i.getInvincibility() > 0 && (event.getSource() == DamageSource.DRAGON_BREATH
						|| event.getSource().getEntity() instanceof Mob || event.getSource().isProjectile()
						|| event.getSource().getEntity() instanceof ServerPlayer)) {
					event.setCanceled(true);
				}
			});
		}
	}

	@SubscribeEvent
	public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
		Level level = event.getLevel();
		if (!level.isClientSide && (event.getEntity() instanceof ServerPlayer player)) {
			ED2Messages.sendToPlayer(new ConfigSyncSTCPacket(ED2CommonConfig.DODGE_WHILST_AIRBORNE.get(),
					ED2CommonConfig.COOLDOWN_TIME.get(), ED2CommonConfig.DODGE_COST.get(), ED2CommonConfig.DODGE_STRENGTH.get(), ED2CommonConfig.DODGE_HEIGHT.get(), ED2CommonConfig.DODGE_COST_WHILST_AIRBORNE.get()), player);
		}
	}

	@SubscribeEvent
	public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof Player) {
			if (!event.getObject().getCapability(PlayerInvincibilityProvider.PLAYER_INVINCIBILITY).isPresent()) {
				event.addCapability(new ResourceLocation(ElenaiDodge2.MODID, "properties"),
						new PlayerInvincibilityProvider());
			}
		}
	}

	// TODO: Repair this
	@SubscribeEvent
	public static void onPlayerCloned(PlayerEvent.Clone event) {
		if (!event.isWasDeath()) {
			event.getOriginal().getCapability(PlayerInvincibilityProvider.PLAYER_INVINCIBILITY).ifPresent(oldStore -> {
				event.getOriginal().getCapability(PlayerInvincibilityProvider.PLAYER_INVINCIBILITY)
						.ifPresent(newStore -> {
							newStore.copyFrom(oldStore);
						});
			});
		}
	}

	@SubscribeEvent
	public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		event.register(InvincibleCapability.class);
	}

}