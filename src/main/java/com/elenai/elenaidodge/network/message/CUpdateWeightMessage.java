package com.elenai.elenaidodge.network.message;

import com.elenai.elenaidodge.ElenaiDodge;
import com.elenai.elenaidodge.event.ArmorTickEventListener;
import com.elenai.elenaidodge.util.ClientStorage;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class CUpdateWeightMessage implements IMessage {
	
	/*
	 * A Message to transfer server side values when the player dodges
	 */

	private int weight;

	private boolean messageValid;

	public CUpdateWeightMessage() {
		this.messageValid = false;
	}

	public CUpdateWeightMessage(int weight) {
		this.weight = weight;

		this.messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.weight = buf.readInt();

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
		buf.writeInt(weight);

	}

	public static class Handler implements IMessageHandler<CUpdateWeightMessage, IMessage> {

		@Override
		public IMessage onMessage(CUpdateWeightMessage message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.CLIENT) {
				return null;
			}
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
					.addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}

		void processMessage(CUpdateWeightMessage message, MessageContext ctx) {
			if(message.weight != 0) {	
			ClientStorage.weight = message.weight;
			} else {
				// Forces Armor Refresh
				ArmorTickEventListener.previousArmor.clear();
			}
		}
	}
}
