package com.elenai.elenaidodge.network.message.client;

import com.elenai.elenaidodge.ElenaiDodge;

import net.minecraft.network.PacketBuffer;

public class ParticleMessageToClient {
	private int level;
	private double x, y, z;
	private boolean messageIsValid;

	public ParticleMessageToClient(int level, double x, double y, double z) {
		this.level = level;
		this.x = x;
		this.y = y;
		this.z = z;
		messageIsValid = true;
	}

	public int getLevel() {
		return level;
	}

	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getZ() {
		return z;
	}

	public boolean isMessageValid() {
		return messageIsValid;
	}

	// for use by the message handler only.
	public ParticleMessageToClient() {
		messageIsValid = false;
	}

	public static ParticleMessageToClient decode(PacketBuffer buf) {
		ParticleMessageToClient message = new ParticleMessageToClient();
		try {
			message.level = buf.readInt();
			message.x = buf.readDouble();
			message.y = buf.readDouble();
			message.z = buf.readDouble();

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
		buf.writeInt(level);
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);


		// for Itemstacks - ByteBufUtils.writeItemStack()
		// for NBT tags ByteBufUtils.writeTag();
		// for Strings: ByteBufUtils.writeUTF8String();
	}

	@Override
	public String toString() {
		return "ParticleMessageToClient[level=" + String.valueOf(level) + "] [xyz=" + String.valueOf(x) + "," + String.valueOf(y) + "," + String.valueOf(z) + "]";
	}

}
