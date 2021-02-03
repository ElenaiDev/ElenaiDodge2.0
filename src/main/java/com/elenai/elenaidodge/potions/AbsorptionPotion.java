package com.elenai.elenaidodge.potions;

import com.elenai.elenaidodge.ElenaiDodge;
import com.elenai.elenaidodge.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge.capability.absorption.IAbsorption;
import com.elenai.elenaidodge.network.PacketHandler;
import com.elenai.elenaidodge.network.message.CUpdateAbsorptionMessage;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class AbsorptionPotion extends Potion {

	public AbsorptionPotion(String name, boolean isBadEffectIn, int liquidColorIn, int iconIndexX, int iconIndexY) {
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
			IAbsorption a = entityLivingBaseIn.getCapability(AbsorptionProvider.ABSORPTION_CAP, null);
			a.set((amplifier+1)*8);
			PacketHandler.instance.sendTo(new CUpdateAbsorptionMessage((amplifier+1)*8), (EntityPlayerMP) entityLivingBaseIn);
		}
	}
	
	@Override
	public void removeAttributesModifiersFromEntity(EntityLivingBase entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
		super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);	
		
		if(entityLivingBaseIn instanceof EntityPlayerMP) {
			IAbsorption a = entityLivingBaseIn.getCapability(AbsorptionProvider.ABSORPTION_CAP, null);
			a.set(0);
			PacketHandler.instance.sendTo(new CUpdateAbsorptionMessage(0), (EntityPlayerMP) entityLivingBaseIn);
		}
	}

}
