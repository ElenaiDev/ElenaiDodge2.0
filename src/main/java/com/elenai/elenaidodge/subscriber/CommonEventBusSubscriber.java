package com.elenai.elenaidodge.subscriber;

import com.elenai.elenaidodge.ElenaiDodge;
import com.elenai.elenaidodge.capability.CapabilityHandler;
import com.elenai.elenaidodge.capability.absorption.Absorption;
import com.elenai.elenaidodge.capability.absorption.AbsorptionStorage;
import com.elenai.elenaidodge.capability.absorption.IAbsorption;
import com.elenai.elenaidodge.capability.absorptionbool.AbsorptionBool;
import com.elenai.elenaidodge.capability.absorptionbool.AbsorptionBoolStorage;
import com.elenai.elenaidodge.capability.absorptionbool.IAbsorptionBool;
import com.elenai.elenaidodge.capability.dodges.Dodges;
import com.elenai.elenaidodge.capability.dodges.DodgesStorage;
import com.elenai.elenaidodge.capability.dodges.IDodges;
import com.elenai.elenaidodge.capability.invincibility.IInvincibility;
import com.elenai.elenaidodge.capability.invincibility.Invincibility;
import com.elenai.elenaidodge.capability.invincibility.InvincibilityStorage;
import com.elenai.elenaidodge.capability.joined.IJoined;
import com.elenai.elenaidodge.capability.joined.Joined;
import com.elenai.elenaidodge.capability.joined.JoinedStorage;
import com.elenai.elenaidodge.capability.particles.IParticles;
import com.elenai.elenaidodge.capability.particles.Particles;
import com.elenai.elenaidodge.capability.particles.ParticlesStorage;
import com.elenai.elenaidodge.capability.weight.IWeight;
import com.elenai.elenaidodge.capability.weight.Weight;
import com.elenai.elenaidodge.capability.weight.WeightStorage;
import com.elenai.elenaidodge.enchantment.EnchantmentLightweight;
import com.elenai.elenaidodge.event.ConfigEventListener;
import com.elenai.elenaidodge.event.InvincibilityEventListener;
import com.elenai.elenaidodge.event.PotionTickEventListener;
import com.elenai.elenaidodge.event.ServerDodgeEventListener;
import com.elenai.elenaidodge.event.TickEventListener;
import com.elenai.elenaidodge.list.EnchantmentList;
import com.elenai.elenaidodge.list.PotionList;
import com.elenai.elenaidodge.network.NetworkHandler;
import com.elenai.elenaidodge.potion.AbsorptionEffect;
import com.elenai.elenaidodge.potion.BaseEffect;
import com.elenai.elenaidodge.potion.EnduranceEffect;
import com.elenai.elenaidodge.potion.WeightEffect;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = ElenaiDodge.MODID, bus = Bus.MOD)
public class CommonEventBusSubscriber {
	
	@SubscribeEvent
	public static void onStaticCommonSetup(FMLCommonSetupEvent event) {
		NetworkHandler.init();
		
		CapabilityManager.INSTANCE.register(IAbsorption.class, new AbsorptionStorage(), Absorption::new);
		CapabilityManager.INSTANCE.register(IAbsorptionBool.class, new AbsorptionBoolStorage(), AbsorptionBool::new);
		CapabilityManager.INSTANCE.register(IDodges.class, new DodgesStorage(), Dodges::new);
		CapabilityManager.INSTANCE.register(IInvincibility.class, new InvincibilityStorage(), Invincibility::new);
		CapabilityManager.INSTANCE.register(IJoined.class, new JoinedStorage(), Joined::new);
		CapabilityManager.INSTANCE.register(IWeight.class, new WeightStorage(), Weight::new);
		CapabilityManager.INSTANCE.register(IParticles.class, new ParticlesStorage(), Particles::new);

		
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
		MinecraftForge.EVENT_BUS.register(new TickEventListener());
		MinecraftForge.EVENT_BUS.register(new ConfigEventListener());
		MinecraftForge.EVENT_BUS.register(new ServerDodgeEventListener());
		MinecraftForge.EVENT_BUS.register(new InvincibilityEventListener());
		MinecraftForge.EVENT_BUS.register(new PotionTickEventListener());
		
		PotionList.addRecipes(); //TODO We will switch to Deferred Registries eventually

	}

	@SubscribeEvent
	public static void registerPotions(final RegistryEvent.Register<Potion> event) {
		event.getRegistry().registerAll(
				PotionList.FEATHERS = new Potion(new EffectInstance(PotionList.FEATHERS_EFFECT, 3600))
						.setRegistryName(new ResourceLocation(ElenaiDodge.MODID, "feathers")),
				PotionList.LONG_FEATHERS = new Potion(new EffectInstance(PotionList.FEATHERS_EFFECT, 9600))
						.setRegistryName(new ResourceLocation(ElenaiDodge.MODID, "long_feathers")),
				PotionList.STRONG_FEATHERS = new Potion(new EffectInstance(PotionList.FEATHERS_EFFECT, 1800, 1))
						.setRegistryName(new ResourceLocation(ElenaiDodge.MODID, "strong_feathers")),
				PotionList.WEIGHT = new Potion(new EffectInstance(PotionList.WEIGHT_EFFECT, 3600))
						.setRegistryName(new ResourceLocation(ElenaiDodge.MODID, "weight")),
				PotionList.LONG_WEIGHT = new Potion(new EffectInstance(PotionList.WEIGHT_EFFECT, 9600))
						.setRegistryName(new ResourceLocation(ElenaiDodge.MODID, "long_weight")),
				PotionList.ENDURANCE = new Potion(new EffectInstance(PotionList.ENDURANCE_EFFECT, 3600))
						.setRegistryName(new ResourceLocation(ElenaiDodge.MODID, "endurance")),
				PotionList.LONG_ENDURANCE = new Potion(new EffectInstance(PotionList.ENDURANCE_EFFECT, 9600))
						.setRegistryName(new ResourceLocation(ElenaiDodge.MODID, "long_endurance")),
				PotionList.STRONG_ENDURANCE = new Potion(new EffectInstance(PotionList.ENDURANCE_EFFECT, 1800, 1))
						.setRegistryName(new ResourceLocation(ElenaiDodge.MODID, "strong_endurance")),
				PotionList.FORCEFUL = new Potion(new EffectInstance(PotionList.FORCEFUL_EFFECT, 3600))
						.setRegistryName(new ResourceLocation(ElenaiDodge.MODID, "forceful")),
				PotionList.LONG_FORCEFUL = new Potion(new EffectInstance(PotionList.FORCEFUL_EFFECT, 9600))
						.setRegistryName(new ResourceLocation(ElenaiDodge.MODID, "long_forceful")),
				PotionList.STRONG_FORCEFUL = new Potion(new EffectInstance(PotionList.FORCEFUL_EFFECT, 1800, 1))
						.setRegistryName(new ResourceLocation(ElenaiDodge.MODID, "strong_forceful")),
				PotionList.FEEBLE = new Potion(new EffectInstance(PotionList.FEEBLE_EFFECT, 3600))
						.setRegistryName(new ResourceLocation(ElenaiDodge.MODID, "feeble")),
				PotionList.LONG_FEEBLE = new Potion(new EffectInstance(PotionList.FEEBLE_EFFECT, 9600))
						.setRegistryName(new ResourceLocation(ElenaiDodge.MODID, "long_feeble")),
				PotionList.STRONG_FEEBLE = new Potion(new EffectInstance(PotionList.FEEBLE_EFFECT, 1800, 1))
						.setRegistryName(new ResourceLocation(ElenaiDodge.MODID, "strong_feeble"))

		);
	}

	@SubscribeEvent
	public static void registerEffects(final RegistryEvent.Register<Effect> event) {
		event.getRegistry().registerAll(
				PotionList.FEATHERS_EFFECT = new AbsorptionEffect(EffectType.BENEFICIAL, 13882323)
						.setRegistryName(new ResourceLocation(ElenaiDodge.MODID, "feathers")),
				PotionList.WEIGHT_EFFECT = new WeightEffect(EffectType.HARMFUL, 5533805)
						.setRegistryName(new ResourceLocation(ElenaiDodge.MODID, "weight")),
				PotionList.ENDURANCE_EFFECT = new EnduranceEffect(EffectType.BENEFICIAL, 9318976)
						.setRegistryName(new ResourceLocation(ElenaiDodge.MODID, "endurance")),
				PotionList.FORCEFUL_EFFECT = new BaseEffect(EffectType.BENEFICIAL, 5534118)
						.setRegistryName(new ResourceLocation(ElenaiDodge.MODID, "forceful")),
				PotionList.FEEBLE_EFFECT = new BaseEffect(EffectType.HARMFUL, 10693147)
						.setRegistryName(new ResourceLocation(ElenaiDodge.MODID, "feeble")));
	}

	@SubscribeEvent
	public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
		event.getRegistry().registerAll(EnchantmentList.LIGHTWEIGHT = new EnchantmentLightweight()
				.setRegistryName(new ResourceLocation(ElenaiDodge.MODID, "lightweight")));
	}
}
