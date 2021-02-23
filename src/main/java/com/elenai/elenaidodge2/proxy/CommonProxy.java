package com.elenai.elenaidodge2.proxy;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.capability.CapabilityHandler;
import com.elenai.elenaidodge2.capability.absorption.Absorption;
import com.elenai.elenaidodge2.capability.absorption.AbsorptionStorage;
import com.elenai.elenaidodge2.capability.absorption.IAbsorption;
import com.elenai.elenaidodge2.capability.absorptionbool.AbsorptionBool;
import com.elenai.elenaidodge2.capability.absorptionbool.AbsorptionBoolStorage;
import com.elenai.elenaidodge2.capability.absorptionbool.IAbsorptionBool;
import com.elenai.elenaidodge2.capability.dodges.Dodges;
import com.elenai.elenaidodge2.capability.dodges.DodgesStorage;
import com.elenai.elenaidodge2.capability.dodges.IDodges;
import com.elenai.elenaidodge2.capability.invincibility.IInvincibility;
import com.elenai.elenaidodge2.capability.invincibility.Invincibility;
import com.elenai.elenaidodge2.capability.invincibility.InvincibilityStorage;
import com.elenai.elenaidodge2.capability.joined.IJoined;
import com.elenai.elenaidodge2.capability.joined.Joined;
import com.elenai.elenaidodge2.capability.joined.JoinedStorage;
import com.elenai.elenaidodge2.capability.particles.IParticles;
import com.elenai.elenaidodge2.capability.particles.Particles;
import com.elenai.elenaidodge2.capability.particles.ParticlesStorage;
import com.elenai.elenaidodge2.capability.weight.IWeight;
import com.elenai.elenaidodge2.capability.weight.Weight;
import com.elenai.elenaidodge2.capability.weight.WeightStorage;
import com.elenai.elenaidodge2.event.ConfigEventListener;
import com.elenai.elenaidodge2.event.InvincibilityEventListener;
import com.elenai.elenaidodge2.event.PlayerSleepEventListener;
import com.elenai.elenaidodge2.event.PotionTickEventListener;
import com.elenai.elenaidodge2.event.RenderEventListener;
import com.elenai.elenaidodge2.event.ServerDodgeEventListener;
import com.elenai.elenaidodge2.event.TickEventListener;
import com.elenai.elenaidodge2.init.EnchantmentInit;
import com.elenai.elenaidodge2.init.ItemInit;
import com.elenai.elenaidodge2.init.PotionInit;
import com.elenai.elenaidodge2.network.PacketHandler;
import com.elenai.elenaidodge2.util.PatronRewardHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod.EventBusSubscriber(modid = ElenaiDodge2.MODID)
public class CommonProxy {

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		PacketHandler.registerMessages(ElenaiDodge2.MODID);
		PatronRewardHandler.init();
		CapabilityManager.INSTANCE.register(IDodges.class, new DodgesStorage(), Dodges::new);
		CapabilityManager.INSTANCE.register(IJoined.class, new JoinedStorage(), Joined::new);
		CapabilityManager.INSTANCE.register(IWeight.class, new WeightStorage(), Weight::new);
		CapabilityManager.INSTANCE.register(IAbsorption.class, new AbsorptionStorage(), Absorption::new);
		CapabilityManager.INSTANCE.register(IAbsorptionBool.class, new AbsorptionBoolStorage(), AbsorptionBool::new);
		CapabilityManager.INSTANCE.register(IInvincibility.class, new InvincibilityStorage(), Invincibility::new);
		CapabilityManager.INSTANCE.register(IParticles.class, new ParticlesStorage(), Particles::new);


		MinecraftForge.EVENT_BUS.register(new RenderEventListener());
		MinecraftForge.EVENT_BUS.register(new EnchantmentInit());
		ItemInit.init();
		PotionInit.registerPotions();

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new TickEventListener());
		MinecraftForge.EVENT_BUS.register(new ConfigEventListener());
		MinecraftForge.EVENT_BUS.register(new ServerDodgeEventListener());
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
		MinecraftForge.EVENT_BUS.register(new PotionTickEventListener());
		MinecraftForge.EVENT_BUS.register(new ItemInit());
		MinecraftForge.EVENT_BUS.register(new PlayerSleepEventListener());
		MinecraftForge.EVENT_BUS.register(new InvincibilityEventListener());


	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}
