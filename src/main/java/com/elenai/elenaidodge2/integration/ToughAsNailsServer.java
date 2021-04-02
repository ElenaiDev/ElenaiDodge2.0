package com.elenai.elenaidodge2.integration;

import com.elenai.elenaidodge2.ModConfig;

import net.minecraft.entity.player.EntityPlayer;
import toughasnails.api.stat.capability.IThirst;
import toughasnails.api.thirst.ThirstHelper;

public class ToughAsNailsServer {
	
	private EntityPlayer player;
	
	public ToughAsNailsServer(EntityPlayer player) {
		this.player = player;
	}

	public void addThirst() {
		IThirst thirstManager = ThirstHelper.getThirstData(player);
		if(thirstManager.getThirst() >= 18 && ModConfig.common.integration.toughAsNails.enabled) {
			thirstManager.setExhaustion(thirstManager.getExhaustion() + (float) ModConfig.common.integration.toughAsNails.exhaustion); // UPDATE
		}
	}
	
}