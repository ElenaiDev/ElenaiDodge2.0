package com.elenai.elenaidodge2.event;

import java.util.HashMap;
import java.util.Map;

import com.elenai.elenaidodge2.api.DodgeEvent;
import com.elenai.elenaidodge2.api.DodgeEvent.Direction;
import com.elenai.elenaidodge2.api.DodgeEvent.RequestDodgeEvent;
import com.elenai.elenaidodge2.config.ConfigHandler;
import com.elenai.elenaidodge2.util.ClientStorage;
import com.elenai.elenaidodge2.util.ModKeybinds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
@SuppressWarnings("resource")

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

		PlayerEntity player = Minecraft.getInstance().player;

		if (ClientStorage.cooldown <= 0 && event.phase == Phase.START
				&& Minecraft.getInstance().currentScreen == null) {

			if (ConfigHandler.doubleTap) {
				if (needsInit) {
					needsInit = false;
					tickInit();
				}
				tickDodging();
				if (ModKeybinds.dodge.isKeyDown() && !ConfigHandler.doubleTapForwards) {
					DodgeEvent ev = new RequestDodgeEvent(Direction.FORWARD);
					MinecraftForge.EVENT_BUS.post(ev);
				}
			}

			else if (ModKeybinds.dodge.isKeyDown()) {
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
		lookupKeyToDirection.put(Minecraft.getInstance().gameSettings.keyBindForward, "forward");
		lookupKeyToDirection.put(Minecraft.getInstance().gameSettings.keyBindBack, "back");
		lookupKeyToDirection.put(Minecraft.getInstance().gameSettings.keyBindLeft, "left");
		lookupKeyToDirection.put(Minecraft.getInstance().gameSettings.keyBindRight, "right");
		keyLastState.put(Minecraft.getInstance().gameSettings.keyBindForward, false);
		keyLastState.put(Minecraft.getInstance().gameSettings.keyBindBack, false);
		keyLastState.put(Minecraft.getInstance().gameSettings.keyBindLeft, false);
		keyLastState.put(Minecraft.getInstance().gameSettings.keyBindRight, false);
	}

	public static void tickDodging() {
		lookupKeyToDirection.forEach((k, d) -> processDodgeKey(k, d));
	}

	public static void processDodgeKey(KeyBinding key, String direction) {
		long curTime = System.currentTimeMillis();
		long lastTime = getLastKeyTime(key);

		if (key.getKey().getKeyCode() > 0) {
			if (key.isKeyDown() && !keyLastState.get(key)) {
				if (lastTime == -1L) {
					setLastKeyTime(key, curTime);
				} else {
					if (lastTime + ConfigHandler.doubleTapTicks > curTime) {
						if(!direction.toUpperCase().equals("FORWARD") || ConfigHandler.doubleTapForwards) {
						DodgeEvent ev = new RequestDodgeEvent(Direction.valueOf(direction.toUpperCase()));
						MinecraftForge.EVENT_BUS.post(ev);}
						setLastKeyTime(key, -1L);
					} else {
						setLastKeyTime(key, curTime);
					}
				}
			}

			if (!key.isKeyDown() && keyLastState.get(key)) {
				for (Map.Entry<KeyBinding, Long> entry : keyTimesLastPressed.entrySet()) {
					if (entry.getKey() != key) {
						entry.setValue(-1L);
					}
				}
			}
			keyLastState.put(key, key.isKeyDown());
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
