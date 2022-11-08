package com.elenai.elenaidodge2.networking;

import java.util.function.Supplier;

import com.elenai.elenaidodge2.capability.PlayerInvincibilityProvider;
import com.elenai.elenaidodge2.config.ED2CommonConfig;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkEvent;

public class DodgeEffectsCTSPacket {
	
	private final String direction;
	
		public DodgeEffectsCTSPacket(String direction) {
			this.direction = direction;
		}

		public DodgeEffectsCTSPacket(FriendlyByteBuf buf) {
			this.direction = buf.readUtf();
		}

		public void toBytes(FriendlyByteBuf buf) {
			buf.writeUtf(direction);
		}

		public boolean handle(Supplier<NetworkEvent.Context> supplier) {
			NetworkEvent.Context context = supplier.get();
			context.enqueueWork(() -> {
				context.getSender().level.playSound(null, new BlockPos(context.getSender().position()), SoundEvents.PLAYER_ATTACK_SWEEP,
						SoundSource.PLAYERS, 1f, 4f + context.getSender().level.random.nextFloat());
				
				context.getSender().getCapability(PlayerInvincibilityProvider.PLAYER_INVINCIBILITY).ifPresent(i -> {
					i.setInvincibility(ED2CommonConfig.INVINCIBILITY_TICKS.get());
				});
				
				ED2Messages.sendToPlayersNearbyAndSelf(new DodgeAnimationSTCPacket(direction, context.getSender().getId()), context.getSender());
				
			});
			return true;
		}
}
