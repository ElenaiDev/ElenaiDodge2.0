package com.elenai.elenaidodge2.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class EnchantmentLightweight extends Enchantment{
	public EnchantmentLightweight() 
	{
		super(Rarity.UNCOMMON, EnchantmentType.ARMOR, new EquipmentSlotType[] {EquipmentSlotType.FEET, EquipmentSlotType.LEGS, EquipmentSlotType.CHEST, EquipmentSlotType.HEAD});
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) 
	{
		return 10 * enchantmentLevel;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel)
	{
		return this.getMinEnchantability(enchantmentLevel) + 10;
	}
	
	@Override
	public int getMaxLevel()
	{
		return 3;
	}
	
	@Override
	protected boolean canApplyTogether(Enchantment ench) 
	{
		return super.canApplyTogether(ench); //&& ench != Enchantments.FROST_WALKER && ench != Enchantments.DEPTH_STRIDER;
	}
}
