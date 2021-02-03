package com.elenai.elenaidodge.potion;

import com.elenai.elenaidodge.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge.capability.dodges.DodgesProvider;
import com.elenai.elenaidodge.capability.invincibility.WeightProvider;
import com.elenai.elenaidodge.network.PacketHandler;
import com.elenai.elenaidodge.network.message.CUpdateStatsMessage;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.network.PacketDistributor;

public class EnduranceEffect extends Effect {

	public EnduranceEffect(EffectType isBadEffectIn, int liquidColorIn) {
		super(isBadEffectIn, liquidColorIn);
		
	}
	
	 public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn, int amplifier) {
	      super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
	      
	      entityLivingBaseIn.getCapability(WeightProvider.WEIGHT_CAP).ifPresent(w -> {
				entityLivingBaseIn.getCapability(DodgesProvider.DODGES_CAP).ifPresent(d -> {
					entityLivingBaseIn.getCapability(AbsorptionProvider.ABSORPTION_CAP).ifPresent(a -> {
						PacketHandler.instance.send(
								PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) entityLivingBaseIn),
								new CUpdateStatsMessage(d.getDodges(), a.getAbsorption(), 0));
					});
				});
			});
	   }

	   public void applyAttributesModifiersToEntity(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn, int amplifier) {
	      super.applyAttributesModifiersToEntity(entityLivingBaseIn, attributeMapIn, amplifier);
	      
	      entityLivingBaseIn.getCapability(WeightProvider.WEIGHT_CAP).ifPresent(w -> {
				entityLivingBaseIn.getCapability(DodgesProvider.DODGES_CAP).ifPresent(d -> {
					entityLivingBaseIn.getCapability(AbsorptionProvider.ABSORPTION_CAP).ifPresent(a -> {
						PacketHandler.instance.send(
								PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) entityLivingBaseIn),
								new CUpdateStatsMessage(d.getDodges(), a.getAbsorption(), 0));
					});
				});
			});
	      
	   }

}
