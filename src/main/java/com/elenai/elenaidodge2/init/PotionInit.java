package com.elenai.elenaidodge2.init;

import com.elenai.elenaidodge2.potions.AbsorptionPotion;
import com.elenai.elenaidodge2.potions.BasePotion;
import com.elenai.elenaidodge2.potions.EndurancePotion;
import com.elenai.elenaidodge2.potions.RegenPotion;
import com.elenai.elenaidodge2.potions.WeightPotion;

import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionInit {
	public static final Potion FEATHERS_EFFECT = new AbsorptionPotion("feathers", false, 13882323, 0, 0);
	public static final Potion WEIGHT_EFFECT = new WeightPotion("weight", true, 5533805, 0, 1);
	public static final Potion ENDURANCE_EFFECT = new EndurancePotion("endurance", false, 9318976, 1, 0);
	public static final Potion FORCEFUL_EFFECT = new BasePotion("forceful", false, 5534118, 2, 0);
	public static final Potion FEEBLE_EFFECT = new BasePotion("feeble", true, 10693147, 1, 1);
	public static final Potion RENEWAL_EFFECT = new RegenPotion("renewal", false, 3093071, 2, 1);
	
	public static final PotionType FEATHERS = new PotionType("feathers", new PotionEffect[] {new PotionEffect(FEATHERS_EFFECT, 3600)}).setRegistryName("feathers");
	public static final PotionType LONG_FEATHERS = new PotionType("feathers", new PotionEffect[] {new PotionEffect(FEATHERS_EFFECT, 9600)}).setRegistryName("long_feathers");
	public static final PotionType STRONG_FEATHERS = new PotionType("feathers", new PotionEffect[] {new PotionEffect(FEATHERS_EFFECT, 1800, 1)}).setRegistryName("strong_feathers");

	public static final PotionType WEIGHT = new PotionType("weight", new PotionEffect[] {new PotionEffect(WEIGHT_EFFECT, 1800)}).setRegistryName("weight");
	public static final PotionType LONG_WEIGHT = new PotionType("weight", new PotionEffect[] {new PotionEffect(WEIGHT_EFFECT, 3600)}).setRegistryName("long_weight");

	public static final PotionType ENDURANCE = new PotionType("endurance", new PotionEffect[] {new PotionEffect(ENDURANCE_EFFECT, 3600)}).setRegistryName("endurance");
	public static final PotionType LONG_ENDURANCE = new PotionType("endurance", new PotionEffect[] {new PotionEffect(ENDURANCE_EFFECT, 9600)}).setRegistryName("long_endurance");
	public static final PotionType STRONG_ENDURANCE = new PotionType("endurance", new PotionEffect[] {new PotionEffect(ENDURANCE_EFFECT, 1800, 1)}).setRegistryName("strong_endurance");

	public static final PotionType FORCEFUL = new PotionType("forceful", new PotionEffect[] {new PotionEffect(FORCEFUL_EFFECT, 3600)}).setRegistryName("forceful");
	public static final PotionType LONG_FORCEFUL = new PotionType("forceful", new PotionEffect[] {new PotionEffect(FORCEFUL_EFFECT, 9600)}).setRegistryName("long_forceful");
	public static final PotionType STRONG_FORCEFUL = new PotionType("forceful", new PotionEffect[] {new PotionEffect(FORCEFUL_EFFECT, 1800, 1)}).setRegistryName("strong_forceful");
	
	public static final PotionType FEEBLE = new PotionType("feeble", new PotionEffect[] {new PotionEffect(FEEBLE_EFFECT, 3600)}).setRegistryName("feeble");
	public static final PotionType LONG_FEEBLE = new PotionType("feeble", new PotionEffect[] {new PotionEffect(FEEBLE_EFFECT, 9600)}).setRegistryName("long_feeble");
	public static final PotionType STRONG_FEEBLE = new PotionType("feeble", new PotionEffect[] {new PotionEffect(FEEBLE_EFFECT, 1800, 1)}).setRegistryName("strong_feeble");
	
	public static final PotionType RENEWAL = new PotionType("renewal", new PotionEffect[] {new PotionEffect(RENEWAL_EFFECT, 3600)}).setRegistryName("renewal");
	public static final PotionType LONG_RENEWAL = new PotionType("renewal", new PotionEffect[] {new PotionEffect(RENEWAL_EFFECT, 9600)}).setRegistryName("long_renewal");
	public static final PotionType STRONG_RENEWAL = new PotionType("renewal", new PotionEffect[] {new PotionEffect(RENEWAL_EFFECT, 1800, 1)}).setRegistryName("strong_renewal");
	
	public static void registerPotions() {
		registerPotion(FEATHERS, LONG_FEATHERS, STRONG_FEATHERS, FEATHERS_EFFECT);
		registerPotion(WEIGHT, LONG_WEIGHT, WEIGHT_EFFECT);
		registerPotion(ENDURANCE, LONG_ENDURANCE, STRONG_ENDURANCE, ENDURANCE_EFFECT);
		registerPotion(FORCEFUL, LONG_FORCEFUL, STRONG_FORCEFUL, FORCEFUL_EFFECT);
		registerPotion(FEEBLE, LONG_FEEBLE, STRONG_FEEBLE, FEEBLE_EFFECT);
		registerPotion(RENEWAL, LONG_RENEWAL, STRONG_RENEWAL, RENEWAL_EFFECT);
		
		registerPotionMixes();
	}
	
	private static void registerPotion(PotionType defaultPotion, PotionType longPotion, PotionType strongPotion, Potion effect) {
		ForgeRegistries.POTIONS.register(effect);
		ForgeRegistries.POTION_TYPES.register(defaultPotion);
		ForgeRegistries.POTION_TYPES.register(longPotion);
		ForgeRegistries.POTION_TYPES.register(strongPotion);
	}
	
	private static void registerPotion(PotionType defaultPotion, PotionType longPotion, Potion effect) {
		ForgeRegistries.POTIONS.register(effect);
		ForgeRegistries.POTION_TYPES.register(defaultPotion);
		ForgeRegistries.POTION_TYPES.register(longPotion);
	}
	
	private static void registerPotionMixes() {
		PotionHelper.addMix(FEATHERS, Items.REDSTONE, LONG_FEATHERS);
		PotionHelper.addMix(FEATHERS, Items.GLOWSTONE_DUST, STRONG_FEATHERS);
		PotionHelper.addMix(PotionTypes.AWKWARD,ItemInit.GOLDEN_FEATHER, FEATHERS);
		PotionHelper.addMix(PotionTypes.THICK, ItemInit.GOLDEN_FEATHER, STRONG_FEATHERS);
		PotionHelper.addMix(PotionTypes.MUNDANE, ItemInit.GOLDEN_FEATHER, LONG_FEATHERS);
		
		PotionHelper.addMix(ENDURANCE, Items.REDSTONE, LONG_ENDURANCE);
		PotionHelper.addMix(ENDURANCE, Items.GLOWSTONE_DUST, STRONG_ENDURANCE);
		PotionHelper.addMix(PotionTypes.AWKWARD, ItemInit.IRON_FEATHER, ENDURANCE);
		PotionHelper.addMix(PotionTypes.THICK, ItemInit.IRON_FEATHER, STRONG_ENDURANCE);
		PotionHelper.addMix(PotionTypes.MUNDANE, ItemInit.IRON_FEATHER, LONG_ENDURANCE);
		
		PotionHelper.addMix(ENDURANCE, Items.FERMENTED_SPIDER_EYE, WEIGHT);
		PotionHelper.addMix(STRONG_ENDURANCE, Items.FERMENTED_SPIDER_EYE, LONG_WEIGHT);
		PotionHelper.addMix(LONG_ENDURANCE, Items.FERMENTED_SPIDER_EYE, LONG_WEIGHT);
		PotionHelper.addMix(WEIGHT, Items.REDSTONE, LONG_WEIGHT);
		
		PotionHelper.addMix(PotionTypes.SWIFTNESS, Items.FEATHER, FORCEFUL);
		PotionHelper.addMix(PotionTypes.LONG_SWIFTNESS, ItemInit.IRON_FEATHER, LONG_FORCEFUL);
		PotionHelper.addMix(PotionTypes.STRONG_SWIFTNESS, ItemInit.IRON_FEATHER, STRONG_FORCEFUL);
		PotionHelper.addMix(FORCEFUL, Items.REDSTONE, LONG_FORCEFUL);
		PotionHelper.addMix(FORCEFUL, Items.GLOWSTONE_DUST, STRONG_FORCEFUL);
		
		PotionHelper.addMix(PotionTypes.REGENERATION, ItemInit.GOLDEN_FEATHER, RENEWAL);
		PotionHelper.addMix(PotionTypes.LONG_REGENERATION, ItemInit.GOLDEN_FEATHER, LONG_RENEWAL);
		PotionHelper.addMix(PotionTypes.STRONG_REGENERATION, ItemInit.GOLDEN_FEATHER, STRONG_RENEWAL);
		PotionHelper.addMix(RENEWAL, Items.REDSTONE, LONG_RENEWAL);
		PotionHelper.addMix(RENEWAL, Items.GLOWSTONE_DUST, STRONG_RENEWAL);
		
		PotionHelper.addMix(PotionTypes.SLOWNESS, ItemInit.IRON_FEATHER, FEEBLE);
		PotionHelper.addMix(PotionTypes.LONG_SLOWNESS, ItemInit.IRON_FEATHER, LONG_FEEBLE);
		PotionHelper.addMix(FEEBLE, Items.REDSTONE, LONG_FEEBLE);
		PotionHelper.addMix(FEEBLE, Items.GLOWSTONE_DUST, STRONG_FEEBLE);
	}
	
}
