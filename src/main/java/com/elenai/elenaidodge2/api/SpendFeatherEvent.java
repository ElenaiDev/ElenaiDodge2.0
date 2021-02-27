package com.elenai.elenaidodge2.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class SpendFeatherEvent extends Event {

	protected final EntityPlayer player;
	protected int cost;

	/**
	 * SpendFeatherEvent is called whenever a feather is spent
	 * @param cost the amount of cost spent
	 * @param player
	 * @author Elenai
	 */
	public SpendFeatherEvent(int cost, EntityPlayer player) {
		this.cost = cost;
		this.player = player;
	}
		
		/**
		 * @return Feather Cost
		 */
		public int getCost() {
			return cost;
		}

		/**
		 * Sets the Feather Cost. it is ALWAYS recommended to do setCost(event.getCost() + X) in order to ensure compatibility.
		 * @param force
		 */
		public void setCost(int cost) {
			this.cost = cost;
		}

		/**
		 * @return Player Spending cost
		 */
		public EntityPlayer getPlayer() {
			return player;
		}
	
}
