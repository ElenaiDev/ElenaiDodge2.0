package com.elenai.elenaidodge.proxy;

import com.elenai.elenaidodge.ElenaiDodge;
import com.elenai.elenaidodge.event.ArmorTickEventListener;
import com.elenai.elenaidodge.event.ClientTickEventListener;
import com.elenai.elenaidodge.event.CoreDodgeEventListener;
import com.elenai.elenaidodge.event.InputEventListener;
import com.elenai.elenaidodge.event.TooltipEventListener;
import com.elenai.elenaidodge.gui.DodgeGui;
import com.elenai.elenaidodge.util.Keybinds;
import com.elenai.elenaidodge.util.PatronRewardHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = ElenaiDodge.MODID, value = Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		MinecraftForge.EVENT_BUS.register(new DodgeGui());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		super.init(event);
		PatronRewardHandler.setupClient();
		Keybinds.register();
		MinecraftForge.EVENT_BUS.register(new InputEventListener());
		MinecraftForge.EVENT_BUS.register(new CoreDodgeEventListener());
		MinecraftForge.EVENT_BUS.register(new ArmorTickEventListener());
		MinecraftForge.EVENT_BUS.register(new TooltipEventListener());
		MinecraftForge.EVENT_BUS.register(new ClientTickEventListener());


	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

}
