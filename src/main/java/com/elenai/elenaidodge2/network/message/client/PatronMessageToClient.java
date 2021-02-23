package com.elenai.elenaidodge2.network.message.client;

import com.elenai.elenaidodge2.ElenaiDodge2;

import net.minecraft.network.PacketBuffer;

public class PatronMessageToClient {
	private int level;
	private boolean messageIsValid;

	public PatronMessageToClient(int level) {
		this.level = level;
		messageIsValid = true;
	}

	public int getLevel() {
		return level;
	}

	public boolean isMessageValid() {
		return messageIsValid;
	}

	// for use by the message handler only.
	public PatronMessageToClient() {
		messageIsValid = false;
	}

	public static PatronMessageToClient decode(PacketBuffer buf) {
		PatronMessageToClient message = new PatronMessageToClient();
		try {
			message.level = buf.readInt();

			// for Itemstacks - ByteBufUtils.readItemStack()
			// for NBT tags ByteBufUtils.readTag();
			// for Strings: ByteBufUtils.readUTF8String();
			// NB that PacketBuffer is a derived class of ByteBuf

		} catch (IllegalArgumentException | IndexOutOfBoundsException e) {
			ElenaiDodge2.LOGGER.warn("Exception while reading PatronMessageToClient: " + e);
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
		buf.writeInt(level);

		// for Itemstacks - ByteBufUtils.writeItemStack()
		// for NBT tags ByteBufUtils.writeTag();
		// for Strings: ByteBufUtils.writeUTF8String();
	}

	@Override
	public String toString() {
		return "PatronMessageToClient[level=" + String.valueOf(level) + "]";
	}

}
