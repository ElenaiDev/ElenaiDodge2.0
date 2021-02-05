package com.elenai.elenaidodge.potion;

import com.elenai.elenaidodge.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge.network.NetworkHandler;
import com.elenai.elenaidodge.network.message.client.AbsorptionMessageToClient;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.network.PacketDistributor;

public class AbsorptionEffect extends Effect {
	
	public AbsorptionEffect(EffectType isBadEffectIn, int liquidColorIn) {
		super(isBadEffectIn, liquidColorIn);
		
	}
	
	 public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn, int amplifier) {
	      super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
	      if(entityLivingBaseIn instanceof ServerPlayerEntity) {

						entityLivingBaseIn.getCapability(AbsorptionProvider.ABSORPTION_CAP).ifPresent(a -> {
							a.set(0);
							NetworkHandler.simpleChannel.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) entityLivingBaseIn),
									new AbsorptionMessageToClient(0));
						});
			}
	   }

	   public void applyAttributesModifiersToEntity(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn, int amplifier) {
	      super.applyAttributesModifiersToEntity(entityLivingBaseIn, attributeMapIn, amplifier);
	      if(entityLivingBaseIn instanceof ServerPlayerEntity) {
						entityLivingBaseIn.getCapability(AbsorptionProvider.ABSORPTION_CAP).ifPresent(a -> {
							a.set((amplifier+1)*8);

							NetworkHandler.simpleChannel.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) entityLivingBaseIn),
									new AbsorptionMessageToClient((amplifier+1)*8));
						});
				
			}
	   }
}
