package com.elenai.elenaidodge.event;

import com.elenai.elenaidodge.init.ItemInit;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderEventListener {
	@SubscribeEvent
	public void registerRenders(ModelRegistryEvent event) {
		registerRender(ItemInit.GOLDEN_FEATHER);
		registerRender(ItemInit.IRON_FEATHER);

	}
	
	private static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation( item.getRegistryName(), "inventory"));
	}
}
