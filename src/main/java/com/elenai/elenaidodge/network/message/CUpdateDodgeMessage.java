package com.elenai.elenaidodge.network.message;

import com.elenai.elenaidodge.ElenaiDodge;
import com.elenai.elenaidodge.util.ClientStorage;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class CUpdateDodgeMessage implements IMessage {
	
	/*
	 * A Message to transfer server side values when the player dodges
	 */

	private int dodges;

	private boolean messageValid;

	public CUpdateDodgeMessage() {
		this.messageValid = false;
	}

	public CUpdateDodgeMessage(int dodges) {
		this.dodges = dodges;

		this.messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.dodges = buf.readInt();

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
		buf.writeInt(dodges);

	}

	public static class Handler implements IMessageHandler<CUpdateDodgeMessage, IMessage> {

		@Override
		public IMessage onMessage(CUpdateDodgeMessage message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.CLIENT) {
				return null;
			}
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
					.addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}

		void processMessage(CUpdateDodgeMessage message, MessageContext ctx) {
				ClientStorage.dodges = message.dodges;
		}
	}
}
