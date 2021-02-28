package com.elenai.elenaidodge2.api;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class CheckFeatherEvent extends Event {

	protected final PlayerEntity player;

	/**
	 * CheckFeatherEvent is called whenever a movement feature checks if the player has enough feathers or weight
	 * to execute it. Cancelling this means the player can dodge anyway.
	 * @param player
	 * @author Elenai
	 */
	public CheckFeatherEvent(ServerPlayerEntity player) {
		this.player = player;
	}

		/**
		 * @return Player Spending cost
		 */
		public PlayerEntity getPlayer() {
			return player;
		}
	
}
