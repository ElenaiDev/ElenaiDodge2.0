package com.elenai.elenaidodge.gui;

import com.elenai.elenaidodge.ModConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class DodgeStep {

	private static final ITextComponent DODGE_TITLE = new TextComponentTranslation("tutorial.dodge.title",
			new Object[] { Tutorial.createKeybindComponent("elenaidodge.dodge") });
	private static final ITextComponent DODGE_DESCRIPTION = new TextComponentTranslation("tutorial.dodge.description",
			new Object[] { Tutorial.createKeybindComponent("forward"), Tutorial.createKeybindComponent("left"),
					Tutorial.createKeybindComponent("back"), Tutorial.createKeybindComponent("right") });

	private static final ITextComponent DOUBLE_DODGE_TITLE = new TextComponentTranslation("tutorial.doubledodge.title",
			new Object[] { Tutorial.createKeybindComponent("left"), Tutorial.createKeybindComponent("back"),
					Tutorial.createKeybindComponent("right") });
	private static final ITextComponent DOUBLE_DODGE_DESCRIPTION = new TextComponentTranslation(
			"tutorial.doubledodge.description", new Object[] { Tutorial.createKeybindComponent("elenaidodge.dodge") });
	
	private static final ITextComponent FORWARDS_DOUBLE_DODGE_TITLE = new TextComponentTranslation("tutorial.forwardsdoubledodge.title"
			);
	private static final ITextComponent FORWARDS_DOUBLE_DODGE_DESCRIPTION = new TextComponentTranslation(
			"tutorial.forwardsdoubledodge.description", new Object[] { Tutorial.createKeybindComponent("forward"), Tutorial.createKeybindComponent("left"), Tutorial.createKeybindComponent("back"),
					Tutorial.createKeybindComponent("right") });

	public static DodgeToast moveToast;
	private static Tutorial tutorial;

	public static void show() {
		tutorial = new Tutorial(Minecraft.getMinecraft());
		if (ModConfig.client.controls.doubleTap) {
			if(ModConfig.client.controls.doubleTapForwards) {
			moveToast = new DodgeToast(DodgeToast.Icons.DODGE_BOOT, DOUBLE_DODGE_TITLE, DOUBLE_DODGE_DESCRIPTION,
					true);
			} else {
				moveToast = new DodgeToast(DodgeToast.Icons.DODGE_BOOT, FORWARDS_DOUBLE_DODGE_TITLE, FORWARDS_DOUBLE_DODGE_DESCRIPTION,
						true);
			}
		} else {
			moveToast = new DodgeToast(DodgeToast.Icons.DODGE_BOOT, DODGE_TITLE, DODGE_DESCRIPTION, true);
		}
		tutorial.getMinecraft().getToastGui().add(moveToast);
	}

	public static void hide() {
		if(moveToast != null) {
		moveToast.hide();
		}
	}
}
