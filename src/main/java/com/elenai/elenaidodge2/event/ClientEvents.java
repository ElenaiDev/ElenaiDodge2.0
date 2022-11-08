package com.elenai.elenaidodge2.event;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.client.KeyBinding;
import com.elenai.elenaidodge2.config.ED2ClientConfig;
import com.elenai.elenaidodge2.util.InputHandlers;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {

	public static int currentCooldown = 0;

	@Mod.EventBusSubscriber(modid = ElenaiDodge2.MODID, value = Dist.CLIENT)
	public static class ClientForgeEvents {
		@SubscribeEvent
		public static void onKeyInput(InputEvent.Key event) {
			Minecraft instance = Minecraft.getInstance();
			if (instance.player != null) {
				if (KeyBinding.DODGE_KEY.consumeClick() && !ED2ClientConfig.DOUBLE_TAP_MODE.get()) {
					InputHandlers.singleTapHandler();
				} else if (ED2ClientConfig.DOUBLE_TAP_MODE.get()) {
					InputHandlers.doubleTapInputHandler();
				}
			}
		}

		@SubscribeEvent
		public static void clientTickEvents(ClientTickEvent event) {
			Minecraft instance = Minecraft.getInstance();
			if (event.phase == TickEvent.Phase.START && instance.player != null && !instance.isPaused()) {
				if (currentCooldown > 0) {
					currentCooldown--;
				}
			}
		}

	}

	@Mod.EventBusSubscriber(modid = ElenaiDodge2.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class ClientModEvents {
		@SubscribeEvent
		public static void onKeyRegistry(RegisterKeyMappingsEvent event) {
			event.register(KeyBinding.DODGE_KEY);
		}
	}
}
