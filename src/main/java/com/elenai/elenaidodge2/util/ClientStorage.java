package com.elenai.elenaidodge2.util;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientStorage {
	
	public static boolean healing = false;
	public static int cooldown = 0;
	public static boolean failed = false;
	
	public static boolean hasAbsorption = false;
	public static boolean shownTutorial = true;
	public static double tutorialDodges = 2;
	
	public static int regenModifier = 0;
	
	// Config Values
	public static boolean halfFeathers;
	public static String weightValues;
	public static int dodges;
	public static int weight = 0;
	public static int regenSpeed;
	public static int absorption = 0;


}
