package com.elenai.elenaidodge.network.message;

import com.elenai.elenaidodge.ElenaiDodge;
import com.elenai.elenaidodge.capability.dodges.DodgesProvider;
import com.elenai.elenaidodge.capability.dodges.IDodges;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class SDodgeRegenMessage implements IMessage {
	private boolean messageValid;

	private int dodges;
	
	public SDodgeRegenMessage() {
		this.messageValid = false;
	}

	public SDodgeRegenMessage(int dodges) {
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

	public static class Handler implements IMessageHandler<SDodgeRegenMessage, IMessage> {

		@Override
		public IMessage onMessage(SDodgeRegenMessage message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.SERVER) {
				return null;
			}
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
					.addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}

		void processMessage(SDodgeRegenMessage message, MessageContext ctx) {;
			EntityPlayerMP player = ctx.getServerHandler().player;
			IDodges d = player.getCapability(DodgesProvider.DODGES_CAP, null);
			if(d.getDodges() < 20) {
			d.set(message.dodges);
			}
		}
	}
}
