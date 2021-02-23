package com.elenai.elenaidodge2.network.message.server;

import com.elenai.elenaidodge2.ElenaiDodge;

import net.minecraft.network.PacketBuffer;

public class WeightMessageToServer  {

	  private int weight;
	  private boolean messageIsValid;
	
  public WeightMessageToServer(int weight) {
    this.weight = weight;
    messageIsValid = true;
  }

  public int getWeight() {
    return weight;
  }

  public boolean isMessageValid() {
    return messageIsValid;
  }

  public WeightMessageToServer() {
    messageIsValid = false;
  }

  public static WeightMessageToServer decode(PacketBuffer buf) {
    WeightMessageToServer message = new WeightMessageToServer();
    try {
      message.weight = buf.readInt();

      // for Itemstacks - ByteBufUtils.readItemStack()
      // for NBT tags ByteBufUtils.readTag();
      // for Strings: ByteBufUtils.readUTF8String();
      // NB that PacketBuffer is a derived class of ByteBuf
    } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
      ElenaiDodge.LOGGER.warn("Exception while reading WeightMessageToServer: " + e);
      return message;
    }
    message.messageIsValid = true;
    return message;
  }

  public void encode(PacketBuffer buf) {
    if (!messageIsValid) return;
   buf.writeInt(weight);

    // for Itemstacks - ByteBufUtils.writeItemStack()
    // for NBT tags ByteBufUtils.writeTag();
    // for Strings: ByteBufUtils.writeUTF8String();
    // NB that PacketBuffer is a derived class of ByteBuf
  }

  @Override
  public String toString() {
    return "WeightMessageToServer[weight=" + String.valueOf(weight) + "]";
  }
}
