package com.elenai.elenaidodge2.network.message.server;

import com.elenai.elenaidodge2.ElenaiDodge2;

import net.minecraft.network.PacketBuffer;

public class DodgeRegenMessageToServer  {

	  private int dodges;
	  private boolean messageIsValid;
	
  public DodgeRegenMessageToServer(int dodges) {
    this.dodges = dodges;
    messageIsValid = true;
  }

  public int getDodges() {
    return dodges;
  }

  public boolean isMessageValid() {
    return messageIsValid;
  }

  public DodgeRegenMessageToServer() {
    messageIsValid = false;
  }

  public static DodgeRegenMessageToServer decode(PacketBuffer buf) {
    DodgeRegenMessageToServer message = new DodgeRegenMessageToServer();
    try {
      message.dodges = buf.readInt();

      // for Itemstacks - ByteBufUtils.readItemStack()
      // for NBT tags ByteBufUtils.readTag();
      // for Strings: ByteBufUtils.readUTF8String();
      // NB that PacketBuffer is a derived class of ByteBuf
    } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
      ElenaiDodge2.LOGGER.warn("Exception while reading WeightMessageToServer: " + e);
      return message;
    }
    message.messageIsValid = true;
    return message;
  }

  public void encode(PacketBuffer buf) {
    if (!messageIsValid) return;
   buf.writeInt(dodges);

    // for Itemstacks - ByteBufUtils.writeItemStack()
    // for NBT tags ByteBufUtils.writeTag();
    // for Strings: ByteBufUtils.writeUTF8String();
    // NB that PacketBuffer is a derived class of ByteBuf
  }

  @Override
  public String toString() {
    return "DodgeRegenMessageToServer[Direction=" + String.valueOf(dodges) + "]";
  }
}
