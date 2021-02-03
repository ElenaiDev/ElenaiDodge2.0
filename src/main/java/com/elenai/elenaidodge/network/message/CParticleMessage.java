package com.elenai.elenaidodge.network.message;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CParticleMessage implements IMessage<CParticleMessage> {

	private int level;
	double x, y, z;

	public CParticleMessage() {

	}

	public CParticleMessage(int level, double x, double y, double z) {
		this.level = level;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void encode(CParticleMessage message, PacketBuffer buffer) {
		buffer.writeInt(message.level);
		buffer.writeDouble(message.x);
		buffer.writeDouble(message.y);
		buffer.writeDouble(message.z);

	}

	@Override
	public CParticleMessage decode(PacketBuffer buffer) {
		return new CParticleMessage(buffer.readInt(), buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
	}

	@SuppressWarnings("resource")
	@Override
	public void handle(CParticleMessage message, Supplier<Context> supplier) {
		supplier.get().enqueueWork(() -> {
			IParticleData particle = null;
			switch(message.level) {
			case 0:
				break;
			case 1:
				particle = ParticleTypes.HEART;
				break;
			case 2:
				particle = ParticleTypes.FLAME;
				break;
			case 3:
				particle = ParticleTypes.SOUL_FIRE_FLAME;
				break;
			case 4:
				particle = ParticleTypes.SOUL;
				break;
			default:
				break;
			}

			if(message.level > 0) {
			for (int i = 0; i < 8; ++i) {
				double d0 = Minecraft.getInstance().player.world.rand.nextGaussian() * 0.02D;
				double d1 = Minecraft.getInstance().player.world.rand.nextGaussian() * 0.02D;
				double d2 = Minecraft.getInstance().player.world.rand.nextGaussian() * 0.02D;
				Minecraft.getInstance().player.world.addParticle(particle,
						message.x + (double) (Minecraft.getInstance().player.world.rand.nextFloat() * 0.6f * 2.0F) - (double) 0.6f - d0 * 12.0D,
						message.y + 0.1,
						message.z + (double) (Minecraft.getInstance().player.world.rand.nextFloat() * 0.6f * 2.0F) - (double) 0.6f - d2 * 12.0D, d0, d1, d2);
			}
			}
		});

		supplier.get().setPacketHandled(true);

	}

}
