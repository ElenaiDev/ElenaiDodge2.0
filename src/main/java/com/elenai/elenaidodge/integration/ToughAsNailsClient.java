package com.elenai.elenaidodge.integration;

import net.minecraft.client.Minecraft;
import toughasnails.api.stat.capability.IThirst;
import toughasnails.api.thirst.ThirstHelper;

public class ToughAsNailsClient {

	public static boolean highThirst() {
		IThirst thirstManager = ThirstHelper.getThirstData(Minecraft.getMinecraft().player);
		if(thirstManager.getThirst() > 18) {
			return true;
		}
		return false;
	}
	
}