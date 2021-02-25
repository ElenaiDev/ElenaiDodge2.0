package com.elenai.elenaidodge2.network.message.client;

import com.elenai.elenaidodge2.ElenaiDodge2;

import net.minecraft.network.PacketBuffer;

public class CancelledFeathersMessageToClient {


	private boolean messageIsValid;

	public CancelledFeathersMessageToClient() {
		messageIsValid = true;
	}

	public boolean isMessageValid() {
		return messageIsValid;
	}


	public static CancelledFeathersMessageToClient decode(PacketBuffer buf) {
		CancelledFeathersMessageToClient message = new CancelledFeathersMessageToClient();
		try {
		} catch (IllegalArgumentException | IndexOutOfBoundsException e) {
			ElenaiDodge2.LOGGER.warn("Exception while reading WeightsMessageToClient: " + e);
			return message;
		}
		message.messageIsValid = true;
		return message;
	}

	/**
	 * Called by the network code. Used to write the contents of your message member
	 * variables into the ByteBuf, ready for transmission over the network.
	 * 
	 * @param buf
	 */
	public void encode(PacketBuffer buf) {
		if (!messageIsValid)
			return;
	}

	@Override
	public String toString() {
		return "DodgeEffectsMessageToClient";
	}

}
