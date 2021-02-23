package com.elenai.elenaidodge2.network.message;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.particle.ParticleGenerator;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class CParticleMessage implements IMessage {
	
	/*
	 * A Message to allow player.setVelocity to be run from the server
	 */

	private int level;
	private double x, y, z;

	private boolean messageValid;

	public CParticleMessage() {
		this.messageValid = false;
	}

	public CParticleMessage(int level, double x, double y, double z) {
		this.level = level;
		this.x = x;
		this.y = y;
		this.z = z;

		this.messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.level = buf.readInt();
			this.x = buf.readDouble();
			this.y = buf.readDouble();
			this.z = buf.readDouble();

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
		buf.writeInt(level);
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);
	}

	public static class Handler implements IMessageHandler<CParticleMessage, IMessage> {

		@Override
		public IMessage onMessage(CParticleMessage message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.CLIENT) {
				return null;
			}
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
					.addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}

		void processMessage(CParticleMessage message, MessageContext ctx) {
			if(message.level > 0) {

			EnumParticleTypes particleType = null;
			switch (message.level) {
			case 1:
				particleType = EnumParticleTypes.HEART;
				break;
			case 2:
				particleType = EnumParticleTypes.FLAME;
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				particleType = EnumParticleTypes.END_ROD;
				break;
			default:
				break;
			}
			
			if(particleType != null) {
				for (int i = 0; i < 8; ++i) {
					double d0 = Minecraft.getMinecraft().player.world.rand.nextGaussian() * 0.02D;
					double d1 = Minecraft.getMinecraft().player.world.rand.nextGaussian() * 0.02D;
					double d2 = Minecraft.getMinecraft().player.world.rand.nextGaussian() * 0.02D;
					Minecraft.getMinecraft().player.world.spawnParticle(particleType,
							message.x + (double) (Minecraft.getMinecraft().player.world.rand.nextFloat() * 0.6f * 2.0F) - (double) 0.6f - d0 * 10.0D,
							message.y + 0.1,
							message.z + (double) (Minecraft.getMinecraft().player.world.rand.nextFloat() * 0.6f * 2.0F) - (double) 0.6f - d2 * 10.0D, d0, d1,
							d2);
				}
			}
			
			ParticleGenerator.generate(message.level, message.x, message.y, message.z);
			
			}
		}
	}
}
