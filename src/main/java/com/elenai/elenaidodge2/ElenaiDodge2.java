package com.elenai.elenaidodge2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.elenai.elenaidodge2.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ElenaiDodge2.MODID, name = ElenaiDodge2.NAME, version = ElenaiDodge2.VERSION, dependencies = "before:rustic;after:quark")
public class ElenaiDodge2
{
    public static final String MODID = "elenaidodge2";
    public static final String NAME = "Elenai Dodge 2";
    public static final String VERSION = "1.0.8";
    @Mod.Instance
	public static ElenaiDodge2 INSTANCE;

	public static final Logger LOG = LogManager.getLogger("ElenaiDodge2");

	@SidedProxy(clientSide = "com.elenai.elenaidodge2.proxy.ClientProxy", serverSide = "com.elenai.elenaidodge2.proxy.ServerProxy")
	private static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
