package com.elenai.elenaidodge2.network.message.client;

import com.elenai.elenaidodge2.ElenaiDodge2;

import net.minecraft.network.PacketBuffer;

public class RegenModifierMessageToClient {
	private int modifier;
	private boolean messageIsValid;

	public RegenModifierMessageToClient(int modifier) {
		this.modifier = modifier;
		messageIsValid = true;
	}

	public int getModifier() {
		return modifier;
	}

	public boolean isMessageValid() {
		return messageIsValid;
	}

	// for use by the message handler only.
	public RegenModifierMessageToClient() {
		messageIsValid = false;
	}

	public static RegenModifierMessageToClient decode(PacketBuffer buf) {
		RegenModifierMessageToClient message = new RegenModifierMessageToClient();
		try {
			message.modifier = buf.readInt();

			// for Itemstacks - ByteBufUtils.readItemStack()
			// for NBT tags ByteBufUtils.readTag();
			// for Strings: ByteBufUtils.readUTF8String();
			// NB that PacketBuffer is a derived class of ByteBuf

		} catch (IllegalArgumentException | IndexOutOfBoundsException e) {
			ElenaiDodge2.LOGGER.warn("Exception while reading RegenModifierMessageToClient: " + e);
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
		buf.writeInt(modifier);


		// for Itemstacks - ByteBufUtils.writeItemStack()
		// for NBT tags ByteBufUtils.writeTag();
		// for Strings: ByteBufUtils.writeUTF8String();
	}

	@Override
	public String toString() {
		return "RegenModifierMessageToClient" + "[modifier=" + String.valueOf(modifier) + "]";
	}

}
