package com.elenai.elenaidodge2.client.animation;

import java.util.IdentityHashMap;
import java.util.Map;

import com.elenai.elenaidodge2.ElenaiDodge2;

import dev.kosmx.playerAnim.api.layered.AnimationStack;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ElenaiDodge2.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DodgeAnimator {

    public static final Map<AbstractClientPlayer, ModifierLayer<IAnimation>> animationData = new IdentityHashMap<>();

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        PlayerAnimationAccess.REGISTER_ANIMATION_EVENT.register(DodgeAnimator::registerPlayerAnimation);
    }

    private static void registerPlayerAnimation(AbstractClientPlayer player, AnimationStack stack) {
        var layer = new ModifierLayer<>();
        stack.addAnimLayer(5000, layer);
        DodgeAnimator.animationData.put(player, layer);
    }
	
    public static enum DodgeDirection {
    	LEFT, RIGHT, FORWARDS, BACKWARDS, FORWARDS_LEFT, FORWARDS_RIGHT, BACKWARDS_LEFT, BACKWARDS_RIGHT;
    }
    
}
