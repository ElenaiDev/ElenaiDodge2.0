package com.elenai.elenaidodge.network.message.client;

import com.elenai.elenaidodge.ElenaiDodge;

import net.minecraft.network.PacketBuffer;

public class AbsorptionMessageToClient {
	  private int absorption;
	  private boolean messageIsValid;
	
  public AbsorptionMessageToClient(int absorption) {
	  this.absorption = absorption;
    messageIsValid = true;
  }

  public int getAbsorption() {
    return absorption;
  }

  public boolean isMessageValid() {
    return messageIsValid;
  }

  // for use by the message handler only.
  public AbsorptionMessageToClient()
  {
    messageIsValid = false;
  }

  public static AbsorptionMessageToClient decode(PacketBuffer buf) {
	  AbsorptionMessageToClient message = new AbsorptionMessageToClient();
    try {
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
   * Called by the network code.
   * Used to write the contents of your message member variables into the ByteBuf, ready for transmission over the network.
   * @param buf
   */
  public void encode(PacketBuffer buf) {
    if (!messageIsValid) return;
    buf.writeInt(absorption);

    // for Itemstacks - ByteBufUtils.writeItemStack()
    // for NBT tags ByteBufUtils.writeTag();
    // for Strings: ByteBufUtils.writeUTF8String();
  }

  @Override
  public String toString() {
    return "AbsorptionMessageToClient[absorption=" + String.valueOf(absorption) + "]";
  }

}
