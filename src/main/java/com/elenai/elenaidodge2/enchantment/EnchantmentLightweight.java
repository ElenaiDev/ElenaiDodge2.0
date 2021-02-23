package com.elenai.elenaidodge2.enchantment;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.init.EnchantmentInit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class EnchantmentLightweight extends Enchantment{
	public EnchantmentLightweight() 
	{
		super(Rarity.UNCOMMON, EnumEnchantmentType.ARMOR, new EntityEquipmentSlot[] {EntityEquipmentSlot.FEET, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.HEAD});
		this.setName("lightweight");
		this.setRegistryName(new ResourceLocation(ElenaiDodge2.MODID + ":lightweight"));
		
		EnchantmentInit.ENCHANTMENTS.add(this);
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
