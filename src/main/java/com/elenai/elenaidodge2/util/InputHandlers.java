package com.elenai.elenaidodge2.util;

import java.util.HashMap;
import java.util.Map;

import com.elenai.elenaidodge2.client.animation.DodgeAnimator.DodgeDirection;
import com.elenai.elenaidodge2.config.ED2ClientConfig;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

public class InputHandlers {

	/**
	 * SINGLE TAP INPUT
	 */
	public static void singleTapHandler() {
		Minecraft instance = Minecraft.getInstance();
		if (instance.player.input.leftImpulse > 0 && instance.player.input.forwardImpulse > 0) {
			DodgeHandler.handleDodge(DodgeDirection.FORWARDS_LEFT);
		} else if(instance.player.input.leftImpulse < 0 && instance.player.input.forwardImpulse > 0) {
			DodgeHandler.handleDodge(DodgeDirection.FORWARDS_RIGHT);
		} else if(instance.player.input.leftImpulse > 0 && instance.player.input.forwardImpulse < 0) {
			DodgeHandler.handleDodge(DodgeDirection.BACKWARDS_LEFT);
		} else if(instance.player.input.leftImpulse < 0 && instance.player.input.forwardImpulse < 0) {
			DodgeHandler.handleDodge(DodgeDirection.BACKWARDS_RIGHT);
		}else if (instance.player.input.leftImpulse > 0) {
			DodgeHandler.handleDodge(DodgeDirection.LEFT);
		} else if (instance.player.input.leftImpulse < 0) {
			DodgeHandler.handleDodge(DodgeDirection.RIGHT);
		} else if (instance.player.input.forwardImpulse > 0) {
			DodgeHandler.handleDodge(DodgeDirection.FORWARDS);
		} else {
			DodgeHandler.handleDodge(DodgeDirection.BACKWARDS);
		}
	}
	
	
	/**
	 * DOUBLE TAP INPUT
	 */
	public static boolean needsInit = true;
	public static HashMap<KeyMapping, Long> keyTimesLastPressed = new HashMap<>();
	public static HashMap<KeyMapping, Boolean> keyLastState = new HashMap<>();
	public static HashMap<KeyMapping, String> lookupKeyToDirection = new HashMap<>();
	
	public static void doubleTapInputHandler() {
		if (needsInit) {
			needsInit = false;
			tickInit();
		}
		tickDodging();
	}
	
	public static void tickInit() {
		Minecraft instance = Minecraft.getInstance();
		lookupKeyToDirection.put(instance.options.keyUp, "forwards");
		lookupKeyToDirection.put(instance.options.keyDown, "backwards");
		lookupKeyToDirection.put(instance.options.keyLeft, "left");
		lookupKeyToDirection.put(instance.options.keyRight, "right");
		keyLastState.put(instance.options.keyUp, false);
		keyLastState.put(instance.options.keyDown, false);
		keyLastState.put(instance.options.keyLeft, false);
		keyLastState.put(instance.options.keyRight, false);
	}
	
	public static void tickDodging() {
		lookupKeyToDirection.forEach((k, d) -> processDodgeKey(k, d));
	}

	public static void processDodgeKey(KeyMapping key, String direction) {
		long curTime = System.currentTimeMillis();
		long lastTime = getLastKeyTime(key);

		if (key.getKey().getValue() > 0) {
			if (key.isDown() && !keyLastState.get(key)) {
				if (lastTime == -1L) {
					setLastKeyTime(key, curTime);
				} else {
					if (lastTime + ED2ClientConfig.DOUBLE_TAP_TICKS.get() > curTime) {
						DodgeHandler.handleDodge(DodgeDirection.valueOf(direction.toUpperCase()));
						setLastKeyTime(key, -1L);
					} else {
						setLastKeyTime(key, curTime);
					}
				}
			}

			if (!key.isDown() && keyLastState.get(key)) {
				for (Map.Entry<KeyMapping, Long> entry : keyTimesLastPressed.entrySet()) {
					if (entry.getKey() != key) {
						entry.setValue(-1L);
					}
				}
			}
			keyLastState.put(key, key.isDown());
		}
	}

	public static long getLastKeyTime(KeyMapping keybind) {
		if (!keyTimesLastPressed.containsKey(keybind)) {
			keyTimesLastPressed.put(keybind, -1L);
		}
		return keyTimesLastPressed.get(keybind);
	}

	public static void setLastKeyTime(KeyMapping keybind, long time) {
		keyTimesLastPressed.put(keybind, time);
	}
	
}
