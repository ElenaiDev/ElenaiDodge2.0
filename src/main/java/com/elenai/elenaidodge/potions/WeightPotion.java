package com.elenai.elenaidodge.potions;

import com.elenai.elenaidodge.ElenaiDodge;
import com.elenai.elenaidodge.capability.weight.IWeight;
import com.elenai.elenaidodge.capability.weight.WeightProvider;
import com.elenai.elenaidodge.network.PacketHandler;
import com.elenai.elenaidodge.network.message.CUpdateWeightMessage;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class WeightPotion extends Potion {

	public WeightPotion(String name, boolean isBadEffectIn, int liquidColorIn, int iconIndexX, int iconIndexY) {
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
	
	@Override
	 public void applyAttributesModifiersToEntity(EntityLivingBase entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
		super.applyAttributesModifiersToEntity(entityLivingBaseIn, attributeMapIn, amplifier);
		
		if(entityLivingBaseIn instanceof EntityPlayerMP) {
			IWeight w = entityLivingBaseIn.getCapability(WeightProvider.WEIGHT_CAP, null);
			w.set(200);
			PacketHandler.instance.sendTo(new CUpdateWeightMessage(200), (EntityPlayerMP) entityLivingBaseIn);
		}
	}
	
	@Override
	public void removeAttributesModifiersFromEntity(EntityLivingBase entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
		super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);	
		
		if(entityLivingBaseIn instanceof EntityPlayerMP) {

			PacketHandler.instance.sendTo(new CUpdateWeightMessage(0), (EntityPlayerMP) entityLivingBaseIn);
		}
	}

}
