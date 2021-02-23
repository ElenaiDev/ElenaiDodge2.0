package com.elenai.elenaidodge2.api;

import com.elenai.elenaidodge2.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge2.capability.absorption.IAbsorption;
import com.elenai.elenaidodge2.capability.dodges.DodgesProvider;
import com.elenai.elenaidodge2.capability.dodges.IDodges;
import com.elenai.elenaidodge2.capability.weight.IWeight;
import com.elenai.elenaidodge2.capability.weight.WeightProvider;
import com.elenai.elenaidodge2.network.PacketHandler;
import com.elenai.elenaidodge2.network.message.CUpdateDodgeMessage;
import com.elenai.elenaidodge2.util.ClientStorage;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;

public class FeathersHelper {

	/**
	 * Returns the amount of feathers the player has on the server out of 20.
	 * 
	 * @author Elenai
	 * @param player
	 * @return The player's feathers
	 */
	public int getFeatherLevel(EntityPlayerMP player) {
		IDodges d = player.getCapability(DodgesProvider.DODGES_CAP, null);
		return(d.getDodges());
	}

	/**
	 * Returns the amount of feathers the player has on the client out of 20.
	 * 
	 * @author Elenai
	 * @param player
	 * @return The player's feathers
	 */
	public int getFeatherLevel(EntityPlayerSP player) {
		return ClientStorage.dodges;
	}

	/**
	 * Increases the player's feathers by the specified amount. (Unless the feather
	 * bar would be full, in which case set to 20)
	 * 
	 * @param player
	 * @param amount
	 */
	public void increaseFeathers(EntityPlayerMP player, int amount) {
		IDodges d = player.getCapability(DodgesProvider.DODGES_CAP, null);

			if (d.getDodges() + amount <= 20) {
				d.increase(amount);
			} else {
				d.set(20);
			}
			PacketHandler.instance.sendTo(new CUpdateDodgeMessage(d.getDodges()), player);
	}

	/**
	 * Decreases the player's feathers by the specified amount including absorption feathers. (If this would be
	 * below 0, then they are simply set to 0)
	 * 
	 * @param player
	 * @param amount
	 */
	public void decreaseFeathers(EntityPlayerMP player, int amount) {		
		
		IAbsorption a = player.getCapability(AbsorptionProvider.ABSORPTION_CAP, null);
		IDodges d = player.getCapability(DodgesProvider.DODGES_CAP, null);
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
		PacketHandler.instance.sendTo(new CUpdateDodgeMessage(d.getDodges()), player);
	}
	
	/**
	 * Returns the player's weight on the server.
	 * 
	 * @author Elenai
	 * @param player
	 * @return The player's weight
	 */
	public int getWeight(EntityPlayerMP player) {
		IWeight w = player.getCapability(WeightProvider.WEIGHT_CAP, null);
		return w.getWeight();
	}

	/**
	 * Returns the player's weight on the client.
	 * 
	 * @author Elenai
	 * @param player
	 * @return The player's weight
	 */
	public int getWeight(EntityPlayerSP player) {
		return ClientStorage.weight;
	}

}
