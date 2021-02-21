package com.elenai.elenaidodge.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import com.elenai.elenaidodge.ElenaiDodge;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * This single class is based off the class PatronRewardHandler used by Vazkii in Quark:
 * https://github.com/Vazkii/Quark/blob/master/src/main/java/vazkii/quark/base/handler/PatronRewardHandler.java
 * I have changed a very small amount from their original method, and therefore all credit for this class goes to them.
 * Both Quark and Elenai Dodge are issued under an altered version of the Creative Commons license.
 * @author Vazkii
 */	
@Mod.EventBusSubscriber(modid = ElenaiDodge.MODID)
public class PatronRewardHandler {
	
	private static Thread thread;
	private static boolean doneLoading;

	private static String name;

	private static final Map<String, Integer> tiers = new HashMap<>();

	public static int localPatronTier = 0;
	
	@OnlyIn(Dist.CLIENT)
	public static void getLocalName() {
		name = Minecraft.getInstance().getSession().getUsername().toLowerCase(Locale.ROOT);
	}

	public static void init() {
		if (thread != null && thread.isAlive())
			return;
		
		doneLoading = false;
		thread = new ThreadContributorListLoader();
	}

	public static int getTier(PlayerEntity player) {
		if(getTier(player.getGameProfile().getName()) == 99) {
			return 4;
		}
		return getTier(player.getGameProfile().getName());
	}
	
	public static int getTier(String name) {
		join();
		return tiers.getOrDefault(name.toLowerCase(Locale.ROOT), 0);
	}

	@SubscribeEvent
	@OnlyIn(Dist.DEDICATED_SERVER)
	public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
		PatronRewardHandler.init();
	}
	
	private static void join() {
		if(!doneLoading)
			try {
				thread.join();
			} catch (InterruptedException e) {
				throw new RuntimeException();
			}
	}
	
	private static void load(Properties props) {
		List<String> allPatrons = new ArrayList<>(props.size());

		props.forEach((k, v) -> {
			String key = (String) k;
			String value = (String) v;
			
			int tier = Integer.parseInt(value);
			if(tier < 10)
				allPatrons.add(key);
			tiers.put(key.toLowerCase(Locale.ROOT), tier);
			
			if(name != null && key.toLowerCase(Locale.ROOT).equals(name))
				localPatronTier = tier;
		});

		doneLoading = true;
	}

	private static class ThreadContributorListLoader extends Thread {

		public ThreadContributorListLoader() {
			setName("Contributor Loading Thread");
			setDaemon(true);
			start();
		}

		@Override
		public void run() {
			try {
				URL url = new URL("https://raw.githubusercontent.com/ElenaiDev/Patrons/master/contributors.properties");
				Properties patreonTiers = new Properties();
				try (InputStreamReader reader = new InputStreamReader(url.openStream())) {
					patreonTiers.load(reader);
					load(patreonTiers);
				}
			} catch (IOException e) {
				ElenaiDodge.LOG.error("Failed to load patreon information", e);
			}
		}

	}

}
