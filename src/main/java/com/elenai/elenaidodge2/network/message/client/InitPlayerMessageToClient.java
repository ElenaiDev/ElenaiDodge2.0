package com.elenai.elenaidodge2.network.message.client;

import com.elenai.elenaidodge2.ElenaiDodge;

import net.minecraft.network.PacketBuffer;

public class InitPlayerMessageToClient {
	private int dodges;
	private boolean messageIsValid;

	public InitPlayerMessageToClient(int dodges) {
		this.dodges = dodges;
		messageIsValid = true;
	}

	public int getDodges() {
		return dodges;
	}

	public boolean isMessageValid() {
		return messageIsValid;
	}

	// for use by the message handler only.
	public InitPlayerMessageToClient() {
		messageIsValid = false;
	}

	public static InitPlayerMessageToClient decode(PacketBuffer buf) {
		InitPlayerMessageToClient message = new InitPlayerMessageToClient();
		try {
			message.dodges = buf.readInt();

			// for Itemstacks - ByteBufUtils.readItemStack()
			// for NBT tags ByteBufUtils.readTag();
			// for Strings: ByteBufUtils.readUTF8String();
			// NB that PacketBuffer is a derived class of ByteBuf

		} catch (IllegalArgumentException | IndexOutOfBoundsException e) {
			ElenaiDodge.LOGGER.warn("Exception while reading WeightsMessageToClient: " + e);
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
		buf.writeInt(dodges);

		// for Itemstacks - ByteBufUtils.writeItemStack()
		// for NBT tags ByteBufUtils.writeTag();
		// for Strings: ByteBufUtils.writeUTF8String();
	}

	@Override
	public String toString() {
		return "DodgeEffectsMessageToClient[dodges=" + String.valueOf(dodges) + "]";
	}

}
