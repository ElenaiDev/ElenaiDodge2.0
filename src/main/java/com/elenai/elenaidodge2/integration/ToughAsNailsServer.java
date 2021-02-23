package com.elenai.elenaidodge2.integration;

import com.elenai.elenaidodge2.config.ConfigHandler;

import net.minecraft.entity.player.PlayerEntity;
import toughasnails.api.thirst.IThirst;
import toughasnails.api.thirst.ThirstHelper;

public class ToughAsNailsServer {
	
	private PlayerEntity player;
	
	public ToughAsNailsServer(PlayerEntity player) {
		this.player = player;
	}

	public void addThirst() {
		IThirst thirstManager = ThirstHelper.getThirst(player);
		if(thirstManager.getThirst() >= 18) {
			thirstManager.addExhaustion((float) ConfigHandler.tanCost);
		}
	}
	
}
