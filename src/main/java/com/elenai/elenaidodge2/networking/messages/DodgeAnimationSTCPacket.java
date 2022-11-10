package com.elenai.elenaidodge2.networking.messages;

import java.util.function.Supplier;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.client.ED2ClientStorage;
import com.elenai.elenaidodge2.client.animation.DodgeAnimator;

import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;

public class DodgeAnimationSTCPacket {
	
	private final String direction;
	private final int id;
	
		public DodgeAnimationSTCPacket(String direction, int id) {
			this.direction = direction;
			this.id = id;
		}

		public DodgeAnimationSTCPacket(FriendlyByteBuf buf) {
			this.direction = buf.readUtf();
			this.id = buf.readInt();
		}

		public void toBytes(FriendlyByteBuf buf) {
			buf.writeUtf(direction);
			buf.writeInt(id);
		}

		public boolean handle(Supplier<NetworkEvent.Context> supplier) {
			NetworkEvent.Context context = supplier.get();
			context.enqueueWork(() -> {
				Minecraft instance = Minecraft.getInstance();
				var player = instance.level.getEntity(id);
				animatePlayer(DodgeAnimator.DodgeDirection.valueOf(direction), (AbstractClientPlayer) player);
			});
			return true;
		}
		
		public static void animatePlayer(DodgeAnimator.DodgeDirection direction, AbstractClientPlayer player) {
			if(ED2ClientStorage.isAnimating()) {
				var animation = DodgeAnimator.animationData.get(player);
				if (animation != null) {
					switch (direction) {
					case FORWARDS:
						animation.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry
								.getAnimation(new ResourceLocation(ElenaiDodge2.MODID, "animation.player.dodge"))));
						break;
					case LEFT:
						animation.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry
								.getAnimation(new ResourceLocation(ElenaiDodge2.MODID, "animation.player.dodge.left"))));
						break;
					case RIGHT:
						animation.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry
								.getAnimation(new ResourceLocation(ElenaiDodge2.MODID, "animation.player.dodge.right"))));
						break;
					case FORWARDS_LEFT:
						animation.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry
								.getAnimation(new ResourceLocation(ElenaiDodge2.MODID, "animation.player.dodge.forwardsleft"))));
						break;
					case FORWARDS_RIGHT:
						animation.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry
								.getAnimation(new ResourceLocation(ElenaiDodge2.MODID, "animation.player.dodge.forwardsright"))));
					break;
					case BACKWARDS_LEFT:
						animation.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry
								.getAnimation(new ResourceLocation(ElenaiDodge2.MODID, "animation.player.dodge.backwardsleft"))));
						break;
					case BACKWARDS_RIGHT:
						animation.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry
								.getAnimation(new ResourceLocation(ElenaiDodge2.MODID, "animation.player.dodge.backwardsright"))));
					break;
					default:
						animation.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry
								.getAnimation(new ResourceLocation(ElenaiDodge2.MODID, "animation.player.dodge.backwards"))));
						break;
					}
				}
			}
		}
}
