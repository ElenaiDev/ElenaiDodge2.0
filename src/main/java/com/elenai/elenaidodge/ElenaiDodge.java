package com.elenai.elenaidodge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.elenai.elenaidodge.config.ConfigHandler;
import com.elenai.elenaidodge.list.ItemList;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ElenaiDodge.MODID)
public class ElenaiDodge {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String NAME = "Elenai Dodge";
	public static final String MODID = "elenaidodge";
	
	public static final Logger LOG = LogManager.getLogger("ElenaiDodge");
	
	public ElenaiDodge() {
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigHandler.CLIENT_SPEC);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHandler.COMMON_SPEC);
		
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::setup);

		ItemList.ITEMS.register(bus);
	   }
	private void setup(final FMLCommonSetupEvent event) {

	}
}
