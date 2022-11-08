package com.elenai.elenaidodge2.client;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public class KeyBinding {
	
	public static final String KEY_CATEGORY_ELENAIDODGE2 = "key.category.elenaidodge2.elenaidodge2";
	public static final String KEY_DODGE = "key.elenaidodge2.dodge";
	
	public static final KeyMapping DODGE_KEY = new KeyMapping(KEY_DODGE, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_ALT, KEY_CATEGORY_ELENAIDODGE2);
}
