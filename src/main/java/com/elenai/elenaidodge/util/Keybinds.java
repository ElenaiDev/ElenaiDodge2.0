package com.elenai.elenaidodge.util;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class Keybinds {
	public static KeyBinding dodge;

	public static void register() {
		dodge = new KeyBinding("key.elenaidodge.dodge", Keyboard.KEY_LMENU, "key.categories.movement");

		ClientRegistry.registerKeyBinding(dodge);
	}
}
