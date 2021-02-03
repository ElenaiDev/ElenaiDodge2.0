package com.elenai.elenaidodge.event;

import com.elenai.elenaidodge.ElenaiDodge;
import com.elenai.elenaidodge.capability.joined.IJoined;
import com.elenai.elenaidodge.capability.joined.JoinedProvider;
import com.elenai.elenaidodge.capability.weight.IWeight;
import com.elenai.elenaidodge.capability.weight.WeightProvider;
import com.elenai.elenaidodge.network.PacketHandler;
import com.elenai.elenaidodge.network.message.CUpdateWeightMessage;
import com.elenai.elenaidodge.util.Utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigEventListener {
	
	/*
	 * Runs whenever the Config needs to be updated on the Client Side.
	 * Also initialises the player on first time setup.
	 */

	@SubscribeEvent
	public void onConfigChange(OnConfigChangedEvent event) {
		if (event.getModID().equals(ElenaiDodge.MODID))
			ConfigManager.sync(ElenaiDodge.MODID, Type.INSTANCE);
		if(event.isWorldRunning()) {
		Utils.updateClientConfig();
		}
	}

	@SubscribeEvent
	public void playerJoinsWorld(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof EntityPlayerMP && !event.getEntity().world.isRemote) {
			Utils.updateClientConfig((EntityPlayerMP) event.getEntity());
			IWeight w = event.getEntity().getCapability(WeightProvider.WEIGHT_CAP, null);
			PacketHandler.instance.sendTo(new CUpdateWeightMessage(w.getWeight()), (EntityPlayerMP) event.getEntity());
			IJoined j = event.getEntity().getCapability(JoinedProvider.JOINED_CAP, null);
			if (!j.hasJoined()) {
				Utils.initPlayer((EntityPlayer) event.getEntity());
				j.join();
			}
		}
	}
}
