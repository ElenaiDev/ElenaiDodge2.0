package com.elenai.elenaidodge2.gui;

import com.elenai.elenaidodge2.config.ConfigHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.toasts.TutorialToast;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class DodgeStep {

	private static final ITextComponent DODGE_TITLE = new TranslationTextComponent("tutorial.dodge.title",
			new Object[] { Tutorial.createKeybindComponent("elenaidodge.dodge") });
	private static final ITextComponent DODGE_DESCRIPTION = new TranslationTextComponent("tutorial.dodge.description",
			new Object[] { Tutorial.createKeybindComponent("forward"), Tutorial.createKeybindComponent("left"),
					Tutorial.createKeybindComponent("back"), Tutorial.createKeybindComponent("right") });

	private static final ITextComponent DOUBLE_DODGE_TITLE = new TranslationTextComponent("tutorial.doubledodge.title",
			new Object[] { Tutorial.createKeybindComponent("left"), Tutorial.createKeybindComponent("back"),
					Tutorial.createKeybindComponent("right") });
	private static final ITextComponent DOUBLE_DODGE_DESCRIPTION = new TranslationTextComponent(
			"tutorial.doubledodge.description", new Object[] { Tutorial.createKeybindComponent("elenaidodge.dodge") });
	
	private static final ITextComponent FORWARDS_DOUBLE_DODGE_TITLE = new TranslationTextComponent("tutorial.forwardsdoubledodge.title");
	private static final ITextComponent FORWARDS_DOUBLE_DODGE_DESCRIPTION = new TranslationTextComponent(
			"tutorial.forwardsdoubledodge.description",
			new Object[] { Tutorial.createKeybindComponent("forward"), Tutorial.createKeybindComponent("left"), Tutorial.createKeybindComponent("back"),
					Tutorial.createKeybindComponent("right") });


	
	public static DodgeToast moveToast;
	private static Tutorial tutorial;

	public static void show() {
		tutorial = new Tutorial(Minecraft.getInstance());

		// Add check for double tap
		if(ConfigHandler.doubleTap) {
			if(!ConfigHandler.doubleTapForwards) {
			moveToast = new DodgeToast(DodgeToast.Icons.DODGE_FEATHER, DOUBLE_DODGE_TITLE, DOUBLE_DODGE_DESCRIPTION, true);
			} else {
				moveToast = new DodgeToast(DodgeToast.Icons.DODGE_FEATHER, FORWARDS_DOUBLE_DODGE_TITLE, FORWARDS_DOUBLE_DODGE_DESCRIPTION, true);
			}
		} else {
			moveToast = new DodgeToast(DodgeToast.Icons.DODGE_FEATHER, DODGE_TITLE, DODGE_DESCRIPTION, true);
		}

		tutorial.getMinecraft().getToastGui().add(moveToast);
	}

	public static void hide() {
		if(moveToast != null) {
		moveToast.hide();
		}
	}
}
