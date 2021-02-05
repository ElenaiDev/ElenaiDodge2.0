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

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class ArmorTickEventListener {

	public static double weight = 0;
	public static int intWeight = 0;
	public static List<Item> previousArmor = new ArrayList<Item>();
	public static List<Item> currentArmor = new ArrayList<Item>();

	@SubscribeEvent
	public void onArmorUpdate(PlayerTickEvent event) {

		if (event.phase == TickEvent.Phase.END && event.side.isClient()) {
			
			PlayerEntity player = event.player;
			
			if(!currentArmor.isEmpty()) {
					currentArmor.clear();
			}
			currentArmor.add(player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem());
			currentArmor.add(player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem());
			currentArmor.add(player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem());
			currentArmor.add(player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem());

			if(!currentArmor.equals(previousArmor) && !event.player.isPotionActive(PotionList.WEIGHT_EFFECT)) {
				weight = 0;
				List<String> weights = new ArrayList<String>();
				Collections.addAll(weights, ClientStorage.weightValues.split(","));
				weights.forEach(w -> {
					String[] itemValue = w.split("="); // itemValue[0] is id, itemValue[1] is weight
					if (player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem().getRegistryName().equals(new ResourceLocation(itemValue[0]))
							|| player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem().getRegistryName().equals(new ResourceLocation(itemValue[0]))
							|| player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem().getRegistryName().equals(new ResourceLocation(itemValue[0]))
							|| player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem().getRegistryName().equals(new ResourceLocation(itemValue[0]))) {
						weight += Double.valueOf(itemValue[1]); 
					}
				});
				intWeight = (int) Math.floor(Double.valueOf(weight));
				intWeight -= Utils.getTotalEnchantmentLevel(EnchantmentList.LIGHTWEIGHT, event.player);
				if(event.player.isPotionActive(PotionList.ENDURANCE_EFFECT)) {
					intWeight -= (event.player.getActivePotionEffect(PotionList.ENDURANCE_EFFECT).getAmplifier()+1)*4;
				}
				if(!ClientStorage.halfFeathers) {
				ClientStorage.weight = (int) (Math.floor(intWeight / 2) * 2);
				NetworkHandler.simpleChannel.sendToServer(new WeightMessageToServer((int) (Math.floor(intWeight / 2) * 2)));

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
