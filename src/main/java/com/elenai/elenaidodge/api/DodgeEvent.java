package com.elenai.elenaidodge.api;

import org.spongepowered.asm.mixin.MixinEnvironment.Side;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

public class DodgeEvent extends Event {
	public enum Direction {
		FORWARD, BACK, LEFT, RIGHT;
	}

	protected final Side side;
	protected Direction direction;
	protected final PlayerEntity player;
	protected double force;

	/**
	 * Do not use DodgeEvent by itself. Please use RequestDodgeEvent for the Client, and
	 * ServerDodgeEvent for the Server. RequestDodgeEvent is called when the player
	 * wants to Dodge. You can push this to the Forge event bus to make the player
	 * dodge yourself. ServerDodgeEvent is called as the player dodges. You can
	 * cancel the event to prevent the player from dodging.
	 * 
	 * @param side
	 * @param direction
	 * @param force
	 * @param player
	 * @author Elenai
	 */
	public DodgeEvent(Side side, Direction direction, double force, PlayerEntity player) {
		this.side = side;
		this.direction = direction;
		this.force = force;
		this.player = player;
	}

	/**
	 * RequestDodgeEvent is called when the player wants to Dodge. You can push this
	 * to the Forge event bus to make the player dodge yourself.
	 * 
	 * @author Elenai
	 */
	public static class RequestDodgeEvent extends DodgeEvent {
		@SuppressWarnings("resource")
		public RequestDodgeEvent(Direction direction) {
			super(Side.SERVER, direction, 0.0, Minecraft.getInstance().player);
		}
	}

	/**
	 * ServerDodgeEvent is called as the player dodges. You can cancel the event to
	 * prevent the player from dodging.
	 * 
	 * @author Elenai
	 */
	@Cancelable
	public static class ServerDodgeEvent extends DodgeEvent {
		public ServerDodgeEvent(Direction direction, double force, PlayerEntity player) {
			super(Side.CLIENT, direction, force, player);
		}
		
		/**
		 * @return Dodge Force
		 */
		public double getForce() {
			return force;
		}

		/**
		 * Sets the dodge force. it is ALWAYS recommended to do setForce(event.getForce() + X) in order to ensure compatibility.
		 * @param force
		 */
		public void setForce(double force) {
			this.force = force;
		}

		/**
		 * @return Player Dodging
		 */
		public PlayerEntity getPlayer() {
			return player;
		}
	}

	/**
	 * @return Client or Server
	 */
	public Side getSide() {
		return side;
	}

	
	/**
	 * @return Dodge Direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Change the direction the player will dodge.
	 * @param direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
