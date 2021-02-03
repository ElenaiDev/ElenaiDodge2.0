package com.elenai.elenaidodge.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class BaseEffect extends Effect {

	public BaseEffect(EffectType isBadEffectIn, int liquidColorIn) {
		super(isBadEffectIn, liquidColorIn);
		
	}
	
	 public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn, int amplifier) {
	      super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
	   }

	   public void applyAttributesModifiersToEntity(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn, int amplifier) {
	      super.applyAttributesModifiersToEntity(entityLivingBaseIn, attributeMapIn, amplifier);
	   }

}
