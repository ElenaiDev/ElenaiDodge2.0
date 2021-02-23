package com.elenai.elenaidodge2.network.message.server;

import com.elenai.elenaidodge2.ElenaiDodge;

import net.minecraft.network.PacketBuffer;

public class DodgeMessageToServer  {

	  private String dir;
	  private boolean messageIsValid;
	
  public DodgeMessageToServer(String dir) {
    this.dir = dir;
    messageIsValid = true;
  }

  public String getDirection() {
    return dir;
  }

  public boolean isMessageValid() {
    return messageIsValid;
  }

  public DodgeMessageToServer() {
    messageIsValid = false;
  }

  public static DodgeMessageToServer decode(PacketBuffer buf) {
    DodgeMessageToServer message = new DodgeMessageToServer();
    try {
      message.dir = buf.readString(99999);

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
   buf.writeString(dir, 99999);

    // for Itemstacks - ByteBufUtils.writeItemStack()
    // for NBT tags ByteBufUtils.writeTag();
    // for Strings: ByteBufUtils.writeUTF8String();
    // NB that PacketBuffer is a derived class of ByteBuf
  }

  @Override
  public String toString() {
    return "DodgeMessageToServer[Direction=" + dir + "]";
  }
}
