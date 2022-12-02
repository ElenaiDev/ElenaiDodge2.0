package com.elenai.elenaidodge2.sound;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.feathers.Feathers;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ED2Sounds {
	
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Feathers.MODID);

	public static final RegistryObject<SoundEvent> DODGE_SOUND = SOUNDS.register("dodge", () -> new SoundEvent(new ResourceLocation(ElenaiDodge2.MODID, "dodge")));
	
	public static void register(IEventBus bus) {
		SOUNDS.register(bus);
	}
	
}
