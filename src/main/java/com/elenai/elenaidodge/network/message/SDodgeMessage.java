package com.elenai.elenaidodge.network.message;

import com.elenai.elenaidodge.ElenaiDodge;
import com.elenai.elenaidodge.api.DodgeEvent;
import com.elenai.elenaidodge.api.DodgeEvent.Direction;
import com.elenai.elenaidodge.api.DodgeEvent.ServerDodgeEvent;
import com.elenai.elenaidodge.util.Utils;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class SDodgeMessage implements IMessage {
	private boolean messageValid;

	private String dir;
	
	public SDodgeMessage() {
		this.messageValid = false;
	}

	public SDodgeMessage(String dir) {
		this.dir = dir;
		this.messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {

		try {
			this.dir = ByteBufUtils.readUTF8String(buf);
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
		ByteBufUtils.writeUTF8String(buf, dir);
	}

	public static class Handler implements IMessageHandler<SDodgeMessage, IMessage> {

		@Override
		public IMessage onMessage(SDodgeMessage message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.SERVER) {
				return null;
			}
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
					.addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}

		void processMessage(SDodgeMessage message, MessageContext ctx) {;
			EntityPlayerMP player = ctx.getServerHandler().player;

			DodgeEvent.ServerDodgeEvent event = new ServerDodgeEvent(Direction.valueOf(message.dir), Utils.calculateForce(player), player);
			if(!MinecraftForge.EVENT_BUS.post(event)) {
				Utils.handleDodge(Direction.valueOf(message.dir), event, player);
			}
		}

	}
}
