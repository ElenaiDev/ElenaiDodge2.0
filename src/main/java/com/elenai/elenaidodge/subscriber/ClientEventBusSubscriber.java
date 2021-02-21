package com.elenai.elenaidodge.subscriber;

import com.elenai.elenaidodge.ElenaiDodge;
import com.elenai.elenaidodge.event.ArmorTickEventListener;
import com.elenai.elenaidodge.event.ClientTickEventListener;
import com.elenai.elenaidodge.event.CoreDodgeEventListener;
import com.elenai.elenaidodge.event.InputEventListener;
import com.elenai.elenaidodge.event.TooltipEventListener;
import com.elenai.elenaidodge.gui.DodgeGui;
import com.elenai.elenaidodge.util.ModKeybinds;
import com.elenai.elenaidodge.util.PatronRewardHandler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ElenaiDodge.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void onStaticClientSetup(FMLClientSetupEvent event) {
		event.setPhase(EventPriority.HIGHEST);
		PatronRewardHandler.getLocalName();
		MinecraftForge.EVENT_BUS.register(new DodgeGui());
		ModKeybinds.register();
		MinecraftForge.EVENT_BUS.register(new InputEventListener());
		MinecraftForge.EVENT_BUS.register(new CoreDodgeEventListener());
		MinecraftForge.EVENT_BUS.register(new ArmorTickEventListener());
		MinecraftForge.EVENT_BUS.register(new TooltipEventListener());
		MinecraftForge.EVENT_BUS.register(new ClientTickEventListener());


	}
}
