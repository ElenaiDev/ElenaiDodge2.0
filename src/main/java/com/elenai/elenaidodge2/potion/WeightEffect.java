package com.elenai.elenaidodge2.potion;

import com.elenai.elenaidodge2.capability.weight.WeightProvider;
import com.elenai.elenaidodge2.network.NetworkHandler;
import com.elenai.elenaidodge2.network.message.client.WeightMessageToClient;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.network.PacketDistributor;

public class WeightEffect extends Effect {

	public WeightEffect(EffectType isBadEffectIn, int liquidColorIn) {
		super(isBadEffectIn, liquidColorIn);
	}

	public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn,
			AttributeModifierManager attributeMapIn, int amplifier) {
		super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
		if (entityLivingBaseIn instanceof ServerPlayerEntity) {

		entityLivingBaseIn.getCapability(WeightProvider.WEIGHT_CAP).ifPresent(w -> {
			NetworkHandler.simpleChannel.send(
					PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) entityLivingBaseIn),
					new WeightMessageToClient(0));
		});
		}
	}

	public void applyAttributesModifiersToEntity(LivingEntity entityLivingBaseIn,
			AttributeModifierManager attributeMapIn, int amplifier) {
		super.applyAttributesModifiersToEntity(entityLivingBaseIn, attributeMapIn, amplifier);
		if (entityLivingBaseIn instanceof ServerPlayerEntity) {
			entityLivingBaseIn.getCapability(WeightProvider.WEIGHT_CAP).ifPresent(w -> {
				w.set(200);
				NetworkHandler.simpleChannel.send(
						PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) entityLivingBaseIn),
						new WeightMessageToClient(w.getWeight()));
			});
		}
	}

}
