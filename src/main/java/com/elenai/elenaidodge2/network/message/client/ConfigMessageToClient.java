package com.elenai.elenaidodge2.network.message.client;

import com.elenai.elenaidodge2.ElenaiDodge2;

import net.minecraft.network.PacketBuffer;

public class ConfigMessageToClient {
	private int regenRate, dodges, absorption;
	private String weights;
	private boolean half;
	private boolean messageIsValid;

	public ConfigMessageToClient(int regenRate, int dodges, String weights, boolean half, int absorption) {
		this.regenRate = regenRate;
		this.dodges = dodges;
		this.weights = weights;
		this.half = half;
		this.absorption = absorption;
		messageIsValid = true;
	}
	public int getRegenRate() {
		return regenRate;
	}
	public int getDodges() {
		return dodges;
	}
	public String getWeightValues() {
		return weights;
	}
	public boolean getHalfFeathers() {
		return half;
	}
	public int getAbsorption() {
		return absorption;
	}

	public boolean isMessageValid() {
		return messageIsValid;
	}

	// for use by the message handler only.
	public ConfigMessageToClient() {
		messageIsValid = false;
	}

	public static ConfigMessageToClient decode(PacketBuffer buf) {
		ConfigMessageToClient message = new ConfigMessageToClient();
		try {
			message.regenRate = buf.readInt();
			message.dodges = buf.readInt();
			message.weights = buf.readString(99999);
			message.half = buf.readBoolean();
			message.absorption = buf.readInt();

			// for Itemstacks - ByteBufUtils.readItemStack()
			// for NBT tags ByteBufUtils.readTag();
			// for Strings: ByteBufUtils.readUTF8String();
			// NB that PacketBuffer is a derived class of ByteBuf

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
		buf.writeInt(regenRate);
		buf.writeInt(dodges);
		buf.writeString(weights, 99999);
		buf.writeBoolean(half);
		buf.writeInt(absorption);

		// for Itemstacks - ByteBufUtils.writeItemStack()
		// for NBT tags ByteBufUtils.writeTag();
		// for Strings: ByteBufUtils.writeUTF8String();
	}

	@Override
	public String toString() {
		return "ConfigMessageToClient[regenRate=" + String.valueOf(regenRate) + "] [dodges=" + String.valueOf(dodges)
		+ "] [weights=" + weights + "] [half=" + String.valueOf(half) + "] [absorption=" + String.valueOf(absorption) + "]";
	}

}
