package com.elenai.elenaidodge.proxy;

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
import com.elenai.elenaidodge.event.ConfigEventListener;
import com.elenai.elenaidodge.event.InvincibilityEventListener;
import com.elenai.elenaidodge.event.PlayerSleepEventListener;
import com.elenai.elenaidodge.event.PotionTickEventListener;
import com.elenai.elenaidodge.event.RenderEventListener;
import com.elenai.elenaidodge.event.ServerDodgeEventListener;
import com.elenai.elenaidodge.event.TickEventListener;
import com.elenai.elenaidodge.init.EnchantmentInit;
import com.elenai.elenaidodge.init.ItemInit;
import com.elenai.elenaidodge.init.PotionInit;
import com.elenai.elenaidodge.network.PacketHandler;
import com.elenai.elenaidodge.util.PatronRewardHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod.EventBusSubscriber(modid = ElenaiDodge.MODID)
public class CommonProxy {

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		PacketHandler.registerMessages(ElenaiDodge.MODID);
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
