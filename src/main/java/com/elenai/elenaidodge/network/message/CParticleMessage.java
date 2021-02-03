package com.elenai.elenaidodge.network.message;

import com.elenai.elenaidodge.ElenaiDodge;
import com.elenai.elenaidodge.particle.ParticleCustom;
import com.elenai.elenaidodge.particle.ParticleCustom.TextureDefinition;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
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
			
			if(message.level == 3) {
				for (int a = 0; a < 8; ++a) {
					double d0 = Minecraft.getMinecraft().player.world.rand.nextGaussian() * 0.02D;
					double d1 = Minecraft.getMinecraft().player.world.rand.nextGaussian() * 0.02D;
					double d2 = Minecraft.getMinecraft().player.world.rand.nextGaussian() * 0.02D;
					   double xPos = message.x +(double) (Minecraft.getMinecraft().player.world.rand.nextFloat() * 0.6f * 2.0F) - (double) 0.6f - d0 * 12.0D;
				        double yPos = message.y  + 0.1;
				        double zPos = message.z + (double) (Minecraft.getMinecraft().player.world.rand.nextFloat() * 0.6f * 2.0F) - (double) 0.6f - d2 * 12.0D;
				        
				        Particle theParticle = new ParticleCustom(
				                new TextureDefinition("soul_fire_flame"),
				                Minecraft.getMinecraft().player.world, 
				                xPos, yPos, zPos, 
				                d0, d1, d2)
				                .setLifeSpan(10)
				                .setGravity(-0.1F)
				                .setScale(0.2f + Minecraft.getMinecraft().player.world.rand.nextFloat() * 0.6f * 2.0F)
				                .setInitialAlpha(1.0F);
				                
				        Minecraft.getMinecraft().effectRenderer.addEffect(theParticle);
						}
			}
			
			if(message.level == 4) {
				for (int a = 0; a < 8; ++a) {
					double d0 = Minecraft.getMinecraft().player.world.rand.nextGaussian() * 0.02D;
					double d1 = Minecraft.getMinecraft().player.world.rand.nextGaussian() * 0.02D;
					double d2 = Minecraft.getMinecraft().player.world.rand.nextGaussian() * 0.02D;
					   double xPos = message.x +(double) (Minecraft.getMinecraft().player.world.rand.nextFloat() * 0.6f * 2.0F) - (double) 0.6f - d0 * 12.0D;
				        double yPos = message.y  + 0.1;
				        double zPos = message.z + (double) (Minecraft.getMinecraft().player.world.rand.nextFloat() * 0.6f * 2.0F) - (double) 0.6f - d2 * 12.0D;
				        
				        Particle theParticle = new ParticleCustom(
				                new TextureDefinition("soul", true, 11),
				                Minecraft.getMinecraft().player.world, 
				                xPos, yPos, zPos, 
				                d0, d1, d2)
				                .setLifeSpan(20)
				                .setGravity(-0.10F)
				                .setScale(2.0f + Minecraft.getMinecraft().player.world.rand.nextFloat() * 0.6f * 2.0F)
				                .setInitialAlpha(1.0F)
				                .setFinalAlpha(0.0F);
				        Minecraft.getMinecraft().effectRenderer.addEffect(theParticle);
						}
			}
			
			}

		}
	}
}
