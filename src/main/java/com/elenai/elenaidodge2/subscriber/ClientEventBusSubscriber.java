package com.elenai.elenaidodge2.subscriber;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.event.ArmorTickEventListener;
import com.elenai.elenaidodge2.event.ClientTickEventListener;
import com.elenai.elenaidodge2.event.ClientDodgeEventListener;
import com.elenai.elenaidodge2.event.InputEventListener;
import com.elenai.elenaidodge2.event.TooltipEventListener;
import com.elenai.elenaidodge2.gui.DodgeGui;
import com.elenai.elenaidodge2.util.ModKeybinds;
import com.elenai.elenaidodge2.util.PatronRewardHandler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ElenaiDodge2.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void onStaticClientSetup(FMLClientSetupEvent event) {
		event.setPhase(EventPriority.HIGH);
		PatronRewardHandler.getLocalName();
		MinecraftForge.EVENT_BUS.register(new DodgeGui());
		ModKeybinds.register();
		MinecraftForge.EVENT_BUS.register(new InputEventListener());
		MinecraftForge.EVENT_BUS.register(new ClientDodgeEventListener());
		MinecraftForge.EVENT_BUS.register(new ArmorTickEventListener());
		MinecraftForge.EVENT_BUS.register(new TooltipEventListener());
		MinecraftForge.EVENT_BUS.register(new ClientTickEventListener());
	}
}
