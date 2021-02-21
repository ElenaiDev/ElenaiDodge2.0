package com.elenai.elenaidodge.integration;

import net.minecraft.client.Minecraft;
import toughasnails.api.thirst.IThirst;
import toughasnails.api.thirst.ThirstHelper;

public class ToughAsNailsClient {

	@SuppressWarnings("resource")
	public static boolean highThirst() {
		IThirst thirstManager = ThirstHelper.getThirst(Minecraft.getInstance().player);
		if(thirstManager.getThirst() > 18) {
			return true;
		}
		return false;
	}
	
}
