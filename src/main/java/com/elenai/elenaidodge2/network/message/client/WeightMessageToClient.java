package com.elenai.elenaidodge2.network.message.client;

import com.elenai.elenaidodge2.ElenaiDodge;

import net.minecraft.network.PacketBuffer;

public class WeightMessageToClient {
	  private int weight;
	  private boolean messageIsValid;
	
  public WeightMessageToClient(int weight) {
	  this.weight = weight;
    messageIsValid = true;
  }

  public int getWeight() {
    return weight;
  }

  public boolean isMessageValid() {
    return messageIsValid;
  }

  // for use by the message handler only.
  public WeightMessageToClient()
  {
    messageIsValid = false;
  }

  public static WeightMessageToClient decode(PacketBuffer buf) {
	  WeightMessageToClient message = new WeightMessageToClient();
    try {
    	message.weight = buf.readInt();

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
   * Called by the network code.
   * Used to write the contents of your message member variables into the ByteBuf, ready for transmission over the network.
   * @param buf
   */
  public void encode(PacketBuffer buf) {
    if (!messageIsValid) return;
    buf.writeInt(weight);

    // for Itemstacks - ByteBufUtils.writeItemStack()
    // for NBT tags ByteBufUtils.writeTag();
    // for Strings: ByteBufUtils.writeUTF8String();
  }

  @Override
  public String toString() {
    return "WeightMessageToClient[weight=" + String.valueOf(weight) + "]";
  }

}
