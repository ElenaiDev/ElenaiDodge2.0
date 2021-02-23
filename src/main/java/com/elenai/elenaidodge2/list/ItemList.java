package com.elenai.elenaidodge2.list;

import com.elenai.elenaidodge2.ElenaiDodge2;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemList {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ElenaiDodge2.MODID);
	
	public static final RegistryObject<Item> IRON_FEATHER = ITEMS.register("iron_feather",
			() -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	
	public static final RegistryObject<Item> GOLDEN_FEATHER = ITEMS.register("golden_feather",
			() -> new Item(new Item.Properties().group(ItemGroup.MISC)));
}
