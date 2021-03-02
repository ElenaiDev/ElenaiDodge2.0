package com.elenai.elenaidodge2.mixin;

import com.elenai.elenaidodge2.api.DodgeAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class MixinPlayerAttribute {

    @Inject(at = @At("RETURN"), method = "func_234570_el_()Lnet/minecraft/entity/ai/attributes/AttributeModifierMap$MutableAttribute;")
    private static void createAttributes(CallbackInfoReturnable<AttributeModifierMap.MutableAttribute> cb) {
        cb.getReturnValue().createMutableAttribute(DodgeAttributes.FEATHERREGEN.get());
    }
}