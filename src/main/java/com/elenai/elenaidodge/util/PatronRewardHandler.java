package com.elenai.elenaidodge.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.WeakHashMap;

import com.elenai.elenaidodge.ElenaiDodge;
import com.google.common.collect.ImmutableSet;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This single class is based off the class PatronRewardHandler used by Vazkii in Quark:
 * https://github.com/Vazkii/Quark/blob/master/src/main/java/vazkii/quark/base/handler/PatronRewardHandler.java
 * I have changed a very small amount from their original method, and therefore all credit for this class goes to them.
 * Both Quark and Elenai Dodge are issued under an altered version of the Creative Commons license.
 * @author Vazkii
 */	
@Mod.EventBusSubscriber(modid = ElenaiDodge.MODID)
public class PatronRewardHandler {

	private static final ImmutableSet<String> DEV_UUID = ImmutableSet.of(
			"1f12bbe0-0396-458e-8bb6-49bffdeb46dd",
			"2436164d-96a1-4fe8-8c0c-a118ff2d9157",
			"c4c40239-2e88-42d3-a5bb-806ed8c21845",
			"5084e6f3-8f54-43f1-8df5-1dca109e430f");

	private static final Set<String> done = Collections.newSetFromMap(new WeakHashMap<>());

	private static Thread thread;
	private static String name;
	private static final Map<String, Integer> tiers = new HashMap<>();
	public static int localPatronTier = 0;

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onRenderPlayer(RenderPlayerEvent.Post event) {
		EntityPlayer player = event.getEntityPlayer();
		String uuid = EntityPlayer.getUUID(player.getGameProfile()).toString();
		if(player instanceof AbstractClientPlayer && DEV_UUID.contains(uuid) && !done.contains(uuid)) {
			AbstractClientPlayer clientPlayer = (AbstractClientPlayer) player;
			if(clientPlayer.hasPlayerInfo()) {
				NetworkPlayerInfo info = ObfuscationReflectionHelper.getPrivateValue(AbstractClientPlayer.class, clientPlayer, "field_175157_a");
				Map<Type, ResourceLocation> textures = ObfuscationReflectionHelper.getPrivateValue(NetworkPlayerInfo.class, info, "field_187107_a");
				ResourceLocation loc = new ResourceLocation(ElenaiDodge.MODID, "textures/misc/dev_cape.png");
				textures.put(Type.CAPE, loc);
				textures.put(Type.ELYTRA, loc);
				done.add(uuid);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void setupClient() {
		name = Minecraft.getMinecraft().getSession().getUsername().toLowerCase();
	}

	public static void init() {
		if (thread != null && thread.isAlive()) {
			return; }
		thread = new ThreadContributorListLoader();
	}

	public static int getTier(EntityPlayer player) {
		if(getTier(player.getName()) == 99) {
			return 4;
		}
		return getTier(player.getName());
	}
	
	public static int getTier(String name) {
		return tiers.getOrDefault(name.toLowerCase(Locale.ROOT), 0);
	}

	@SubscribeEvent
	public static void onPlayerJoin(EntityJoinWorldEvent event) {
		if(event.getEntity() instanceof EntityPlayer) {
		PatronRewardHandler.init();
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
	}

	private static class ThreadContributorListLoader extends Thread {

		public ThreadContributorListLoader() {
			setName("Elenai Dodge Patron Loading Thread");
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


