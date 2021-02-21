package com.elenai.elenaidodge.integration;

import c4.conarm.common.armor.utils.ArmorHelper;
import c4.conarm.lib.armor.ArmorCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class ConstructsArmory {

	public static double getWeight(EntityPlayer player, EntityEquipmentSlot slot) {
		switch (slot) {
		case HEAD:
			if (player.getItemStackFromSlot(EntityEquipmentSlot.HEAD)
					.getItem() instanceof c4.conarm.common.items.armor.Helmet) {
				ItemStack item = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
				return Double.valueOf((ArmorHelper.getDefense(item) / 8) * 1.8);
			}
			break;
		case CHEST:
			if (player.getItemStackFromSlot(EntityEquipmentSlot.CHEST)
					.getItem() instanceof c4.conarm.common.items.armor.Chestplate) {
				ItemStack item = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
				return Double.valueOf((ArmorHelper.getDefense(item) / 8) * 1.8);
			}
			break;
		case LEGS:
			if (player.getItemStackFromSlot(EntityEquipmentSlot.LEGS)
					.getItem() instanceof c4.conarm.common.items.armor.Leggings) {
				ItemStack item = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
				return Double.valueOf((ArmorHelper.getDefense(item) / 8) * 1.8);
			}
			break;
		case FEET:
			if (player.getItemStackFromSlot(EntityEquipmentSlot.FEET)
					.getItem() instanceof c4.conarm.common.items.armor.Boots) {
				ItemStack item = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
				return Double.valueOf((ArmorHelper.getDefense(item) / 8) * 1.8);
			}
			break;
		default:
			break;
		}
		return 0;
	}

	public static double getWeight(ItemStack item) {
		if (item.getItem() instanceof ArmorCore) {
			return Double.valueOf((ArmorHelper.getDefense(item) / 8) * 1.8);
		}
		return 0;
	}

}
