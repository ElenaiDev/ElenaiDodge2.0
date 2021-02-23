package com.elenai.elenaidodge2.proxy;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.event.ArmorTickEventListener;
import com.elenai.elenaidodge2.event.ClientTickEventListener;
import com.elenai.elenaidodge2.event.CoreDodgeEventListener;
import com.elenai.elenaidodge2.event.InputEventListener;
import com.elenai.elenaidodge2.event.TooltipEventListener;
import com.elenai.elenaidodge2.gui.DodgeGui;
import com.elenai.elenaidodge2.util.Keybinds;
import com.elenai.elenaidodge2.util.PatronRewardHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = ElenaiDodge2.MODID, value = Side.CLIENT)
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
