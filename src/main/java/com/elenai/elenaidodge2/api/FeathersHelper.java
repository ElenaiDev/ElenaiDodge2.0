package com.elenai.elenaidodge2.api;

import com.elenai.elenaidodge2.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge2.capability.dodges.DodgesProvider;
import com.elenai.elenaidodge2.capability.weight.WeightProvider;
import com.elenai.elenaidodge2.network.NetworkHandler;
import com.elenai.elenaidodge2.network.message.client.DodgeMessageToClient;
import com.elenai.elenaidodge2.util.ClientStorage;
import com.elenai.elenaidodge2.util.Utils;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.PacketDistributor;

public class FeathersHelper {

	/**
	 * Returns the amount of feathers the player has on the server out of 20.
	 * 
	 * @author Elenai
	 * @param player
	 * @return The player's feathers
	 */
	public int getFeatherLevel(ServerPlayerEntity player) {
		return player.getCapability(DodgesProvider.DODGES_CAP).map(d -> {
			return d.getDodges();
		}).orElse(0);
	}

	/**
	 * Returns the amount of feathers the player has on the client out of 20.
	 * 
	 * @author Elenai
	 * @param player
	 * @return The player's feathers
	 */
	public int getFeatherLevel(ClientPlayerEntity player) {
		return ClientStorage.dodges;
	}

	/**
	 * Increases the player's feathers by the specified amount. (Unless the feather
	 * bar would be full, in which case set to 20)
	 * 
	 * @param player
	 * @param amount
	 */
	public void increaseFeathers(ServerPlayerEntity player, int amount) {
		player.getCapability(DodgesProvider.DODGES_CAP).ifPresent(d -> {
			if (d.getDodges() + amount <= 20) {
				d.increase(amount);
			} else {
				d.set(20);
			}
			NetworkHandler.simpleChannel.send(PacketDistributor.PLAYER.with(() -> player),
					new DodgeMessageToClient(d.getDodges()));
		});
		Utils.showDodgeBar();
	}

	/**
	 * Decreases the player's feathers by the specified amount including absorption feathers. (If this would be
	 * below 0, then they are simply set to 0)
	 * 
	 * @param player
	 * @param amount
	 */
	public void decreaseFeathers(ServerPlayerEntity player, int amount) {
		player.getCapability(AbsorptionProvider.ABSORPTION_CAP).ifPresent(a -> {
			player.getCapability(DodgesProvider.DODGES_CAP).ifPresent(d -> {
				if (!player.isCreative() && !player.isSpectator()) {
					if (a.getAbsorption() <= 0) {
						d.set(d.getDodges() - amount);
					} else if (a.getAbsorption() - amount >= 0) {
						a.set(a.getAbsorption() - amount);
					} else {
						d.increase(a.getAbsorption() - amount);
						a.set(0);
					}
				}
				if(d.getDodges() < 0) {
					d.set(0);
				}
				NetworkHandler.simpleChannel.send(PacketDistributor.PLAYER.with(() -> player),
						new DodgeMessageToClient(d.getDodges()));
			});
		});
		Utils.showDodgeBar();
	}
	
	/**
	 * Returns the player's weight on the server.
	 * 
	 * @author Elenai
	 * @param player
	 * @return The player's weight
	 */
	public int getWeight(ServerPlayerEntity player) {
		return player.getCapability(WeightProvider.WEIGHT_CAP).map(w -> {
			return w.getWeight();
		}).orElse(0);
	}

	/**
	 * Returns the player's weight on the client.
	 * 
	 * @author Elenai
	 * @param player
	 * @return The player's weight
	 */
	public int getWeight(ClientPlayerEntity player) {
		return ClientStorage.weight;
	}

}
