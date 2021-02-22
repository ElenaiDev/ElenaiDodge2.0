package com.elenai.elenaidodge.event;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import com.elenai.elenaidodge.ModConfig;
import com.elenai.elenaidodge.api.DodgeEvent;
import com.elenai.elenaidodge.api.DodgeEvent.Direction;
import com.elenai.elenaidodge.api.DodgeEvent.RequestDodgeEvent;
import com.elenai.elenaidodge.util.ClientStorage;
import com.elenai.elenaidodge.util.Keybinds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class InputEventListener {

	/*
	 * The Input Event Listener
	 * 1) This class fires a dodge event when the dodge key is pressed with a movement key if double tap mode is disabled.
	 * 2) This class fires a dodge event when a movement key is double tapped if double tap mode is enabled.
	 */
	
	public static boolean needsInit = true;
	public static HashMap<KeyBinding, Long> keyTimesLastPressed = new HashMap<>();
	public static HashMap<KeyBinding, Boolean> keyLastState = new HashMap<>();
	public static HashMap<KeyBinding, String> lookupKeyToDirection = new HashMap<>();

	@SubscribeEvent
	public void onKeyInput(TickEvent.ClientTickEvent event) {

		EntityPlayer player = Minecraft.getMinecraft().player;

		if (ClientStorage.cooldown <= 0 && event.phase == Phase.START
				&& Minecraft.getMinecraft().currentScreen == null) {

			if (ModConfig.client.controls.doubleTap) {
				if (needsInit) {
					needsInit = false;
					tickInit();
				}
				tickDodging();
				if (Keybinds.dodge.isKeyDown() && !ModConfig.client.controls.doubleTapForwards) {
					DodgeEvent ev = new RequestDodgeEvent(Direction.FORWARD);
					MinecraftForge.EVENT_BUS.post(ev);
				}
			}

			else if (Keybinds.dodge.isKeyDown()) {
				if (player.moveStrafing > 0) {
					DodgeEvent ev = new RequestDodgeEvent(Direction.LEFT);
					MinecraftForge.EVENT_BUS.post(ev);
				} else if (player.moveStrafing < 0) {
					DodgeEvent ev = new RequestDodgeEvent(Direction.RIGHT);
					MinecraftForge.EVENT_BUS.post(ev);
				} else if (player.moveForward > 0) {
					DodgeEvent ev = new RequestDodgeEvent(Direction.FORWARD);
					MinecraftForge.EVENT_BUS.post(ev);
				} else if (player.moveForward < 0) {
					DodgeEvent ev = new RequestDodgeEvent(Direction.BACK);
					MinecraftForge.EVENT_BUS.post(ev);
				}
			}
		}
	}

	public static void tickInit() {
		lookupKeyToDirection.put(Minecraft.getMinecraft().gameSettings.keyBindForward, "forward");
		lookupKeyToDirection.put(Minecraft.getMinecraft().gameSettings.keyBindBack, "back");
		lookupKeyToDirection.put(Minecraft.getMinecraft().gameSettings.keyBindLeft, "left");
		lookupKeyToDirection.put(Minecraft.getMinecraft().gameSettings.keyBindRight, "right");
		keyLastState.put(Minecraft.getMinecraft().gameSettings.keyBindForward, false);
		keyLastState.put(Minecraft.getMinecraft().gameSettings.keyBindBack, false);
		keyLastState.put(Minecraft.getMinecraft().gameSettings.keyBindLeft, false);
		keyLastState.put(Minecraft.getMinecraft().gameSettings.keyBindRight, false);
	}

	public static void tickDodging() {
		lookupKeyToDirection.forEach((k, d) -> processDodgeKey(k, d));
	}

	public static void processDodgeKey(KeyBinding key, String direction) {
		long curTime = System.currentTimeMillis();
		long lastTime = getLastKeyTime(key);

		if (key.getKeyCode() > 0) {
			if (Keyboard.isKeyDown(key.getKeyCode()) && !keyLastState.get(key)) {
				if (lastTime == -1L) {
					setLastKeyTime(key, curTime);
				} else {
					if (lastTime + ModConfig.client.controls.doubleTapTicks > curTime) {
						if(!direction.toUpperCase().equals("FORWARD")|| ModConfig.client.controls.doubleTapForwards) {
						DodgeEvent ev = new RequestDodgeEvent(Direction.valueOf(direction.toUpperCase()));
						MinecraftForge.EVENT_BUS.post(ev);}
						setLastKeyTime(key, -1L);
					} else {
						setLastKeyTime(key, curTime);
					}
				}
			}

			if (!Keyboard.isKeyDown(key.getKeyCode()) && keyLastState.get(key)) {
				for (Map.Entry<KeyBinding, Long> entry : keyTimesLastPressed.entrySet()) {
					if (entry.getKey() != key) {
						entry.setValue(-1L);
					}
				}
			}
			keyLastState.put(key, Keyboard.isKeyDown(key.getKeyCode()));
		}
	}

	public static long getLastKeyTime(KeyBinding keybind) {
		if (!keyTimesLastPressed.containsKey(keybind)) {
			keyTimesLastPressed.put(keybind, -1L);
		}
		return keyTimesLastPressed.get(keybind);
	}

	public static void setLastKeyTime(KeyBinding keybind, long time) {
		keyTimesLastPressed.put(keybind, time);
	}
}
