package com.elenai.elenaidodge2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.elenai.elenaidodge2.config.ConfigHandler;
import com.elenai.elenaidodge2.list.ItemList;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ElenaiDodge2.MODID)
public class ElenaiDodge2 {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String NAME = "Elenai Dodge";
	public static final String MODID = "elenaidodge2";
    public static final String VERSION = "1.0.6";
	
	public static final Logger LOG = LogManager.getLogger("ElenaiDodge");
	
	public ElenaiDodge2() {
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigHandler.CLIENT_SPEC);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHandler.COMMON_SPEC);
		
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		ItemList.ITEMS.register(bus);
	   }
}
