package com.elenai.elenaidodge2.list;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class PotionList {

	public static Potion FEATHERS = null;
	public static Potion LONG_FEATHERS = null;
	public static Potion STRONG_FEATHERS = null;
	
	public static Potion WEIGHT = null;
	public static Potion LONG_WEIGHT = null;
	
	public static Potion ENDURANCE = null;
	public static Potion LONG_ENDURANCE = null;
	public static Potion STRONG_ENDURANCE = null;
	
	public static Potion FORCEFUL = null;
	public static Potion LONG_FORCEFUL = null;
	public static Potion STRONG_FORCEFUL = null;
	
	public static Potion FEEBLE = null;
	public static Potion LONG_FEEBLE = null;
	public static Potion STRONG_FEEBLE = null;

	public static Potion REPLENISHMENT = null;
	public static Potion LONG_REPLENISHMENT = null;
	public static Potion STRONG_REPLENISHMENT = null;
	
	public static Effect FEATHERS_EFFECT = null;
	public static Effect WEIGHT_EFFECT = null;
	public static Effect ENDURANCE_EFFECT = null;
	public static Effect FORCEFUL_EFFECT = null;
	public static Effect FEEBLE_EFFECT = null;
	public static Effect REPLENISHMENT_EFFECT = null;
	
	public static void addRecipes() {
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), FEATHERS)),
				Ingredient.fromStacks(new ItemStack(Items.REDSTONE)), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),LONG_FEATHERS));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), FEATHERS)),
				Ingredient.fromStacks(new ItemStack(Items.GLOWSTONE_DUST)), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),STRONG_FEATHERS));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.AWKWARD)),
				Ingredient.fromStacks(new ItemStack(ItemList.GOLDEN_FEATHER.get())), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),FEATHERS));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.THICK)),
				Ingredient.fromStacks(new ItemStack(ItemList.GOLDEN_FEATHER.get())), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),STRONG_FEATHERS));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.MUNDANE)),
				Ingredient.fromStacks(new ItemStack(ItemList.GOLDEN_FEATHER.get())), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),LONG_FEATHERS));
		
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), ENDURANCE)),
				Ingredient.fromStacks(new ItemStack(Items.REDSTONE)), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),LONG_ENDURANCE));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), ENDURANCE)),
				Ingredient.fromStacks(new ItemStack(Items.GLOWSTONE_DUST)), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),STRONG_ENDURANCE));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.AWKWARD)),
				Ingredient.fromStacks(new ItemStack(ItemList.IRON_FEATHER.get())), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),ENDURANCE));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.THICK)),
				Ingredient.fromStacks(new ItemStack(ItemList.IRON_FEATHER.get())), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),STRONG_ENDURANCE));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.MUNDANE)),
				Ingredient.fromStacks(new ItemStack(ItemList.IRON_FEATHER.get())), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),LONG_ENDURANCE));
		
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), ENDURANCE)),
				Ingredient.fromStacks(new ItemStack(Items.FERMENTED_SPIDER_EYE)), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),WEIGHT));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), STRONG_ENDURANCE)),
				Ingredient.fromStacks(new ItemStack(Items.FERMENTED_SPIDER_EYE)), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),LONG_WEIGHT));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), LONG_ENDURANCE)),
				Ingredient.fromStacks(new ItemStack(Items.FERMENTED_SPIDER_EYE)), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),LONG_WEIGHT));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), WEIGHT)),
				Ingredient.fromStacks(new ItemStack(Items.REDSTONE)), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),LONG_WEIGHT));
		
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.SWIFTNESS)),
				Ingredient.fromStacks(new ItemStack(Items.FEATHER)), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),FORCEFUL));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.LONG_SWIFTNESS)),
				Ingredient.fromStacks(new ItemStack(Items.FEATHER)), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),LONG_FORCEFUL));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.STRONG_SWIFTNESS)),
				Ingredient.fromStacks(new ItemStack(Items.FEATHER)), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),STRONG_FORCEFUL));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), FORCEFUL)),
				Ingredient.fromStacks(new ItemStack(Items.REDSTONE)), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),LONG_FORCEFUL));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), FORCEFUL)),
				Ingredient.fromStacks(new ItemStack(Items.GLOWSTONE_DUST)), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),STRONG_FORCEFUL));
		
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.SLOWNESS)),
				Ingredient.fromStacks(new ItemStack(Items.FEATHER)), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),FEEBLE));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.LONG_SLOWNESS)),
				Ingredient.fromStacks(new ItemStack(Items.FEATHER)), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),LONG_FEEBLE));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), FEEBLE)),
				Ingredient.fromStacks(new ItemStack(Items.REDSTONE)), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),LONG_FEEBLE));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), FEEBLE)),
				Ingredient.fromStacks(new ItemStack(Items.GLOWSTONE_DUST)), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),STRONG_FEEBLE));

		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.REGENERATION)),
				Ingredient.fromStacks(new ItemStack(ItemList.GOLDEN_FEATHER.get())), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),REPLENISHMENT));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.LONG_REGENERATION)),
				Ingredient.fromStacks(new ItemStack(ItemList.GOLDEN_FEATHER.get())), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),LONG_REPLENISHMENT));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.STRONG_REGENERATION)),
				Ingredient.fromStacks(new ItemStack(ItemList.GOLDEN_FEATHER.get())), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),STRONG_REPLENISHMENT));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), REPLENISHMENT)),
				Ingredient.fromStacks(new ItemStack(Items.REDSTONE)), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),LONG_REPLENISHMENT));
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), REPLENISHMENT)),
				Ingredient.fromStacks(new ItemStack(Items.GLOWSTONE_DUST)), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),STRONG_REPLENISHMENT));
		
	}
	
}
