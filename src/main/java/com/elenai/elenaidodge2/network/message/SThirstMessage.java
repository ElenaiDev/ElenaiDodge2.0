package com.elenai.elenaidodge2.network.message;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.integration.ToughAsNailsServer;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class SThirstMessage implements IMessage {
	private boolean messageValid;

	public SThirstMessage() {
		this.messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {

		try {
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
	}

	public static class Handler implements IMessageHandler<SThirstMessage, IMessage> {

		@Override
		public IMessage onMessage(SThirstMessage message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.SERVER) {
				return null;
			}
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
					.addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}

		void processMessage(SThirstMessage message, MessageContext ctx) {;
		if(Loader.isModLoaded("toughasnails")) {
			ToughAsNailsServer tan = new ToughAsNailsServer(ctx.getServerHandler().player);
			tan.addThirst();
			}
		}

	}
}
