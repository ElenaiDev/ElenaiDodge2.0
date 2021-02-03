package com.elenai.elenaidodge.network.message;

import com.elenai.elenaidodge.ElenaiDodge;
import com.elenai.elenaidodge.capability.weight.IWeight;
import com.elenai.elenaidodge.capability.weight.WeightProvider;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class SWeightMessage implements IMessage {
	private boolean messageValid;

	private int weight;
	
	public SWeightMessage() {
		this.messageValid = false;
	}

	public SWeightMessage(int weight) {
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

	public static class Handler implements IMessageHandler<SWeightMessage, IMessage> {

		@Override
		public IMessage onMessage(SWeightMessage message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.SERVER) {
				return null;
			}
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
					.addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}

		void processMessage(SWeightMessage message, MessageContext ctx) {;
			EntityPlayerMP player = ctx.getServerHandler().player;
			IWeight w = player.getCapability(WeightProvider.WEIGHT_CAP, null);
			w.set(message.weight);
			
		}
	}
}
