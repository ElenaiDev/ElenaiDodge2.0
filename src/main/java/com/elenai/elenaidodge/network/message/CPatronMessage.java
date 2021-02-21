package com.elenai.elenaidodge.network.message;

import com.elenai.elenaidodge.ElenaiDodge;
import com.elenai.elenaidodge.util.PatronRewardHandler;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class CPatronMessage implements IMessage {
	
	/*
	 * A Message to transfer server side values when the player level
	 */

	private int level;

	private boolean messageValid;

	public CPatronMessage() {
		this.messageValid = false;
	}

	public CPatronMessage(int level) {
		this.level = level;

		this.messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.level = buf.readInt();

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
		buf.writeInt(level);

	}

	public static class Handler implements IMessageHandler<CPatronMessage, IMessage> {

		@Override
		public IMessage onMessage(CPatronMessage message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.CLIENT) {
				return null;
			}
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
					.addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}

		void processMessage(CPatronMessage message, MessageContext ctx) {
			if(message.level == 99) {
				PatronRewardHandler.localPatronTier = 4;
			} else {
				PatronRewardHandler.localPatronTier = message.level;
			}
		}
	}
}
