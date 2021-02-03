package com.elenai.elenaidodge.integration;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;

@JEIPlugin
public class JEIDodgePlugin implements IModPlugin {
	
	/*
	 * This class does nothing yet, however it will be used if I ever add a logo item.
	 */
	
	@Override
	public void register(IModRegistry registry) {
		//IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
		//blacklist.addIngredientToBlacklist(new ItemStack(ItemInit.FEATHER));
		//blacklist.addIngredientToBlacklist(new ItemStack(ItemInit.ABSORP_FEATHER));
		//blacklist.addIngredientToBlacklist(new ItemStack(ItemInit.STEEL_FEATHER));
		//blacklist.addIngredientToBlacklist(new ItemStack(ItemInit.MIXED_FEATHER));
	}

}
