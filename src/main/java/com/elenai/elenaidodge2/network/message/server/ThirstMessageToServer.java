package com.elenai.elenaidodge2.network.message.server;

import com.elenai.elenaidodge2.ElenaiDodge2;

import net.minecraft.network.PacketBuffer;

public class ThirstMessageToServer  {

	  private boolean messageIsValid;
	
  public ThirstMessageToServer() {
    messageIsValid = true;
  }


  public boolean isMessageValid() {
    return messageIsValid;
  }


  public static ThirstMessageToServer decode(PacketBuffer buf) {
    ThirstMessageToServer message = new ThirstMessageToServer();
    try {
      // for Itemstacks - ByteBufUtils.readItemStack()
      // for NBT tags ByteBufUtils.readTag();
      // for Strings: ByteBufUtils.readUTF8String();
      // NB that PacketBuffer is a derived class of ByteBuf
    } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
      ElenaiDodge2.LOGGER.warn("Exception while reading ThirstMessageToServer: " + e);
      return message;
    }
    message.messageIsValid = true;
    return message;
  }

  public void encode(PacketBuffer buf) {
    if (!messageIsValid) return;

    // for Itemstacks - ByteBufUtils.writeItemStack()
    // for NBT tags ByteBufUtils.writeTag();
    // for Strings: ByteBufUtils.writeUTF8String();
    // NB that PacketBuffer is a derived class of ByteBuf
  }

  @Override
  public String toString() {
    return "ThirstMessageToServer";
  }
}
