package com.elenai.elenaidodge2.gui;

import org.lwjgl.opengl.GL11;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.config.ConfigHandler;
import com.elenai.elenaidodge2.util.ClientStorage;
import com.elenai.elenaidodge2.util.PatronRewardHandler;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DodgeGui {

	public static ResourceLocation DODGE_ICONS = new ResourceLocation(ElenaiDodge2.MODID, "textures/gui/icons.png");
	public static ResourceLocation ADVANCED_DODGE_ICONS = new ResourceLocation(ElenaiDodge2.MODID, "textures/gui/advanced_icons.png");
	public static float alpha = 1f;
	
	@SubscribeEvent
	public void onRenderDodgeGUIEvent(RenderGameOverlayEvent.Post event) {
		if (ConfigHandler.hud && !Minecraft.getInstance().player.isCreative() && !Minecraft.getInstance().player.isSpectator()) {

			if((event.getType() == ElementType.ALL && ConfigHandler.compatHud) || (event.getType() == ElementType.FOOD && !ConfigHandler.compatHud)) {
			Minecraft.getInstance().getTextureManager().bindTexture(DODGE_ICONS);
			GlStateManager.enableBlend();
			enableAlpha(alpha);
			if(alpha > 0) {
			renderFeathers(event.getWindow().getScaledHeight(),
					event.getWindow().getScaledWidth(), ClientStorage.dodges, ClientStorage.weight,
					ClientStorage.healing, 16, 25, 34, 43, 52, 61, 70, PatronRewardHandler.localPatronTier, event);
			
			renderAbsorptionFeathers(event.getWindow().getScaledHeight(),
					event.getWindow().getScaledWidth(), ClientStorage.absorption, ClientStorage.weight,
					ClientStorage.healing, 79, 88, event);
			}
			disableAlpha(alpha);

			Minecraft.getInstance().getTextureManager().bindTexture(Screen.GUI_ICONS_LOCATION);
			GlStateManager.disableBlend();
			}
		}
	}

	@SuppressWarnings("resource")
	public static void renderFeathers(int screenHeight, int screenWidth, int dodges,
			int weight, boolean healing, int noFeather, int halfFeather, int fullFeather, int armoredFeather,
			int halfArmoredFeather, int halfMixedFeather, int mixedFeather, int patronLevel, RenderGameOverlayEvent.Post event) {
		IngameGui gui = Minecraft.getInstance().ingameGUI;
		if(patronLevel > 4) {
			Minecraft.getInstance().getTextureManager().bindTexture(ADVANCED_DODGE_ICONS);
			patronLevel -= 5;
			patronLevel *= 9;
		} else 
		if (patronLevel > 0) {
			patronLevel += 1;
			patronLevel *= 9;
		}
		
		int rows = MathHelper.ceil(dodges / 20.0F);
		if (rows <= 0) {
			rows = 1;
		}
		int rowHeight = Math.min(Math.max(10 - (rows - 2), 3), 10);
		int top = (screenHeight - ForgeIngameGui.right_height) - ((rows * rowHeight) - 10) + ConfigHandler.yOffset;

		for (int i = rows - 1; i >= 0; i--) {
			int right = (screenWidth / 2 + 82) + ConfigHandler.xOffset;
			for (int j = 1; j < 20; j += 2) {

				if (j + (i * 20) < dodges) {
					if (j + (i * 20) > weight) {
						gui.blit(event.getMatrixStack(), right, top, fullFeather, patronLevel, 9, 9);
					} else if (j + (i * 20) == weight) {
						gui.blit(event.getMatrixStack(), right, top, mixedFeather, patronLevel, 9, 9);
					} else {
						gui.blit(event.getMatrixStack(), right, top, armoredFeather, patronLevel, 9, 9);
					}

				} else if (j + (i * 20) == dodges) {
					if (j + (i * 20) > weight) {
						gui.blit(event.getMatrixStack(), right, top, halfFeather, patronLevel, 9, 9);
					} else if (j + (i * 20) == weight) {
						gui.blit(event.getMatrixStack(), right, top, halfMixedFeather, patronLevel, 9, 9);
					} else {
						gui.blit(event.getMatrixStack(), right, top, halfArmoredFeather, patronLevel, 9, 9);
					}

				} else if (j + (i * 20) > dodges) {
					gui.blit(event.getMatrixStack(), right, top, noFeather, patronLevel, 9, 9);
				}

				if (healing) {
					gui.blit(event.getMatrixStack(), right, top, 16, 9, 9, 9);
				} else
				if (ClientStorage.failed && ConfigHandler.flash) {
					gui.blit(event.getMatrixStack(), right, top, 43, 9, 9, 9);
				}
				right -= 8;
			}
			top += rowHeight;
			ForgeIngameGui.right_height += rowHeight;
		}
		if (rowHeight < 10) {
			ForgeIngameGui.right_height += (10 - rowHeight);
		}
	}
	
	@SuppressWarnings("resource")
	public static void renderAbsorptionFeathers(int screenHeight, int screenWidth, int dodges,
			int weight, boolean healing, int halfFeather, int fullFeather, RenderGameOverlayEvent.Post event) {
		IngameGui gui = Minecraft.getInstance().ingameGUI;

		int rows = MathHelper.ceil(dodges / 20.0F);
		int rowHeight = Math.min(Math.max(10 - (rows - 2), 3), 10);
		int top = (screenHeight - ForgeIngameGui.right_height) - ((rows * rowHeight) - 10) + ConfigHandler.yOffset;

		
		for (int i = rows - 1; i >= 0; i--) {
			int right = (screenWidth / 2 + 82) + ConfigHandler.xOffset;
			for (int j = 1; j < 20; j += 2) {

				if (j + (i * 20) < dodges) {
						gui.blit(event.getMatrixStack(), right, top, fullFeather, 0, 9, 9);
				} else if (j + (i * 20) == dodges) {
						gui.blit(event.getMatrixStack(), right, top, halfFeather, 0, 9, 9);
				}
				right -= 8;
			}
			top += rowHeight;
			ForgeIngameGui.right_height += rowHeight;
		}
		if (rowHeight < 10) {
			ForgeIngameGui.right_height += (10 - rowHeight);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void enableAlpha(float alpha) {
		GlStateManager.enableBlend();

		if (alpha == 1f)
			return;

		GlStateManager.color4f(1.0F, 1.0F, 1.0F, alpha);
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	@SuppressWarnings("deprecation")
	public static void disableAlpha(float alpha) {
		GlStateManager.disableBlend();

		if (alpha == 1f)
			return;

		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

}
