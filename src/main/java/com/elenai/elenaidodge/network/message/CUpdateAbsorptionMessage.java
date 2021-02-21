package com.elenai.elenaidodge.network.message;

import com.elenai.elenaidodge.ElenaiDodge;
import com.elenai.elenaidodge.util.ClientStorage;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class CUpdateAbsorptionMessage implements IMessage {
	
	/*
	 * A Message to transfer server side values when the player dodges
	 */

	private int absorption;

	private boolean messageValid;

	public CUpdateAbsorptionMessage() {
		this.messageValid = false;
	}

	public CUpdateAbsorptionMessage(int absorption) {
		this.absorption = absorption;

		this.messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.absorption = buf.readInt();

		} catch (IndexOutOfBoundsException ioe) {
			ElenaiDodge.LOG.error("Error occured whilst networking!", ioe);
			return;
		}
		this.messageValid = true;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (!this.messageValid) {
			return;
		}
		buf.writeInt(absorption);

	}

	public static class Handler implements IMessageHandler<CUpdateAbsorptionMessage, IMessage> {

		@Override
		public IMessage onMessage(CUpdateAbsorptionMessage message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.CLIENT) {
				return null;
			}
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
					.addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}

		void processMessage(CUpdateAbsorptionMessage message, MessageContext ctx) {
				ClientStorage.absorption = message.absorption;
		}
	}
}
