package com.elenai.elenaidodge.event;

import com.elenai.elenaidodge.capability.dodges.DodgesProvider;
import com.elenai.elenaidodge.capability.dodges.IDodges;
import com.elenai.elenaidodge.network.PacketHandler;
import com.elenai.elenaidodge.network.message.CUpdateDodgeMessage;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerSleepEventListener {

	@SubscribeEvent
	public void onPlayerWakeUp(PlayerWakeUpEvent event) {

		if (!event.getEntityPlayer().world.isRemote) {
			if (event.getEntityPlayer().world.getWorldTime() == 24000) {
				IDodges d = event.getEntityPlayer().getCapability(DodgesProvider.DODGES_CAP, null);
				d.set(20);
				PacketHandler.instance.sendTo(new CUpdateDodgeMessage(d.getDodges()),
						(EntityPlayerMP) event.getEntityPlayer());
			}
		}
	}
}
