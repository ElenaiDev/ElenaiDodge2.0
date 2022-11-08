package com.elenai.elenaidodge2.client;

import com.elenai.elenaidodge2.config.ED2ClientConfig;

public class ED2ClientStorage {

	private static int cooldown;
	private static int cost;
	private static boolean airborne;
	private static double power;
	private static double verticality;
	private static boolean animating = ED2ClientConfig.DISPLAY_ANIMATION.get();
	
	public static int getCooldown() {
		return cooldown;
	}
	public static void setCooldown(int cooldown) {
		ED2ClientStorage.cooldown = cooldown;
	}
	public static int getCost() {
		return cost;
	}
	public static void setCost(int cost) {
		ED2ClientStorage.cost = cost;
	}
	public static boolean allowAirborne() {
		return airborne;
	}
	public static void setAirborne(boolean airborne) {
		ED2ClientStorage.airborne = airborne;
	}
	public static double getVerticality() {
		return verticality;
	}
	public static void setVerticality(double verticality) {
		ED2ClientStorage.verticality = verticality;
	}
	public static double getPower() {
		return power;
	}
	public static void setPower(double power) {
		ED2ClientStorage.power = power;
	}
	public static boolean isAnimating() {
		return animating;
	}
	
	
}
