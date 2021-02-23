package com.elenai.elenaidodge2.network.message.client;

import com.elenai.elenaidodge2.ElenaiDodge;

import net.minecraft.network.PacketBuffer;

public class DodgeEffectsMessageToClient {
	private int dodges, absorption;
	private boolean messageIsValid;

	public DodgeEffectsMessageToClient(int dodges, int absorption) {
		this.dodges = dodges;
		this.absorption = absorption;
		messageIsValid = true;
	}

	public int getDodges() {
		return dodges;
	}

	public int getAbsorption() {
		return absorption;
	}

	public boolean isMessageValid() {
		return messageIsValid;
	}

	// for use by the message handler only.
	public DodgeEffectsMessageToClient() {
		messageIsValid = false;
	}

	public static DodgeEffectsMessageToClient decode(PacketBuffer buf) {
		DodgeEffectsMessageToClient message = new DodgeEffectsMessageToClient();
		try {
			message.dodges = buf.readInt();
			message.absorption = buf.readInt();

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
		buf.writeInt(absorption);

		// for Itemstacks - ByteBufUtils.writeItemStack()
		// for NBT tags ByteBufUtils.writeTag();
		// for Strings: ByteBufUtils.writeUTF8String();
	}

	@Override
	public String toString() {
		return "DodgeEffectsMessageToClient[dodges=" + String.valueOf(dodges) + "] [absorption=" + String.valueOf(absorption) + "]";
	}

}
