package com.elenai.elenaidodge.particle;

import com.elenai.elenaidodge.particle.ParticleCustom.TextureDefinition;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;

public class ParticleGenerator {

	public static void generate(int level, double x, double y, double z) {
		if(level == 3) {
			for (int a = 0; a < 8; ++a) {
				double d0 = Minecraft.getMinecraft().player.world.rand.nextGaussian() * 0.02D;
				double d1 = Minecraft.getMinecraft().player.world.rand.nextGaussian() * 0.02D;
				double d2 = Minecraft.getMinecraft().player.world.rand.nextGaussian() * 0.02D;
				   double xPos = x +(double) (Minecraft.getMinecraft().player.world.rand.nextFloat() * 0.6f * 2.0F) - (double) 0.6f - d0 * 12.0D;
			        double yPos = y  + 0.1;
			        double zPos = z + (double) (Minecraft.getMinecraft().player.world.rand.nextFloat() * 0.6f * 2.0F) - (double) 0.6f - d2 * 12.0D;
			        
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
		
		if(level == 4) {
			for (int a = 0; a < 8; ++a) {
				double d0 = Minecraft.getMinecraft().player.world.rand.nextGaussian() * 0.02D;
				double d1 = Minecraft.getMinecraft().player.world.rand.nextGaussian() * 0.02D;
				double d2 = Minecraft.getMinecraft().player.world.rand.nextGaussian() * 0.02D;
				   double xPos = x +(double) (Minecraft.getMinecraft().player.world.rand.nextFloat() * 0.6f * 2.0F) - (double) 0.6f - d0 * 12.0D;
			        double yPos = y  + 0.1;
			        double zPos = z + (double) (Minecraft.getMinecraft().player.world.rand.nextFloat() * 0.6f * 2.0F) - (double) 0.6f - d2 * 12.0D;
			        
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
