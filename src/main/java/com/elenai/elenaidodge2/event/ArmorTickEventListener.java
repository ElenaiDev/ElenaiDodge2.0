package com.elenai.elenaidodge2.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.elenai.elenaidodge2.init.EnchantmentInit;
import com.elenai.elenaidodge2.init.PotionInit;
import com.elenai.elenaidodge2.integration.ConstructsArmory;
import com.elenai.elenaidodge2.network.PacketHandler;
import com.elenai.elenaidodge2.network.message.SWeightMessage;
import com.elenai.elenaidodge2.util.ClientStorage;
import com.elenai.elenaidodge2.util.Utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ArmorTickEventListener {

	public static double weight = 0;
	public static int intWeight = 0;
	public static List<ItemStack> previousArmor = new ArrayList<ItemStack>();
	public static List<ItemStack> currentArmor = new ArrayList<ItemStack>();
	public static boolean head = false, chest = false, legs = false, feet = false;

	@SubscribeEvent
	public void onArmorUpdate(TickEvent.ClientTickEvent event) {

		if (event.phase == TickEvent.Phase.END && event.side.isClient() && ClientStorage.weightValues != null) {

			EntityPlayer player = Minecraft.getMinecraft().player;
			if (player != null) {

				if (!currentArmor.isEmpty()) {
					currentArmor.clear();
				}
				currentArmor.add(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD));
				currentArmor.add(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST));
				currentArmor.add(player.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
				currentArmor.add(player.getItemStackFromSlot(EntityEquipmentSlot.FEET));

				if (!currentArmor.equals(previousArmor) && !player.isPotionActive(PotionInit.WEIGHT_EFFECT)) {
					weight = 0;
					List<String> weights = new ArrayList<String>();
					Collections.addAll(weights, ClientStorage.weightValues.split(","));
					weights.forEach(w -> {
						String[] itemValue = w.split("="); // itemValue[0] is id, itemValue[1] is weight
						if (player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == Item
								.getByNameOrId(itemValue[0])) {
							weight += Double.valueOf(itemValue[1]);
							head = true;
						}

						if (player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Item
								.getByNameOrId(itemValue[0])) {
							weight += Double.valueOf(itemValue[1]);
							chest = true;
						}

						if (player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == Item
								.getByNameOrId(itemValue[0])) {
							weight += Double.valueOf(itemValue[1]);
							legs = true;
						}

						if (player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == Item
								.getByNameOrId(itemValue[0])) {
							weight += Double.valueOf(itemValue[1]);
							feet = true;
						}
					});

						if (!head && player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemArmor) {
							ItemArmor item = (ItemArmor) player.getItemStackFromSlot(EntityEquipmentSlot.HEAD)
									.getItem();
							
							weight += Double.valueOf((item.damageReduceAmount/2)*1.8);
						}
						if (!chest && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemArmor) {
							ItemArmor item = (ItemArmor) player.getItemStackFromSlot(EntityEquipmentSlot.CHEST)
									.getItem();
							weight += Double.valueOf((item.damageReduceAmount/2)*1.8);
						}
						if (!legs && player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof ItemArmor) {
							ItemArmor item = (ItemArmor) player.getItemStackFromSlot(EntityEquipmentSlot.LEGS)
									.getItem();
							weight += Double.valueOf((item.damageReduceAmount/2)*1.8);
						}
						if (!feet && player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemArmor) {
							ItemArmor item = (ItemArmor) player.getItemStackFromSlot(EntityEquipmentSlot.FEET)
									.getItem();
							weight += Double.valueOf((item.damageReduceAmount/2)*1.8);
						}
						if(Loader.isModLoaded("conarm")) {
							if(!head) {weight += ConstructsArmory.getWeight(player, EntityEquipmentSlot.HEAD);}
							if(!chest) {weight += ConstructsArmory.getWeight(player, EntityEquipmentSlot.CHEST);}
							if(!legs) {weight += ConstructsArmory.getWeight(player, EntityEquipmentSlot.LEGS);}
							if(!feet) {weight += ConstructsArmory.getWeight(player, EntityEquipmentSlot.FEET);}
						}
					head = false; chest = false; legs = false; feet = false;
					intWeight = (int) Math.floor(Double.valueOf(weight));
					intWeight -= Utils.getTotalEnchantmentLevel(EnchantmentInit.LIGHTWEIGHT, player);
					if (player.isPotionActive(PotionInit.ENDURANCE_EFFECT)) {
						intWeight -= (player.getActivePotionEffect(PotionInit.ENDURANCE_EFFECT).getAmplifier() + 1) * 4;
					}
					if (!ClientStorage.halfFeathers) {
						ClientStorage.weight = (int) (Math.floor(intWeight / 2) * 2);
						PacketHandler.instance.sendToServer(new SWeightMessage((int) (Math.floor(intWeight / 2) * 2)));
					} else {
						ClientStorage.weight = intWeight;
						PacketHandler.instance.sendToServer(new SWeightMessage(intWeight));
					}

					previousArmor.clear();
					previousArmor.addAll(currentArmor);
				}

			}
		}
	}
}
