package com.elenai.elenaidodge2.event;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.capability.joined.IJoined;
import com.elenai.elenaidodge2.capability.joined.JoinedProvider;
import com.elenai.elenaidodge2.capability.regen.IRegen;
import com.elenai.elenaidodge2.capability.regen.RegenProvider;
import com.elenai.elenaidodge2.capability.weight.IWeight;
import com.elenai.elenaidodge2.capability.weight.WeightProvider;
import com.elenai.elenaidodge2.network.PacketHandler;
import com.elenai.elenaidodge2.network.message.CPatronMessage;
import com.elenai.elenaidodge2.network.message.CUpdateRegenMessage;
import com.elenai.elenaidodge2.network.message.CUpdateWeightMessage;
import com.elenai.elenaidodge2.util.PatronRewardHandler;
import com.elenai.elenaidodge2.util.Utils;

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
		if (event.getModID().equals(ElenaiDodge2.MODID))
			ConfigManager.sync(ElenaiDodge2.MODID, Type.INSTANCE);
		if(event.isWorldRunning()) {
		Utils.updateClientConfig();
		}
	}

	@SubscribeEvent
	public void playerJoinsWorld(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof EntityPlayerMP && !event.getEntity().world.isRemote) {
			PacketHandler.instance.sendTo(new CPatronMessage(PatronRewardHandler.getTier((EntityPlayerMP) event.getEntity())), (EntityPlayerMP) event.getEntity());
			Utils.updateClientConfig((EntityPlayerMP) event.getEntity());
			
			IWeight w = event.getEntity().getCapability(WeightProvider.WEIGHT_CAP, null);
			PacketHandler.instance.sendTo(new CUpdateWeightMessage(w.getWeight()), (EntityPlayerMP) event.getEntity());
			
			IRegen r = event.getEntity().getCapability(RegenProvider.REGEN_CAP, null);
			PacketHandler.instance.sendTo(new CUpdateRegenMessage(r.getRegen()), (EntityPlayerMP) event.getEntity());
			
			IJoined j = event.getEntity().getCapability(JoinedProvider.JOINED_CAP, null);
			if (!j.hasJoined()) {
				Utils.initPlayer((EntityPlayer) event.getEntity());
				j.join();
			}
		}
	}
}
