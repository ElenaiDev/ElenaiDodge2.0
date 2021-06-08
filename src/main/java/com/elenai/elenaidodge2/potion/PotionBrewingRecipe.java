package com.elenai.elenaidodge2.potion;

import com.elenai.elenaidodge2.list.ItemList;
import com.elenai.elenaidodge2.list.PotionList;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class PotionBrewingRecipe {

	public PotionBrewingRecipe() {
		
		/*
		 * Naming Convention:
		 * Output Effect Type + 'BrewingRecipe' + Input Ingredient
		 */
		
		// Absorption
		BrewingRecipeRegistry.addRecipe(new AbsorptionBrewingRecipe());
		BrewingRecipeRegistry.addRecipe(new AbsorptionBrewingRecipeRedstone());
		BrewingRecipeRegistry.addRecipe(new AbsorptionBrewingRecipeGlowstone());
		BrewingRecipeRegistry.addRecipe(new AbsorptionBrewingRecipeMundane());
		BrewingRecipeRegistry.addRecipe(new AbsorptionBrewingRecipeThick());
		
		// Endurance
		BrewingRecipeRegistry.addRecipe(new EnduranceBrewingRecipe());
		BrewingRecipeRegistry.addRecipe(new EnduranceBrewingRecipeRedstone());
		BrewingRecipeRegistry.addRecipe(new EnduranceBrewingRecipeGlowstone());
		BrewingRecipeRegistry.addRecipe(new EnduranceBrewingRecipeMundane());
		BrewingRecipeRegistry.addRecipe(new EnduranceBrewingRecipeThick());
		
		// Forceful
		BrewingRecipeRegistry.addRecipe(new ForcefulBrewingRecipe());
		BrewingRecipeRegistry.addRecipe(new ForcefulBrewingRecipeRedstone());
		BrewingRecipeRegistry.addRecipe(new ForcefulBrewingRecipeGlowstone());
		BrewingRecipeRegistry.addRecipe(new ForcefulBrewingRecipeStrongSwiftness());
		BrewingRecipeRegistry.addRecipe(new ForcefulBrewingRecipeLongSwiftness());
		
		// Feeble
		BrewingRecipeRegistry.addRecipe(new FeebleBrewingRecipe());
		BrewingRecipeRegistry.addRecipe(new FeebleBrewingRecipeRedstone());
		BrewingRecipeRegistry.addRecipe(new FeebleBrewingRecipeGlowstone());
		BrewingRecipeRegistry.addRecipe(new FeebleBrewingRecipeLongSlowness());
		BrewingRecipeRegistry.addRecipe(new FeebleBrewingRecipeForceful());
		BrewingRecipeRegistry.addRecipe(new FeebleBrewingRecipeLongForceful());
		BrewingRecipeRegistry.addRecipe(new FeebleBrewingRecipeStrongForceful());
		
		// Replenishment
		BrewingRecipeRegistry.addRecipe(new ReplenishmentBrewingRecipe());
		BrewingRecipeRegistry.addRecipe(new ReplenishmentBrewingRecipeGlowstone());
		BrewingRecipeRegistry.addRecipe(new ReplenishmentBrewingRecipeRedstone());
		BrewingRecipeRegistry.addRecipe(new ReplenishmentBrewingRecipeLongRegeneration());
		BrewingRecipeRegistry.addRecipe(new ReplenishmentBrewingRecipeStrongRegeneration());
		
		// Weight
		BrewingRecipeRegistry.addRecipe(new WeightBrewingRecipe());
		BrewingRecipeRegistry.addRecipe(new WeightBrewingRecipeRedstone());
		BrewingRecipeRegistry.addRecipe(new WeightBrewingRecipeStrongEndurance());
		BrewingRecipeRegistry.addRecipe(new WeightBrewingRecipeLongEndurance());


	}
	
	public class AbsorptionBrewingRecipe implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == Potions.AWKWARD;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == ItemList.GOLDEN_FEATHER.get();
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.FEATHERS);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class AbsorptionBrewingRecipeRedstone implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == PotionList.FEATHERS;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.REDSTONE;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.LONG_FEATHERS);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class AbsorptionBrewingRecipeGlowstone implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == PotionList.FEATHERS;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.GLOWSTONE_DUST;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.STRONG_FEATHERS);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class AbsorptionBrewingRecipeMundane implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == Potions.MUNDANE;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == ItemList.GOLDEN_FEATHER.get();
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.LONG_FEATHERS);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class AbsorptionBrewingRecipeThick implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == Potions.THICK;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == ItemList.GOLDEN_FEATHER.get();
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.STRONG_FEATHERS);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class EnduranceBrewingRecipe implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == Potions.AWKWARD;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == ItemList.IRON_FEATHER.get();
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.ENDURANCE);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class EnduranceBrewingRecipeRedstone implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == PotionList.ENDURANCE;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.REDSTONE;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.LONG_ENDURANCE);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class EnduranceBrewingRecipeGlowstone implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == PotionList.ENDURANCE;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.GLOWSTONE_DUST;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.STRONG_ENDURANCE);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class EnduranceBrewingRecipeMundane implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == Potions.MUNDANE;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == ItemList.IRON_FEATHER.get();
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.LONG_ENDURANCE);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class EnduranceBrewingRecipeThick implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == Potions.THICK;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == ItemList.IRON_FEATHER.get();
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.STRONG_ENDURANCE);
			}
			return ItemStack.EMPTY;
		}
	}

	public class ForcefulBrewingRecipe implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == Potions.SWIFTNESS;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.FEATHER;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.FORCEFUL);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class ForcefulBrewingRecipeRedstone implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == PotionList.FORCEFUL;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.REDSTONE;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.LONG_FORCEFUL);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class ForcefulBrewingRecipeGlowstone implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == PotionList.FORCEFUL;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.GLOWSTONE_DUST;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.STRONG_FORCEFUL);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class ForcefulBrewingRecipeStrongSwiftness implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == Potions.STRONG_SWIFTNESS;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.FEATHER;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.STRONG_FORCEFUL);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class ForcefulBrewingRecipeLongSwiftness implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == Potions.LONG_SWIFTNESS;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.FEATHER;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.LONG_FORCEFUL);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class FeebleBrewingRecipe implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == Potions.SLOWNESS;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.FEATHER;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.FEEBLE);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class FeebleBrewingRecipeLongSlowness implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == Potions.LONG_SLOWNESS;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.FEATHER;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.LONG_FEEBLE);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class FeebleBrewingRecipeRedstone implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == PotionList.FEEBLE;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.REDSTONE;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.LONG_FEEBLE);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class FeebleBrewingRecipeGlowstone implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == PotionList.FEEBLE;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.GLOWSTONE_DUST;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.STRONG_FEEBLE);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class FeebleBrewingRecipeForceful implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == PotionList.FORCEFUL;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.FERMENTED_SPIDER_EYE;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.FEEBLE);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class FeebleBrewingRecipeLongForceful implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == PotionList.LONG_FORCEFUL;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.FERMENTED_SPIDER_EYE;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.LONG_FEEBLE);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class FeebleBrewingRecipeStrongForceful implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == PotionList.STRONG_FORCEFUL;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.FERMENTED_SPIDER_EYE;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.STRONG_FEEBLE);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class ReplenishmentBrewingRecipe implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == Potions.REGENERATION;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == ItemList.GOLDEN_FEATHER.get();
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.REPLENISHMENT);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class ReplenishmentBrewingRecipeRedstone implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == PotionList.REPLENISHMENT;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.REDSTONE;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.LONG_REPLENISHMENT);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class ReplenishmentBrewingRecipeGlowstone implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == PotionList.REPLENISHMENT;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.GLOWSTONE_DUST;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.STRONG_REPLENISHMENT);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class ReplenishmentBrewingRecipeLongRegeneration implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == Potions.LONG_REGENERATION;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == ItemList.GOLDEN_FEATHER.get();
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.LONG_REPLENISHMENT);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class ReplenishmentBrewingRecipeStrongRegeneration implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == Potions.STRONG_REGENERATION;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == ItemList.GOLDEN_FEATHER.get();
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.STRONG_REPLENISHMENT);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class WeightBrewingRecipe implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == PotionList.ENDURANCE;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.FERMENTED_SPIDER_EYE;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.WEIGHT);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class WeightBrewingRecipeRedstone implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == PotionList.WEIGHT;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.REDSTONE;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.LONG_WEIGHT);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class WeightBrewingRecipeLongEndurance implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == PotionList.LONG_ENDURANCE;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.FERMENTED_SPIDER_EYE;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.LONG_WEIGHT);
			}
			return ItemStack.EMPTY;
		}
	}
	
	public class WeightBrewingRecipeStrongEndurance implements IBrewingRecipe {

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input) == PotionList.STRONG_ENDURANCE;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.FERMENTED_SPIDER_EYE;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isIngredient(ingredient) && isInput(input)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.LONG_WEIGHT);
			}
			return ItemStack.EMPTY;
		}
	}
}
