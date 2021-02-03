package com.elenai.elenaidodge.potions;

import com.elenai.elenaidodge.ElenaiDodge;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class BasePotion extends Potion {

	public BasePotion(String name, boolean isBadEffectIn, int liquidColorIn, int iconIndexX, int iconIndexY) {
		super(isBadEffectIn, liquidColorIn);
		
		setPotionName("effect." + name);
		setIconIndex(iconIndexX, iconIndexY);
		setRegistryName(new ResourceLocation(ElenaiDodge.MODID + ":" + name));
	}
	
	@Override
	public boolean hasStatusIcon() {
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(ElenaiDodge.MODID, "textures/gui/icons.png"));
		return true;
	}
}
