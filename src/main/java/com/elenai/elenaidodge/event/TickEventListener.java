package com.elenai.elenaidodge.event;

import com.elenai.elenaidodge.capability.particles.ParticlesProvider;
import com.elenai.elenaidodge.capability.weight.InvincibilityProvider;
import com.elenai.elenaidodge.config.ConfigHandler;
import com.elenai.elenaidodge.gui.DodgeStep;
import com.elenai.elenaidodge.network.PacketHandler;
import com.elenai.elenaidodge.network.message.CParticleMessage;
import com.elenai.elenaidodge.network.message.SDodgeRegenMessage;
import com.elenai.elenaidodge.util.ClientStorage;
import com.elenai.elenaidodge.util.PatronRewardHandler;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.PacketDistributor;

public class TickEventListener {

	public static int regen = ClientStorage.regenSpeed;

	public static int pauseLen = 7;
	public static int animationLen = 4;

	public static int animation = animationLen;
	public static int pause = pauseLen;
	public static int flashes = 2;

	@SubscribeEvent
	public void onClientPlayerTick(TickEvent.PlayerTickEvent event) {

		// CLIENT
		if (event.phase == TickEvent.Phase.END && event.player.world.isRemote) {

			// Tutorial
			if (!ClientStorage.shownTutorial && ConfigHandler.tutorial) {
				DodgeStep.show();
				ClientStorage.shownTutorial = true;
			}

			if (ClientStorage.tutorialDodges == 1) {
				DodgeStep.hide();
			}

			// COOLDOWN
			if (ClientStorage.cooldown > 0) {
				ClientStorage.cooldown--;
			}

			// ANIMATION
			if (flashes < 2 && pause <= 0) {
				pause = pauseLen;
				animation = animationLen;
				ClientStorage.healing = true;
				flashes++;
			}

			if (pause > 0) {
				pause--;
			}

			if (animation > 0) {
				animation--;
			}

			if (ClientStorage.healing && animation == 0) {
				ClientStorage.healing = false;
			}

			if (ClientStorage.dodges < 20) {
				if (regen > 0) {
					regen--;
				} else if (regen <= 0) {
					ClientStorage.dodges++;
					PacketHandler.instance.send(PacketDistributor.SERVER.noArg(),
							new SDodgeRegenMessage(ClientStorage.dodges));
					flashes = 0;
					regen = ClientStorage.regenSpeed;
				}
			}
		}

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
					PacketHandler.instance.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> (ServerPlayerEntity) event.player),
							new CParticleMessage(PatronRewardHandler.getTier(event.player), event.player.getPosX(),
									event.player.getPosY(), event.player.getPosZ()));
				}
			});
		}
	}
}