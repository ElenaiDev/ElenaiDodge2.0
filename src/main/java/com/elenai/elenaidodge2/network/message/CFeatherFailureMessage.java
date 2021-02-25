package com.elenai.elenaidodge2.network.message;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.event.ClientTickEventListener;
import com.elenai.elenaidodge2.util.Utils;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class CFeatherFailureMessage implements IMessage {
	
	/*
	 * A Message to allow player.setVelocity to be run from the server
	 */


	private boolean messageValid;

	public CFeatherFailureMessage() {
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

	public static class Handler implements IMessageHandler<CFeatherFailureMessage, IMessage> {

		@Override
		public IMessage onMessage(CFeatherFailureMessage message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.CLIENT) {
				return null;
			}
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
					.addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}

		void processMessage(CFeatherFailureMessage message, MessageContext ctx) {
			ClientTickEventListener.failedFlashes = 0;
			Utils.showDodgeBar();
		}
	}
}
