package com.elenai.elenaidodge2.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ED2ClientConfig {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	public static final ForgeConfigSpec.ConfigValue<Boolean> DISPLAY_ANIMATION;
	public static final ForgeConfigSpec.ConfigValue<Boolean> DOUBLE_TAP_MODE;
	public static final ForgeConfigSpec.ConfigValue<Integer> DOUBLE_TAP_TICKS;
	
	static {
		BUILDER.push("Elenai Dodge 2's Config");

		DISPLAY_ANIMATION = BUILDER.comment("Whether to show dodge animations")
				.define("Dodge Animations", true);
		
		DOUBLE_TAP_MODE = BUILDER.comment("Enable this to allow for dodging by double tapping a movement key")
				.define("Double Tap Mode", false);
		
		DOUBLE_TAP_TICKS = BUILDER.comment("How many system ticks you have between double taps for them to register")
				.define("Double Tap Ticks", 215);
		
		BUILDER.pop();
		SPEC = BUILDER.build();
	}
}
