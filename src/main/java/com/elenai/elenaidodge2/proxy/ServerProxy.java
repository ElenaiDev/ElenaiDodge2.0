package com.elenai.elenaidodge2.proxy;

import com.elenai.elenaidodge2.ElenaiDodge2;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = ElenaiDodge2.MODID, value = Side.SERVER)
public class ServerProxy extends CommonProxy {

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

}
