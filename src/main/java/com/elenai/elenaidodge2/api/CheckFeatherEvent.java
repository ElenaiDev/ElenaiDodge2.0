package com.elenai.elenaidodge2.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class CheckFeatherEvent extends Event {

	protected final EntityPlayer player;

	/**
	 * CheckFeatherEvent is called whenever a movement feature checks if the player has enough feathers or weight
	 * to execute it. Cancelling this means the player can dodge anyway.
	 * @param player
	 * @author Elenai
	 */
	public CheckFeatherEvent(EntityPlayer player) {
		this.player = player;
	}

		/**
		 * @return Player Spending cost
		 */
		public EntityPlayer getPlayer() {
			return player;
		}
	
}
