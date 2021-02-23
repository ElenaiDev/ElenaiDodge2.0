package com.elenai.elenaidodge2.config;

import org.apache.commons.lang3.tuple.Pair;

import com.elenai.elenaidodge2.ElenaiDodge2;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;

@EventBusSubscriber(modid = ElenaiDodge2.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class ConfigHandler {

	public static class Client {

		public final ForgeConfigSpec.BooleanValue doubleTap;
		public final ForgeConfigSpec.IntValue doubleTapTicks;
		public final ForgeConfigSpec.BooleanValue doubleTapForwards;
		public final ForgeConfigSpec.BooleanValue hud;
		public final ForgeConfigSpec.BooleanValue compatHud;
		public final ForgeConfigSpec.BooleanValue tutorial;
		public final ForgeConfigSpec.BooleanValue tooltips;


		public Client(ForgeConfigSpec.Builder builder) {

			// CONTROLS
			doubleTap = builder.comment("Enable to make dodging left, right or backwards require a double tap,"
					+ " and forwards require a tap of the dodge button.").define("controls.double_tap", false);
			doubleTapTicks = builder.comment(
					"How many system ticks you have between double tapping (these are faster than Minecraft ticks).")
					.defineInRange("controls.double_tap_ticks", 200, 1, Integer.MAX_VALUE);
			doubleTapForwards = builder.comment("Enable to make dodging forwards require a double tap (Must have double tap mode enabled)"
					).define("controls.double_tap_forwards", false);

			// HUD
			hud = builder.comment(
					"Whether to show the feathers in the UI.")
					.define("hud.show_hud", true);
			compatHud = builder.comment(
					"Whether to enable compatibility hud. This will fix issues with the dodge bar disappearing in some instances, but is disabled as it may cause it to look out of place in others.")
					.define("hud.compatibility_hud", false);

			tutorial = builder
					.comment("Whether to show the tutorial on joining a new world.")
					.define("hud.tutorial", true);
			tooltips = builder.comment(
					"Whether to show armor weight tooltips. If Quark is installed, please restart the game after disabling or enabling 'Quark Settings -> Client -> Visual Stat Display'.")
					.define("hud.tooltips", true);

		}
	}

	public static final Client CLIENT;
	public static final ForgeConfigSpec CLIENT_SPEC;
	static {
		final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
		CLIENT_SPEC = specPair.getRight();
		CLIENT = specPair.getLeft();

	}

	public static class Common {

		public final ForgeConfigSpec.DoubleValue force;
		public final ForgeConfigSpec.DoubleValue verticality;
		public final ForgeConfigSpec.DoubleValue exhaustion;
		public final ForgeConfigSpec.IntValue hunger;
		public final ForgeConfigSpec.BooleanValue enableWhilstSneaking;
		public final ForgeConfigSpec.BooleanValue enableWhilstBlocking;
		public final ForgeConfigSpec.BooleanValue enableWhilstAirborne;
		public final ForgeConfigSpec.IntValue invincibilityTicks;
		public final ForgeConfigSpec.ConfigValue<String> potions;
		
		public final ForgeConfigSpec.IntValue cost;
		public final ForgeConfigSpec.IntValue rate;
		public final ForgeConfigSpec.BooleanValue half;
		
		public final ForgeConfigSpec.ConfigValue<String> weights;

		public final ForgeConfigSpec.BooleanValue message;
		public final ForgeConfigSpec.BooleanValue nether;
		public final ForgeConfigSpec.BooleanValue end;

		public final ForgeConfigSpec.DoubleValue tanCost;
		
		public Common(ForgeConfigSpec.Builder builder) {

			// BALANCE
			force = builder
					.comment("The force of the player's dodge before any multipliers have been applied. This value is very sensitive.")
					.defineInRange("balance.force", 0.6, 0.0, Double.MAX_VALUE);
			verticality = builder
					.comment("How high the player is pushed from the ground when dodging. This value is proportional distance dodged due to friction.")
					.defineInRange("balance.verticality", 0.25, 0.0, Double.MAX_VALUE);
			exhaustion = builder
					.comment("How much exhaustion is added when dodging. For reference, sprinting adds 0.01 exhaustion per meter, and the Hunger effect adds 0.1 per second.")
					.defineInRange("balance.exhaustion", 0.4, 0.0, 40.0);
			hunger = builder
					.comment("How many half drumsticks the player needs to dodge. The default six is the same as sprinting.")
					.defineInRange("balance.hunger", 6, -1, 20);
			enableWhilstSneaking = builder
					.comment("Whether the player can dodge whilst sneaking.")
					.define("balance.enable_whilst_sneaking", false);
			enableWhilstBlocking = builder
					.comment("Whether the player can dodge whilst blocking.")
					.define("balance.enable_whilst_blocking", false);
			enableWhilstAirborne = builder
					.comment("Whether the player can dodge whilst airborne.")
					.define("balance.enable_whilst_airborne", false);
			invincibilityTicks = builder
					.comment("How many in-game ticks of invincibility the player has after dodging. 20 ticks is 1 second.")
					.defineInRange("balance.invincibility_ticks", 10, 0, 100);
			potions = builder
					.comment("Potions that prevent the player from dodging. Insert values in the format modid:potion,modid:potion. Idea Credit: SandwichHorror")
					.define("balance.potion_blacklist", "");
			
			// FEATHERS
			cost = builder
					.comment("How many half feathers dodging requires.")
					.defineInRange("feathers.cost", 2, 0, 20);
			rate = builder
					.comment("The amount of ticks required to regenerate half a feather.")
					.defineInRange("feathers.regen_rate", 100, 1, Integer.MAX_VALUE);
			half = builder
					.comment("Whether to enable 'Half Feathers'. Instead of weight values rounding down, they will instead show as a half feather. These can be used if the 'Cost' value is set to an odd number.")
					.define("feathers.half_feathers", false);
			
			// WEIGHTS
			weights = builder
					.comment("The weight of each item of Armor. This Overrides the automatically generated values. Each 'Steel Feather' is equivalent to a weight of 2. Insert values as such: modid:itemname=value,modid:itemname=value. Any item with a weight over 24 will not show its weight as a tooltip. Idea Credit: SandwichHorror")
					.define("weight.weights", "minecraft:leather_boots=1,minecraft:leather_leggings=2,"
							+ "minecraft:leather_chestplate=3,minecraft:leather_helmet=1,minecraft:iron_boots=2,"
							+ "minecraft:iron_leggings=3,minecraft:iron_chestplate=5,minecraft:iron_helmet=3,"
							+ "minecraft:diamond_boots=3,minecraft:diamond_leggings=4,minecraft:diamond_chestplate=6,"
							+ "minecraft:diamond_helmet=3,minecraft:golden_boots=2,minecraft:golden_leggings=2,"
							+ "minecraft:golden_chestplate=3,minecraft:golden_helmet=2,minecraft:chainmail_boots=2,"
							+ "minecraft:chainmail_leggings=2,minecraft:chainmail_chestplate=3,"
							+ "minecraft:chainmail_helmet=2,minecraft:elytra=2,"
							+ "minecraft:netherite_boots=4,minecraft:netherite_leggings=5,minecraft:netherite_chestplate=7,"
							+ "minecraft:netherite_helmet=4,minecraft:turtle_helmet=1");
			
			// MISC
			message = builder
					.comment("Whether the Player is notified when the Endurance Effect is overpowered by the Heavy Effect.")
					.define("misc.message", true);
			nether = builder
					.comment("Whether the Player dodges further in the Nether.")
					.define("misc.nether", true);
			end = builder
					.comment("Whether the Player dodge is weaker in the End.")
					.define("misc.end", true);
			
			// INTEGRATION
			tanCost = builder
					.comment("If Tough as Nails is installed: How much exhaustion (thirst) regenerating a feather costs. Idea Credit: ArtyArtyArtemis")
					.defineInRange("integration.tough_as_nails_cost", 2d, 0d, 40d);
		}
	}

	public static final Common COMMON;
	public static final ForgeConfigSpec COMMON_SPEC;
	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	// Bakers
	@SubscribeEvent
	public static void onModConfigEvent(final ModConfig.ModConfigEvent configEvent) {
		if (configEvent.getConfig().getSpec() == ConfigHandler.CLIENT_SPEC) {
			bakeClientConfig();
		} else if (configEvent.getConfig().getSpec() == ConfigHandler.COMMON_SPEC) {
			bakeCommonConfig();
		}
	}
	
	// Client
	public static boolean doubleTap, hud, tutorial, tooltips, compatHud, doubleTapForwards;
	public static int doubleTapTicks;

	public static void bakeClientConfig() {
		doubleTap = CLIENT.doubleTap.get();
		hud = CLIENT.hud.get();
		tutorial = CLIENT.tutorial.get();
		tooltips = CLIENT.tooltips.get();
		doubleTapTicks = CLIENT.doubleTapTicks.get();
		compatHud = CLIENT.compatHud.get();
		doubleTapForwards = CLIENT.doubleTapForwards.get();
	}
	
	// Common
	public static double force, verticality, exhaustion, tanCost;
	public static int hunger, invincibilityTicks, cost, rate;
	public static boolean enableWhilstSneaking, enableWhilstBlocking, enableWhilstAirborne, half, message, nether, end;
	public static String[] potions = new String[] {};
	public static String[] weights = new String[] {};

	public static void bakeCommonConfig() {
		force = COMMON.force.get();
		verticality = COMMON.verticality.get();
		exhaustion = COMMON.exhaustion.get();
		hunger = COMMON.hunger.get();
		invincibilityTicks = COMMON.invincibilityTicks.get();
		cost = COMMON.cost.get();
		rate = COMMON.rate.get();
		enableWhilstSneaking = COMMON.enableWhilstSneaking.get();
		enableWhilstBlocking = COMMON.enableWhilstBlocking.get();
		enableWhilstAirborne = COMMON.enableWhilstAirborne.get();
		half = COMMON.half.get();
		message = COMMON.message.get();
		nether = COMMON.nether.get();
		end = COMMON.end.get();
		potions = COMMON.potions.get().split(",");
		weights = COMMON.weights.get().split(",");
		tanCost = COMMON.tanCost.get();


	}
}