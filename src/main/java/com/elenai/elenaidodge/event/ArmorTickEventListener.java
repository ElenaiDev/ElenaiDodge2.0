package com.elenai.elenaidodge.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.elenai.elenaidodge.init.EnchantmentInit;
import com.elenai.elenaidodge.init.PotionInit;
import com.elenai.elenaidodge.network.PacketHandler;
import com.elenai.elenaidodge.network.message.SWeightMessage;
import com.elenai.elenaidodge.util.ClientStorage;
import com.elenai.elenaidodge.util.Utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


public class ArmorTickEventListener {

	public static double weight = 0;
	public static int intWeight = 0;
	public static List<Item> previousArmor = new ArrayList<Item>();
	public static List<Item> currentArmor = new ArrayList<Item>();

	@SubscribeEvent
	public void onArmorUpdate(TickEvent.PlayerTickEvent event) {

		if (event.phase == TickEvent.Phase.END && event.player.world.isRemote) {
			
			EntityPlayer player = event.player;
			
			if(!currentArmor.isEmpty()) {
					currentArmor.clear();
			}
			currentArmor.add(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem());
			currentArmor.add(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem());
			currentArmor.add(player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem());
			currentArmor.add(player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem());

			if(!currentArmor.equals(previousArmor) && !event.player.isPotionActive(PotionInit.WEIGHT_EFFECT)) {
				weight = 0;
				List<String> weights = new ArrayList<String>();
				Collections.addAll(weights, ClientStorage.weightValues.split(","));
				weights.forEach(w -> {
					String[] itemValue = w.split("="); // itemValue[0] is id, itemValue[1] is weight
					if (player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == Item.getByNameOrId(itemValue[0])
							|| player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Item
									.getByNameOrId(itemValue[0])
							|| player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == Item
									.getByNameOrId(itemValue[0])
							|| player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == Item
									.getByNameOrId(itemValue[0])) {
						weight += Double.valueOf(itemValue[1]); 
					}
				});
				intWeight = (int) Math.floor(Double.valueOf(weight));
				intWeight -= Utils.getTotalEnchantmentLevel(EnchantmentInit.LIGHTWEIGHT, event.player);
				if(event.player.isPotionActive(PotionInit.ENDURANCE_EFFECT)) {
					intWeight -= (event.player.getActivePotionEffect(PotionInit.ENDURANCE_EFFECT).getAmplifier()+1)*4;
				}
				if(!ClientStorage.halfFeathers) {
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
