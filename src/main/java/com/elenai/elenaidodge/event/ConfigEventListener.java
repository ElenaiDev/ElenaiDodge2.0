package com.elenai.elenaidodge.event;

import com.elenai.elenaidodge.capability.joined.JoinedProvider;
import com.elenai.elenaidodge.capability.weight.WeightProvider;
import com.elenai.elenaidodge.network.NetworkHandler;
import com.elenai.elenaidodge.network.message.client.WeightMessageToClient;
import com.elenai.elenaidodge.util.Utils;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.PacketDistributor;

public class ConfigEventListener {
	
	@SubscribeEvent
	public void playerJoinsWorld(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof ServerPlayerEntity && !event.getEntity().world.isRemote) {
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
