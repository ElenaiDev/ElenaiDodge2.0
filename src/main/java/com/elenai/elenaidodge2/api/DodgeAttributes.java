package com.elenai.elenaidodge2.api;

import com.elenai.elenaidodge2.ElenaiDodge2;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class DodgeAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister
            .create(Attribute.class, ElenaiDodge2.MODID);

    /**
     * measured in feathers per tick.
     */
    public static final RegistryObject<Attribute> FEATHERREGEN = ATTRIBUTES.register("feather_regeneration", () -> new RangedAttribute(ElenaiDodge2.MODID + ".featherTicks", 0d, Double.MIN_VALUE, Double.MAX_VALUE).setShouldWatch(true));
}
