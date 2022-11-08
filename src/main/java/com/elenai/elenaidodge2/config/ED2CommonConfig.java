package com.elenai.elenaidodge2.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ED2CommonConfig {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	public static final ForgeConfigSpec.ConfigValue<Integer> COOLDOWN_TIME;
	public static final ForgeConfigSpec.ConfigValue<Integer> DODGE_COST;
	public static final ForgeConfigSpec.ConfigValue<Integer> INVINCIBILITY_TICKS;

	public static final ForgeConfigSpec.ConfigValue<Double> DODGE_STRENGTH;
	public static final ForgeConfigSpec.ConfigValue<Double> DODGE_HEIGHT;

	
	public static final ForgeConfigSpec.ConfigValue<Boolean> DODGE_WHILST_AIRBORNE;
	
	//TODO: Add the tutorial back!
	static {
		BUILDER.push("Elenai Dodge 2's Config");

		COOLDOWN_TIME = BUILDER.comment("How many ticks it takes after dodging before you can dodge again")
				.define("Dodge Cooldown", 16);
		
		INVINCIBILITY_TICKS = BUILDER.comment("How many ticks the player is invincible for after dodging")
				.define("Invincibility Ticks", 6);
		
		DODGE_COST = BUILDER.comment("How many half feathers it costs to dodge")
				.define("Dodge Cost", 2);
		
		DODGE_STRENGTH = BUILDER.comment("How far the player moves when dodging")
				.define("Dodge Strength", 0.25d);
		DODGE_HEIGHT = BUILDER.comment("How high the player moves when dodging")
				.define("Dodge Height", 0.2d);
		
		
		DODGE_WHILST_AIRBORNE = BUILDER.comment("Whether the player can dodge in mid-air").define("Dodge Whilst Airborne", false);

		BUILDER.pop();
		SPEC = BUILDER.build();
	}
}
