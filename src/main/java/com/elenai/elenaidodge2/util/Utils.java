package com.elenai.elenaidodge2.util;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.api.DodgeEvent;
import com.elenai.elenaidodge2.api.DodgeEvent.Direction;
import com.elenai.elenaidodge2.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge2.capability.dodges.DodgesProvider;
import com.elenai.elenaidodge2.config.ConfigHandler;
import com.elenai.elenaidodge2.effects.ServerDodgeEffects;
import com.elenai.elenaidodge2.event.ClientTickEventListener;
import com.elenai.elenaidodge2.gui.DodgeGui;
import com.elenai.elenaidodge2.network.NetworkHandler;
import com.elenai.elenaidodge2.network.message.client.CancelledFeathersMessageToClient;
import com.elenai.elenaidodge2.network.message.client.ConfigMessageToClient;
import com.elenai.elenaidodge2.network.message.client.DodgeEffectsMessageToClient;
import com.elenai.elenaidodge2.network.message.client.InitPlayerMessageToClient;
import com.elenai.elenaidodge2.network.message.client.VelocityMessageToClient;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.network.PacketDistributor;

public class Utils {

	/**
	 * Allows player.setVelocity to be run from the server.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param player
	 * @author Elenai
	 * @side Server
	 */
	public static void setPlayerVelocity(double x, double y, double z, PlayerEntity player) {
		NetworkHandler.simpleChannel.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player),
				new VelocityMessageToClient(x, y, z));
	}
	
	/**
	 * Tells the Client to flash the GUI white and show the GUI.
	 * 
	 * @param player
	 * @author Elenai
	 * @side Server
	 */
	public static void cancelledByFeathers(PlayerEntity player) {
		NetworkHandler.simpleChannel.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player),
				new CancelledFeathersMessageToClient());
	}

	/**
	 * Dodges the player in the given direction.
	 * 
	 * @param direction
	 * @param player
	 * @author Elenai
	 * @side Server
	 */
	public static void handleDodge(Direction direction, DodgeEvent.ServerDodgeEvent event, ServerPlayerEntity player) {

		double f = event.getForce();
		double motionX;
		double motionZ;

		switch (direction) {
		case LEFT:
			motionX = (double) (MathHelper.cos(player.rotationYaw / 180.0F * (float) Math.PI)
					* MathHelper.cos(1 / 180.0F * (float) Math.PI) * f);
			motionZ = (double) -(-MathHelper.sin(player.rotationYaw / 180.0F * (float) Math.PI)
					* MathHelper.cos(1 / 180.0F * (float) Math.PI) * f);
			break;
		case RIGHT:
			motionX = (double) -(MathHelper.cos(player.rotationYaw / 180.0F * (float) Math.PI)
					* MathHelper.cos(1 / 180.0F * (float) Math.PI) * f);
			motionZ = (double) (-MathHelper.sin(player.rotationYaw / 180.0F * (float) Math.PI)
					* MathHelper.cos(1 / 180.0F * (float) Math.PI) * f);
			break;
		case FORWARD:
			motionX = (double) (-MathHelper.sin(player.rotationYaw / 180.0F * (float) Math.PI)
					* MathHelper.cos(1 / 180.0F * (float) Math.PI) * f);
			motionZ = (double) (MathHelper.cos(player.rotationYaw / 180.0F * (float) Math.PI)
					* MathHelper.cos(1 / 180.0F * (float) Math.PI) * f);
			break;
		case BACK:
			motionX = (double) -(-MathHelper.sin(player.rotationYaw / 180.0F * (float) Math.PI)
					* MathHelper.cos(1 / 180.0F * (float) Math.PI) * f);
			motionZ = (double) -(MathHelper.cos(player.rotationYaw / 180.0F * (float) Math.PI)
					* MathHelper.cos(1 / 180.0F * (float) Math.PI) * f);
			break;
		default:
			motionX = 0;
			motionZ = 0;
			ElenaiDodge2.LOG.error("DodgeEvent Posted and Received but no direction given!");
		}
		setPlayerVelocity(motionX, ConfigHandler.verticality, motionZ, player);
		ServerDodgeEffects.run(player);
		player.getCapability(DodgesProvider.DODGES_CAP).ifPresent(d -> {
			player.getCapability(AbsorptionProvider.ABSORPTION_CAP).ifPresent(a -> {
				NetworkHandler.simpleChannel.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new DodgeEffectsMessageToClient(d.getDodges(), a.getAbsorption()));
			});
		});
	}

	/**
	 * Returns the Player's Dodge Force when all default calculations have been
	 * applied.
	 * 
	 * @param player
	 * @return The Player's total Dodge Force
	 * @author Elenai
	 * @side Server
	 */
	public static double calculateForce(PlayerEntity player) {
		return ConfigHandler.force;
	}

	/**
	 * A method to be run when the player first joins the world.
	 * 
	 * @author Elenai
	 * @param player
	 */
	public static void initPlayer(PlayerEntity player) {
		NetworkHandler.simpleChannel.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player),
				new InitPlayerMessageToClient(20));
		player.getCapability(DodgesProvider.DODGES_CAP).ifPresent(d -> {
			d.set(20);
		});
	}

	/**
	 * Updates the Client Config for the given player.
	 * 
	 * @author Elenai
	 * @param player
	 */
	public static void updateClientConfig(ServerPlayerEntity player) {
		player.getCapability(DodgesProvider.DODGES_CAP).ifPresent(d -> {
			player.getCapability(AbsorptionProvider.ABSORPTION_CAP).ifPresent(a -> {

				NetworkHandler.simpleChannel.send(PacketDistributor.PLAYER.with(() -> player), 
						new ConfigMessageToClient(ConfigHandler.rate, d.getDodges(),
								arrayToString(ConfigHandler.weights), ConfigHandler.half, a.getAbsorption()));
			});

		});

	}

	/**
	 * Returns the cumulative total of an equipped enchantment type.
	 * 
	 * @author Diesieben07
	 * @param enchantment
	 * @param entity
	 * @return
	 */
	public static int getTotalEnchantmentLevel(Enchantment enchantment, LivingEntity entity) {
		Iterable<ItemStack> iterable = enchantment.getEntityEquipment(entity).values();

		if (iterable == null) {
			return 0;
		} else {
			int i = 0;

			for (ItemStack itemstack : iterable) {
				int j = EnchantmentHelper.getEnchantmentLevel(enchantment, itemstack);
				i += j;
			}

			return i;
		}
	}

	/**
	 * Converts a String Array into a CSV String
	 * 
	 * @author Nico Huysamen
	 * @author Adapted by Elenai
	 * @param string
	 * @return
	 */
	public static String arrayToString(String[] string) {
		if (string.length > 0) {
			StringBuilder stringBuilder = new StringBuilder();

			for (String n : string) {
				stringBuilder.append("").append(n.replace("'", "\\'")).append(",");
			}

			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			return stringBuilder.toString();
		} else {
			return "";
		}
	}
	
	/**
	 * Shows the player's dodge bar if it is hidden
	 */
	public static void showDodgeBar() {
		if (DodgeGui.alpha < 1) {
			DodgeGui.alpha = 1f;
			ClientTickEventListener.alpha = ClientTickEventListener.alphaLen;
		}
	}
	
	/**
	 * Checks if the player has the TAN implementation enabled and the mod is present on the Client.
	 * If Reskillable is not installed, this will simply return true.
	 * 
	 * @return Dodge Trait Unlocked
	 * @author Elenai
	 */
	public static boolean tanEnabled(ClientPlayerEntity player) {
		if (ModList.get().isLoaded("toughasnails") && ConfigHandler.enableTan) {
			return true;
		}
		return false;
	}

}
