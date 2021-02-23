package com.elenai.elenaidodge2.util;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ModKeybinds {

	public static KeyBinding dodge;

	public static void register() {
		dodge = new KeyBinding("key.elenaidodge.dodge", GLFW.GLFW_KEY_LEFT_ALT, "key.categories.movement");

		ClientRegistry.registerKeyBinding(dodge);
	}
}
