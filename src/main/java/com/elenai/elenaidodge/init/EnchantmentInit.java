package com.elenai.elenaidodge.init;

import java.util.ArrayList;
import java.util.List;

import com.elenai.elenaidodge.enchantment.EnchantmentLightweight;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentInit {
public static final List<Enchantment> ENCHANTMENTS = new ArrayList<Enchantment>();
	
	public static final Enchantment LIGHTWEIGHT = new EnchantmentLightweight();
	
	@SubscribeEvent
	public void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
		event.getRegistry().registerAll(ENCHANTMENTS.toArray(new Enchantment[0]));
		
	}
}
