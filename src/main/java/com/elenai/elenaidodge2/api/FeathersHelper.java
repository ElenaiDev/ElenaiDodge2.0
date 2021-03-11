package com.elenai.elenaidodge2.api;

import com.elenai.elenaidodge2.capability.absorption.AbsorptionProvider;
import com.elenai.elenaidodge2.capability.absorption.IAbsorption;
import com.elenai.elenaidodge2.capability.dodges.DodgesProvider;
import com.elenai.elenaidodge2.capability.dodges.IDodges;
import com.elenai.elenaidodge2.capability.regen.IRegen;
import com.elenai.elenaidodge2.capability.regen.RegenProvider;
import com.elenai.elenaidodge2.capability.weight.IWeight;
import com.elenai.elenaidodge2.capability.weight.WeightProvider;
import com.elenai.elenaidodge2.network.PacketHandler;
import com.elenai.elenaidodge2.network.message.CUpdateDodgeMessage;
import com.elenai.elenaidodge2.network.message.CUpdateRegenMessage;
import com.elenai.elenaidodge2.util.ClientStorage;
import com.elenai.elenaidodge2.util.Utils;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;

public class FeathersHelper {

	/**
	 * Returns the amount of feathers the player has on the server out of 20.
	 * 
	 * @author Elenai
	 * @param player
	 * @return The player's feathers
	 */
	public static int getFeatherLevel(EntityPlayerMP player) {
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
	public static int getFeatherLevel(EntityPlayerSP player) {
		return ClientStorage.dodges;
	}

	/**
	 * Increases the player's feathers by the specified amount. (Unless the feather
	 * bar would be full, in which case set to 20)
	 * 
	 * @param player
	 * @param amount
	 */
	public static void increaseFeathers(EntityPlayerMP player, int amount) {
		IDodges d = player.getCapability(DodgesProvider.DODGES_CAP, null);

			if (d.getDodges() + amount <= 20) {
				d.increase(amount);
			} else {
				d.set(20);
			}
			PacketHandler.instance.sendTo(new CUpdateDodgeMessage(d.getDodges()), player);
			Utils.showDodgeBar();
	}

	/**
	 * Decreases the player's feathers by the specified amount including absorption feathers. (If this would be
	 * below 0, then they are simply set to 0)
	 * 
	 * @param player
	 * @param amount
	 */
	public static void decreaseFeathers(EntityPlayerMP player, int amount) {		
		
		SpendFeatherEvent event = new SpendFeatherEvent(amount, player);
		if(!MinecraftForge.EVENT_BUS.post(event)) {
		IAbsorption a = player.getCapability(AbsorptionProvider.ABSORPTION_CAP, null);
		IDodges d = player.getCapability(DodgesProvider.DODGES_CAP, null);
		if (!player.isCreative() && !player.isSpectator()) {
			if (a.getAbsorption() <= 0) {
				d.set(d.getDodges() - event.getCost());
			} else if (a.getAbsorption() - event.getCost() >= 0) {
				a.set(a.getAbsorption() - event.getCost());
			} else {
				d.increase(a.getAbsorption() - event.getCost());
				a.set(0);
			}
		}
		if(d.getDodges() < 0) {
			d.set(0);
		}
		PacketHandler.instance.sendTo(new CUpdateDodgeMessage(d.getDodges()), player);
		Utils.showDodgeBar();
		}
	}
	
	/**
	 * Returns the player's weight on the server.
	 * 
	 * @author Elenai
	 * @param player
	 * @return The player's weight
	 */
	public static int getWeight(EntityPlayerMP player) {
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
	public static int getWeight(EntityPlayerSP player) {
		return ClientStorage.weight;
	}
	
	/**
	 * Sets the player's regen modifier on the server. This is a value that is added onto the Config specified regen.
	 * A positive value will increase regen time, a negative value will decrease it.
	 * 
	 * WARNING! It is far better to use the increase and decrease methods for inter-mod compatibility.
	 * To temporarily disable regen, simply set this to an almost unreachable value, such as 9999999999.
	 * 
	 * If this makes combined regen ever go below 0, it will default to 1.
	 * 
	 * @author Elenai
	 * @param player
	 * @param modifier
	 */
	public static void setRegenModifier(EntityPlayerMP player, int modifier) {
		IRegen r = player.getCapability(RegenProvider.REGEN_CAP, null);
			r.set(modifier);
			PacketHandler.instance.sendTo(new CUpdateRegenMessage(r.getRegen()), player);
	}
	
	/**
	 * Increases the player's regen modifier on the server. This is a value that is added onto the Config specified regen.
	 * A positive value will increase regen time, a negative value will decrease it.
	 * 
	 * If this makes combined regen ever go below 0, it will default to 1.
	 * 
	 * @author Elenai
	 * @param player
	 * @param modifier
	 */
	public static void increaseRegenModifier(EntityPlayerMP player, int modifier) {
		IRegen r = player.getCapability(RegenProvider.REGEN_CAP, null);
		r.increase(modifier);
		PacketHandler.instance.sendTo(new CUpdateRegenMessage(r.getRegen()), player);
	}
	
	/**
	 * Decreases the player's regen modifier on the server. This is a value that is added onto the Config specified regen.
	 * A positive value will increase regen time, a negative value will decrease it.
	 * 
	 * If this makes combined regen ever go below 0, it will default to 1.
	 * 
	 * @author Elenai
	 * @param player
	 * @param modifier
	 */
	public static void decreaseRegenModifier(EntityPlayerMP player, int modifier) {
		IRegen r = player.getCapability(RegenProvider.REGEN_CAP, null);
		r.decrease(modifier);
		PacketHandler.instance.sendTo(new CUpdateRegenMessage(r.getRegen()), player);
	}
	
	/**
	 * Returns the player's regen modifier on the server.
	 * 
	 * @author Elenai
	 * @param player
	 * @return The player's regen modifier
	 */
	public static int getRegenModifier(EntityPlayerSP player) {
		IRegen r = player.getCapability(RegenProvider.REGEN_CAP, null);
		return r.getRegen();
	}

}
