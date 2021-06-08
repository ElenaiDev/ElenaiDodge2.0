package com.elenai.elenaidodge2.gui;

import org.lwjgl.opengl.GL11;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.ModConfig;
import com.elenai.elenaidodge2.util.ClientStorage;
import com.elenai.elenaidodge2.util.PatronRewardHandler;
import com.elenai.elenaidodge2.util.Utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DodgeGui {

	public static ResourceLocation DODGE_ICONS = new ResourceLocation(ElenaiDodge2.MODID, "textures/gui/icons.png");
	public static ResourceLocation ADVANCED_DODGE_ICONS = new ResourceLocation(ElenaiDodge2.MODID,
			"textures/gui/advanced_icons.png");
	public static float alpha = 1f;

	@SubscribeEvent
	public void onRenderDodgeGUIEvent(RenderGameOverlayEvent.Post event) {
		if (ModConfig.client.hud.hud && !Minecraft.getMinecraft().player.isCreative()
				&& !Minecraft.getMinecraft().player.isSpectator()
				&& Utils.dodgeTraitUnlocked(Minecraft.getMinecraft().player)) {

			if ((event.getType() == ElementType.ALL && ModConfig.client.hud.compatHud)
					|| (event.getType() == ElementType.FOOD && !ModConfig.client.hud.compatHud)) {
				Minecraft.getMinecraft().getTextureManager().bindTexture(DODGE_ICONS);
				GlStateManager.enableBlend();
				enableAlpha(alpha);

				if (alpha > 0) {
					renderFeathers(event.getResolution().getScaledHeight(), event.getResolution().getScaledWidth(),
							ClientStorage.dodges, ClientStorage.weight, ClientStorage.healing, 16, 25, 34, 43, 52, 61,
							70, PatronRewardHandler.localPatronTier);

					renderAbsorptionFeathers(event.getResolution().getScaledHeight(),
							event.getResolution().getScaledWidth(), ClientStorage.absorption, ClientStorage.weight,
							ClientStorage.healing, 79, 88);
				}
				disableAlpha(alpha);

				Minecraft.getMinecraft().getTextureManager().bindTexture(Gui.ICONS);
				GlStateManager.disableBlend();
			}
		}
	}

	public static void renderFeathers(int screenHeight, int screenWidth, int dodges, int weight, boolean healing,
			int noFeather, int halfFeather, int fullFeather, int armoredFeather, int halfArmoredFeather,
			int halfMixedFeather, int mixedFeather, int patronLevel) {
		GuiIngame gui = Minecraft.getMinecraft().ingameGUI;
		if (patronLevel > 4 && patronLevel != 99) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(ADVANCED_DODGE_ICONS);
			patronLevel -= 5;
			patronLevel *= 9;
		} else if (patronLevel > 0 && patronLevel != 99) {
			patronLevel += 1;
			patronLevel *= 9;
		}

		if (patronLevel == 99) {
			patronLevel = 5;
			patronLevel *= 9;
		}

		int rows = MathHelper.ceil(dodges / 20.0F);
		if (rows <= 0) {
			rows = 1;
		}
		int rowHeight = Math.min(Math.max(10 - (rows - 2), 3), 10);
		int top = (screenHeight - GuiIngameForge.right_height) - ((rows * rowHeight) - 10) + ModConfig.client.hud.yOffset;

		for (int i = rows - 1; i >= 0; i--) {
			int right = (screenWidth / 2 + 82) + ModConfig.client.hud.xOffset;
			for (int j = 1; j < 20; j += 2) {

				if (j + (i * 20) < dodges) {
					if (j + (i * 20) > weight) {
						gui.drawTexturedModalRect(right, top, fullFeather, patronLevel, 9, 9);
					} else if (j + (i * 20) == weight) {
						gui.drawTexturedModalRect(right, top, mixedFeather, patronLevel, 9, 9);
					} else {
						gui.drawTexturedModalRect(right, top, armoredFeather, patronLevel, 9, 9);
					}

				} else if (j + (i * 20) == dodges) {
					if (j + (i * 20) > weight) {
						gui.drawTexturedModalRect(right, top, halfFeather, patronLevel, 9, 9);
					} else if (j + (i * 20) == weight) {
						gui.drawTexturedModalRect(right, top, halfMixedFeather, patronLevel, 9, 9);
					} else {
						gui.drawTexturedModalRect(right, top, halfArmoredFeather, patronLevel, 9, 9);
					}

				} else if (j + (i * 20) > dodges) {
					gui.drawTexturedModalRect(right, top, noFeather, patronLevel, 9, 9);
				}

				if (healing) {
					gui.drawTexturedModalRect(right, top, 16, 9, 9, 9);
				} else if (ClientStorage.failed && ModConfig.client.hud.flash) {
					gui.drawTexturedModalRect(right, top, 43, 9, 9, 9);
				}

				right -= 8;
			}
			top += rowHeight;
			GuiIngameForge.right_height += rowHeight;
		}
		if (rowHeight < 10) {
			GuiIngameForge.right_height += (10 - rowHeight);
		}
	}

	public static void renderAbsorptionFeathers(int screenHeight, int screenWidth, int dodges, int weight,
			boolean healing, int halfFeather, int fullFeather) {
		GuiIngame gui = Minecraft.getMinecraft().ingameGUI;

		int rows = MathHelper.ceil(dodges / 20.0F);
		int rowHeight = Math.min(Math.max(10 - (rows - 2), 3), 10);
		int top = (screenHeight - GuiIngameForge.right_height) - ((rows * rowHeight) - 10) + ModConfig.client.hud.yOffset;

		for (int i = rows - 1; i >= 0; i--) {
			int right = (screenWidth / 2 + 82) + ModConfig.client.hud.xOffset;
			for (int j = 1; j < 20; j += 2) {

				if (j + (i * 20) < dodges) {
					gui.drawTexturedModalRect(right, top, fullFeather, 0, 9, 9);
				} else if (j + (i * 20) == dodges) {
					gui.drawTexturedModalRect(right, top, halfFeather, 0, 9, 9);
				}
				right -= 8;
			}
			top += rowHeight;
			GuiIngameForge.right_height += rowHeight;
		}
		if (rowHeight < 10) {
			GuiIngameForge.right_height += (10 - rowHeight);
		}
	}

	public static void enableAlpha(float alpha) {
		GlStateManager.enableBlend();

		if (alpha == 1f)
			return;

		GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}

	public static void disableAlpha(float alpha) {
		GlStateManager.disableBlend();

		if (alpha == 1f)
			return;

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	}

}
