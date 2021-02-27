package com.elenai.elenaidodge2.network.message;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.util.ClientStorage;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class CUpdateRegenMessage implements IMessage {
	
	/*
	 * A Message to transfer server side values when the player regen
	 */

	private int regen;

	private boolean messageValid;

	public CUpdateRegenMessage() {
		this.messageValid = false;
	}

	public CUpdateRegenMessage(int regen) {
		this.regen = regen;

		this.messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.regen = buf.readInt();

		} catch (IndexOutOfBoundsException ioe) {
			ElenaiDodge2.LOG.error("Error occured whilst networking!", ioe);
			return;
		}
		this.messageValid = true;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (!this.messageValid) {
			return;
		}
		buf.writeInt(regen);

	}

	public static class Handler implements IMessageHandler<CUpdateRegenMessage, IMessage> {

		@Override
		public IMessage onMessage(CUpdateRegenMessage message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.CLIENT) {
				return null;
			}
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
					.addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}

		void processMessage(CUpdateRegenMessage message, MessageContext ctx) {
				ClientStorage.regenModifier = message.regen;
		}
	}
}
