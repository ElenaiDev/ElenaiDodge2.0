package com.elenai.elenaidodge2;

import com.elenai.elenaidodge2.config.ED2ClientConfig;
import com.elenai.elenaidodge2.config.ED2CommonConfig;
import com.elenai.elenaidodge2.networking.ED2Messages;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ElenaiDodge2.MODID)
public class ElenaiDodge2 {
    public static final String MODID = "elenaidodge2";

    public ElenaiDodge2() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ED2ClientConfig.SPEC, "Elenai-Dodge-2-Client.toml");
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ED2CommonConfig.SPEC, "Elenai-Dodge-2-Common.toml");

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    	event.enqueueWork(() -> {
			ED2Messages.register();
		});
    }

}
