package com.elenai.elenaidodge2.api;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class SpendFeatherEvent extends Event {

	protected final PlayerEntity player;
	protected int cost;

	/**
	 * SpendFeatherEvent is called whenever a feather is spent
	 * @param cost the amount of cost spent
	 * @param player
	 * @author Elenai
	 */
	public SpendFeatherEvent(int cost, PlayerEntity player) {
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
		public PlayerEntity getPlayer() {
			return player;
		}
	
}
