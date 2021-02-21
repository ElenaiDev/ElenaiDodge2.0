package com.elenai.elenaidodge.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.elenai.elenaidodge.list.EnchantmentList;
import com.elenai.elenaidodge.list.PotionList;
import com.elenai.elenaidodge.network.NetworkHandler;
import com.elenai.elenaidodge.network.message.server.WeightMessageToServer;
import com.elenai.elenaidodge.util.ClientStorage;
import com.elenai.elenaidodge.util.Utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ArmorTickEventListener {

	public static double weight = 0;
	public static int intWeight = 0;
	public static List<ItemStack> previousArmor = new ArrayList<ItemStack>();
	public static List<ItemStack> currentArmor = new ArrayList<ItemStack>();
	public static boolean head = false, chest = false, legs = false, feet = false;

	@SuppressWarnings("resource")
	@SubscribeEvent
	public void onArmorUpdate(ClientTickEvent event) {

		if (event.phase == TickEvent.Phase.END && event.side.isClient() && ClientStorage.weightValues != null) {

			if (Minecraft.getInstance().player != null) {
				PlayerEntity player = Minecraft.getInstance().player;

				if (!currentArmor.isEmpty()) {
					currentArmor.clear();
				}
				currentArmor.add(player.getItemStackFromSlot(EquipmentSlotType.HEAD));
				currentArmor.add(player.getItemStackFromSlot(EquipmentSlotType.CHEST));
				currentArmor.add(player.getItemStackFromSlot(EquipmentSlotType.LEGS));
				currentArmor.add(player.getItemStackFromSlot(EquipmentSlotType.FEET));

				if (!currentArmor.equals(previousArmor) && !player.isPotionActive(PotionList.WEIGHT_EFFECT)) {
					weight = 0;
					List<String> weights = new ArrayList<String>();
					Collections.addAll(weights, ClientStorage.weightValues.split(","));
					weights.forEach(w -> {
						String[] itemValue = w.split("="); // itemValue[0] is id, itemValue[1] is weight
						
						if (player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem().getRegistryName()
								.equals(new ResourceLocation(itemValue[0]))) {
							weight += Double.valueOf(itemValue[1]);
							head = true;
						}
								
								if (player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem().getRegistryName()
										.equals(new ResourceLocation(itemValue[0]))) {
									weight += Double.valueOf(itemValue[1]);
									chest = true;
								}
								
										if (player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem().getRegistryName()
										.equals(new ResourceLocation(itemValue[0]))) {
											weight += Double.valueOf(itemValue[1]);
											legs = true;
										}
										
								
										if (player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem().getRegistryName()
										.equals(new ResourceLocation(itemValue[0]))) {
							weight += Double.valueOf(itemValue[1]);
							feet = true;
										}
					});
					

					if (!head && player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() instanceof ArmorItem) {
						ArmorItem item = (ArmorItem) player.getItemStackFromSlot(EquipmentSlotType.HEAD)
								.getItem();
						weight += Double.valueOf((item.getDamageReduceAmount()/2)*1.8);
					}
					if (!chest && player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() instanceof ArmorItem) {
						ArmorItem item = (ArmorItem) player.getItemStackFromSlot(EquipmentSlotType.CHEST)
								.getItem();
						weight += Double.valueOf((item.getDamageReduceAmount()/2)*1.8);
					}
					if (!legs && player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() instanceof ArmorItem) {
						ArmorItem item = (ArmorItem) player.getItemStackFromSlot(EquipmentSlotType.LEGS)
								.getItem();
						weight += Double.valueOf((item.getDamageReduceAmount()/2)*1.8);
					}
					if (!feet && player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() instanceof ArmorItem) {
						ArmorItem item = (ArmorItem) player.getItemStackFromSlot(EquipmentSlotType.FEET)
								.getItem();
						weight += Double.valueOf((item.getDamageReduceAmount()/2)*1.8);
					}
					head = false; chest = false; legs = false; feet = false;
					intWeight = (int) Math.floor(Double.valueOf(weight));
					intWeight -= Utils.getTotalEnchantmentLevel(EnchantmentList.LIGHTWEIGHT, player);
					if (player.isPotionActive(PotionList.ENDURANCE_EFFECT)) {
						intWeight -= (player.getActivePotionEffect(PotionList.ENDURANCE_EFFECT).getAmplifier() + 1) * 4;
					}
					if (!ClientStorage.halfFeathers) {
						ClientStorage.weight = (int) (Math.floor(intWeight / 2) * 2);
						NetworkHandler.simpleChannel
								.sendToServer(new WeightMessageToServer((int) (Math.floor(intWeight / 2) * 2)));

					} else {
						ClientStorage.weight = intWeight;
						NetworkHandler.simpleChannel.sendToServer(new WeightMessageToServer(intWeight));
					}

					previousArmor.clear();
					previousArmor.addAll(currentArmor);
				}
			}
		}
	}
}
