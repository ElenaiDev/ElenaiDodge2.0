package com.elenai.elenaidodge2.event;

import com.elenai.elenaidodge2.capability.joined.JoinedProvider;
import com.elenai.elenaidodge2.capability.weight.WeightProvider;
import com.elenai.elenaidodge2.network.NetworkHandler;
import com.elenai.elenaidodge2.network.message.client.PatronMessageToClient;
import com.elenai.elenaidodge2.network.message.client.WeightMessageToClient;
import com.elenai.elenaidodge2.util.PatronRewardHandler;
import com.elenai.elenaidodge2.util.Utils;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.PacketDistributor;

public class ConfigEventListener {
	
	@SubscribeEvent
	public void playerJoinsWorld(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof ServerPlayerEntity && !event.getEntity().world.isRemote) {
			NetworkHandler.simpleChannel.send(
					PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getEntity()),
					new PatronMessageToClient(PatronRewardHandler.getTier((ServerPlayerEntity) event.getEntity())));
			Utils.updateClientConfig((ServerPlayerEntity) event.getEntity());
			event.getEntity().getCapability(WeightProvider.WEIGHT_CAP).ifPresent(w -> {

				NetworkHandler.simpleChannel.send(
						PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getEntity()),
						new WeightMessageToClient(w.getWeight()));

			});
			event.getEntity().getCapability(JoinedProvider.JOINED_CAP).ifPresent(j -> {
				if (!j.getJoined()) {
					Utils.initPlayer((ServerPlayerEntity) event.getEntity());
					j.set(true);
				}
			});;
		}
	}
}
