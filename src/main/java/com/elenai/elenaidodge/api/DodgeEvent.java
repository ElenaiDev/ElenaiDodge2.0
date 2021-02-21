package com.elenai.elenaidodge.api;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;

public class DodgeEvent extends Event {
	public enum Direction {
		FORWARD, BACK, LEFT, RIGHT;
	}

	protected final Side side;
	protected Direction direction;
	protected final EntityPlayer player;
	protected double force;

	/**
	 * Do not use this by itself. Please use RequestDodgeEvent for the Client, and
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
	public DodgeEvent(Side side, Direction direction, double force, EntityPlayer player) {
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
		public RequestDodgeEvent(Direction direction) {
			super(Side.SERVER, direction, 0.0, Minecraft.getMinecraft().player);
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
		public ServerDodgeEvent(Direction direction, double force, EntityPlayer player) {
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
		public EntityPlayer getPlayer() {
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
