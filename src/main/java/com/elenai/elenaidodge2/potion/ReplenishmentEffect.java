package com.elenai.elenaidodge2.potion;

import com.elenai.elenaidodge2.api.FeathersHelper;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class ReplenishmentEffect extends Effect {

	public ReplenishmentEffect(EffectType isBadEffectIn, int liquidColorIn) {
		super(isBadEffectIn, liquidColorIn);

	}

	public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn,
			AttributeModifierManager attributeMapIn, int amplifier) {
		super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);

		if (entityLivingBaseIn instanceof ServerPlayerEntity) {
			FeathersHelper.increaseRegenModifier((ServerPlayerEntity) entityLivingBaseIn, ((amplifier+1)*20) + 30);
		}
	}

	public void applyAttributesModifiersToEntity(LivingEntity entityLivingBaseIn,
			AttributeModifierManager attributeMapIn, int amplifier) {
		super.applyAttributesModifiersToEntity(entityLivingBaseIn, attributeMapIn, amplifier);

		if (entityLivingBaseIn instanceof ServerPlayerEntity) {
				FeathersHelper.decreaseRegenModifier((ServerPlayerEntity) entityLivingBaseIn, ((amplifier+1)*20) + 30);
		}

	}

}
